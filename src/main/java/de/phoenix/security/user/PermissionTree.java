/*
 * Copyright (C) 2013 Project-Phoenix
 * 
 * This file is part of WebService.
 * 
 * WebService is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * WebService is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with WebService.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.phoenix.security.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PermissionTree implements Comparable<PermissionTree> {

    
    private PermissionTree root;
    private List<PermissionTree> childs;

    private final String node;

    /**
     * Creates a new permission tree without a node and without any childs
     */
    public PermissionTree() {
        this(null, null);
    }

    /**
     * Create a dummy node. Only used for searching childs
     * 
     * @param node
     *            The node without any "."
     */
    private PermissionTree(final String node) {
        this(null, node);
    }

    /**
     * Creates a child node
     * 
     * @param root
     *            The parent of this child
     * @param node
     *            The node without any "."
     */
    private PermissionTree(final PermissionTree root, final String node) {
        this.root = root;
        this.node = node;
    }

    /**
     * @return The root of this tree
     */
    public PermissionTree getRoot() {
        return root;
    }

    /**
     * @return An unmodifiable copy when the tree has childs and an empty list
     *         when the tree has no childs
     */
    public List<PermissionTree> getChilds() {
        return !isLeaf() ? Collections.unmodifiableList(childs) : Collections.<PermissionTree> emptyList();
    }

    /**
     * @return The permission node. Without any "."
     */
    public String getNode() {
        return node;
    }

    private final static String WILDCARD = "*";
    private final static PermissionTree WILDCARD_NODE = new PermissionTree(WILDCARD);
    private static final char NODE_SEPERATOR = '.';

    /**
     * Insert the new permission node to a direct child of the tree
     * 
     * @param node
     *            The node without any "."
     * @return The newly created child holding the node
     */
    private PermissionTree add(String node) {
        if (isLeaf())
            childs = new SortedList<PermissionTree>();

        if (node.equals(WILDCARD)) {
            childs.clear();
        }

        // There is already a wildcard - no need to add subnodes of a wildcard
        if (Collections.binarySearch(childs, WILDCARD_NODE) >= 0)
            return this;
        PermissionTree newNode = new PermissionTree(this, node);

        // Check if the node is already in the tree
        if (Collections.binarySearch(childs, newNode) >= 0)
            return this;
        else {
            // create new node
            childs.add(newNode);
            return newNode;
        }
    }

    /**
     * Add a new permission node to the permission tree.
     * 
     * @param node
     *            A permission node to add in the format "node.node"
     */
    public void addNode(String node) {
        // Split at first fullstop
        int pointIndex = node.indexOf(NODE_SEPERATOR);
        // Node is a leaf - insert at this tree
        if (pointIndex == -1)
            add(node);
        else {
            // Split node at first fullstop
            // Prefix
            String prefix = node.substring(0, pointIndex);
            String suffix = node.substring(pointIndex + 1);

            PermissionTree childNode = null;
            if (isLeaf()) {
                childNode = add(prefix);
            } else {
                // Prevent double nodes
                int i = Collections.binarySearch(childs, new PermissionTree(prefix));
                if (i < 0)
                    childNode = add(prefix);
                else
                    childNode = childs.get(i);
            }
            childNode.addNode(suffix);
        }
    }

    /**
     * Check if the permission tree is holding the specific node or has a
     * wildcard which includes the specific node
     * 
     * @param node
     *            A permission node to add in the format "node.node"
     * @return True when the permission node is holding the specific node or
     *         when the permission tree has in a higher level of the hierachie
     *         of the specific node a wildcard
     */
    public boolean hasNode(String node) {

        if (isEmpty())
            return false;

        int pointIndex = node.indexOf(NODE_SEPERATOR);
        if (pointIndex == -1) {
            if (childs.size() == 1 && childs.get(0).getNode().equals(WILDCARD)) {
                return true;
            }
            // Node must be a child of this subtree
            return Collections.binarySearch(childs, new PermissionTree(node)) >= 0;
        } else {
            // Node must be a child of a subtree of this subtree

            // Split the node at the first .
            String prefix = node.substring(0, pointIndex);
            String suffix = node.substring(pointIndex + 1);
            if (childs.size() == 1 && childs.get(0).getNode().equals(WILDCARD)) {
                return true;
            }

            // Search for possible subtree with the node
            int i = Collections.binarySearch(childs, new PermissionTree(prefix));
            return i >= 0 ? childs.get(i).hasNode(suffix) : false;
        }
    }

    /**
     * Removes the node of the permission tree. When there is a wildcard on the
     * hierachie of the specific node, no node is deleted or changed!
     * 
     * @param node
     *            A permission node to add in the format "node.node"
     * @return True when the permission node was part of the tree, otherwise
     *         false
     */
    public boolean removeNode(String node) {
        if (isEmpty())
            return false;

        // Search for the node
        int pointIndex = node.indexOf(NODE_SEPERATOR);
        if (pointIndex == -1) {
            int i = Collections.binarySearch(childs, new PermissionTree(node));
            // Not in the tree
            if (i < 0)
                return false;
            else {
                childs.remove(i);
                // Safe memory
                if (childs.isEmpty())
                    childs = null;
                return true;
            }
        }
        // Split the node
        String prefix = node.substring(0, pointIndex);
        String suffix = node.substring(pointIndex + 1);
        int i = Collections.binarySearch(childs, new PermissionTree(prefix));
        if (i < 0)
            return false;
        else
            return childs.get(i).removeNode(suffix);
    }

    /**
     * Creates a 1:1 copy of this permission tree
     * 
     * @return A new permission tree
     */
    public PermissionTree copy() {
        return copyAndAdd(new PermissionTree());
    }

    /**
     * Add all permission nodes of this permission tree to the copy
     * 
     * @param copy
     *            The target permission tree holding all permissions of this and
     *            the copy tree
     * @return The completly filled permission tree
     */
    public PermissionTree copyAndAdd(final PermissionTree copy) {
        if (isEmpty())
            return copy;
        for (PermissionTree child : childs) {
            child.copy(copy, child.getNode());
        }
        return copy;
    }

    /**
     * Recursive helper function
     * 
     * @param copy
     *            Target permission tree
     * @param parentNode
     *            Current node
     */
    private void copy(final PermissionTree copy, String parentNode) {
        if (isLeaf()) {
            copy.addNode(parentNode);
        } else {
            for (PermissionTree child : childs) {
                child.copy(copy, parentNode + "." + child.getNode());
            }
        }
    }

    @JsonIgnore
    /**
     * @return True when the tree is holding no permission tree
     */
    public boolean isEmpty() {
        return this.node == null && this.childs == null;
    }

    @JsonIgnore
    /**
     * @return True when the tree has no childs(but can have a root)
     */
    private boolean isLeaf() {
        return this.childs == null;
    }

    /**
     * Transform the permission tree to a list containing all permission node.
     * The format of the node is "node.node"
     * 
     * @return LinkedList of all permission nodes
     */
    public List<String> toList() {
        if (isEmpty())
            return Collections.<String> emptyList();
        List<String> list = new LinkedList<String>();
        for (PermissionTree child : childs) {
            child.toList(list, child.getNode());
        }
        return list;
    }

    /**
     * Recursive helper function
     * 
     * @param list
     *            The target lis t
     * @param parentNode
     *            The current node
     */
    private void toList(final List<String> list, String parentNode) {
        if (isLeaf()) {
            list.add(parentNode);
        } else {
            for (PermissionTree child : childs) {
                child.toList(list, parentNode + "." + child.getNode());
            }
        }
    }

    /**
     * @return The node of this permission tree
     */
    @Override
    public String toString() {
        return node;
    }

    /**
     * Compares the permission node of the permission tree
     */
    public int compareTo(PermissionTree other) {
        return this.node.compareTo(other.node);
    }
    
    public static class SortedList<T extends Comparable<T>> extends ArrayList<T> {

        private static final long serialVersionUID = -441423857950125427L;

        /**
         * Add the element to this sorted list in a sorted order.
         * 
         * @param e
         *            The element
         * @return Always true
         * 
         */
        @Override
        public boolean add(T e) {

            int insertPoint = Collections.binarySearch(this, e);
            if (insertPoint < 0)
                insertPoint = (insertPoint * -1) - 1;
            super.add(insertPoint, e);
            return true;

        }

        /**
         * 
         * @deprecated Function ignores the index and insert the element in the
         *             correct order
         */
        @Deprecated
        public void add(int index, T element) {
            add(element);
        }

        /**
         * Add all elements from the collection to this sorted list in a sorted
         * order.
         * 
         * @param c
         *            The collection holding all elements to add to this sorted
         *            list. The elements in the collection needn't to be in sorted
         *            order
         * @return Always true
         * 
         */
        @Override
        public boolean addAll(Collection<? extends T> c) {
            for (T s : c)
                add(s);
            return true;
        }

        /**
         * 
         * @deprecated Function ignores the index and insert the elements in the
         *             correct order
         */
        @Deprecated
        public boolean addAll(int index, Collection<? extends T> c) {
            return addAll(c);
        }
    }
}

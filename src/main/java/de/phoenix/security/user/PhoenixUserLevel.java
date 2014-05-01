/*
 * Copyright (C) 2014 Project-Phoenix
 * 
 * This file is part of Library.
 * 
 * Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Library.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.phoenix.security.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;
import de.phoenix.security.permission.datastructure.PermissionTree;

public class PhoenixUserLevel implements PhoenixEntity {

    public static final PhoenixUserLevel DEFAULT = new PhoenixUserLevel("default", new PermissionTree());
    
    private PhoenixUserLevel parent;

    @JsonProperty
    private PermissionTree ownPermissions;

    @Key
    private String name;

    protected PhoenixUserLevel() {
        // Empty constructor for JSON transport
    }

    public PhoenixUserLevel(String name, PermissionTree permissions) {
        this(null, name, permissions);
    }

    public PhoenixUserLevel(PhoenixUserLevel parent, String name, PermissionTree permissions) {
        this.parent = parent;
        this.name = name;
        this.ownPermissions = permissions.copy();
    }

    public boolean hasPermission(String node) {
        return ownPermissions.hasNode(node) || (parent != null && parent.hasPermission(node));
    }

    @JsonIgnore
    public List<String> getPermissionNodes() {
        return ownPermissions.toList();
    }

    public String getName() {
        return name;
    }

    public PhoenixUserLevel getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "PhoenixUserLevel [parent=" + parent + ", ownPermissions=" + ownPermissions + ", name=" + name + "]";
    }

}

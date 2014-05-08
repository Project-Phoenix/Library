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

package de.phoenix.security.user.permission;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;
import de.phoenix.security.user.PhoenixUser;

public class PhoenixUserLevel implements PhoenixEntity {

    public static final String WEB_RESOURCE_ROOT = PhoenixUser.WEB_RESOURCE_ROOT;
    public static final String WEB_RESOURCE_CREATE = "createUserlevel";
    public static final String WEB_RESOURCE_CHANGE_PERMISSION = "changePermission";

    public static final PhoenixUserLevel DEFAULT = new PhoenixUserLevel("default", new PermissionTree());

    private PhoenixUserLevel parent;

    private PermissionTree permissions;

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
        this.permissions = permissions.copy();
    }

    public boolean hasPermission(String node) {
        return permissions.hasNode(node) || (parent != null && parent.hasPermission(node));
    }

    public PermissionTree getPermissions() {
        return permissions;
    }

    public String getName() {
        return name;
    }

    public PhoenixUserLevel getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "PhoenixUserLevel [parent=" + parent + ", ownPermissions=" + permissions + ", name=" + name + "]";
    }

    /**
     * Resource needs: <xmp> PhoenixUserLevel </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The create webresource for PhoenixUserLevel
     */
    public static WebResource createResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CREATE);
    }

    /**
     * Resource needs: <xmp> ChangeUserLevelPermission </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The change permission webresource for PhoenixUserLevel
     */
    public static WebResource changePermissionResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CHANGE_PERMISSION);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

}

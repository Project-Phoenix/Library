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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

public class PhoenixUser implements PhoenixEntity {

    public static final String WEB_RESOURCE_ROOT = "user";
    public static final String WEB_RESOURCE_CREATE = "create";
    public static final String WEB_RESOURCE_GET = "get";

    private String surname;
    private String name;

    @Key
    private String username;

    private String mail;

    private PhoenixUserLevel userLevel;

    protected PhoenixUser() {
        // Empty constructor for JSON transport
    }

    public PhoenixUser(String surname, String name, String username, String mail) {
        this(surname, name, username, mail, PhoenixUserLevel.DEFAULT);
    }

    public PhoenixUser(String surname, String name, String username, String mail, PhoenixUserLevel userLevel) {

        this.surname = surname;
        this.name = name;
        this.username = username;
        this.mail = mail;
        this.userLevel = userLevel;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public PhoenixUserLevel getUserLevel() {
        return userLevel;
    }

    @Override
    public String toString() {
        return "PhoenixUser [surname=" + surname + ", name=" + name + ", username=" + username + ", mail=" + mail + ", userLevel=" + userLevel + "]";
    }

    /**
     * Resource needs: <xmp> CreatePhoenixUser </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The create webresource for PhoenixUser
     */
    public static WebResource createResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CREATE);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixUser> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixUser
     */
    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

}

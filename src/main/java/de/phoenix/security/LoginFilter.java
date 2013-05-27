/*
 * Copyright (C) 2013 Project-Phoenix
 * 
 * This file is part of library.
 * 
 * library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with library.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.phoenix.security;

import org.apache.commons.codec.digest.DigestUtils;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

public class LoginFilter extends ClientFilter {

    private final String username;
    private final String password;

    public static final String NAME_HEAD = "Username";
    public static final String PASS_HEAD = "Password";

    /**
     * A single use filter to authentifacte the user by its username and
     * password for once. Use this to get a token from the server and delete the
     * LoginFilter!
     * 
     * @param user
     *            The username
     * @param password
     *            The password in plain text. It will be pre hashed in the class
     */
    public LoginFilter(String user, String password) {
        this.username = user;
        this.password = DigestUtils.sha512Hex(password);
    }

    @Override
    public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {

        cr.getHeaders().add(NAME_HEAD, username);
        cr.getHeaders().add(PASS_HEAD, password);

        return getNext().handle(cr);
    }

}

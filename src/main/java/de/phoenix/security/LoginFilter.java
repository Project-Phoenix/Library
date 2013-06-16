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

import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.codec.digest.DigestUtils;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.core.util.Base64;

public final class LoginFilter extends ClientFilter {

    // Base64 encoded string for Basic Http Authentifaction
    private final String authentication;

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
        authentication = createAuthentificationString(user, password);
    }

    // Using SHA512 for creating a hexadecimal encoded hash
    private static final int HASH_PASSWORD_LENGTH = 64;

    private String createAuthentificationString(String user, String password) {

        // Authentifaction string as defined in HTTP for Basic
        // Authentification(User:Password in Base64)

        // Build string
        StringBuilder sBuilder = new StringBuilder(user.length() + HASH_PASSWORD_LENGTH);
        sBuilder.append(user).append(':');
        // Hash password with sha512
        sBuilder.append(DigestUtils.sha512Hex(password));
        try {
            // Encode the string
            return "Basic " + new String(Base64.encode(sBuilder.toString()), "ASCII");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @Override
    public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {

        // Append authentifaction string to the http request
        if (!cr.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            cr.getHeaders().add(HttpHeaders.AUTHORIZATION, authentication);
        }
        return getNext().handle(cr);
    }

}

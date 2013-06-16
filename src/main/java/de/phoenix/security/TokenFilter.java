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

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

public final class TokenFilter extends ClientFilter {

    private final Token token;

    public static final String TOKEN_HEAD = "PhoenixToken";

    /**
     * Constructs a filter for the Client or single Webresources to
     * authentifcate the user by a unique order of chars instead of sending and
     * validating everytime the user by its password or username
     * 
     * @param token
     *            The generated token from the Webservice
     */
    public TokenFilter(Token token) {
        this.token = token;
    }

    @Override
    public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
        // TODO: Implement check if token is expired?

        // Append authentifaction string to the http request
        if (!cr.getHeaders().containsKey(TOKEN_HEAD)) {
            cr.getHeaders().add(TOKEN_HEAD, token.getID());
        }
        return getNext().handle(cr);
    }

}

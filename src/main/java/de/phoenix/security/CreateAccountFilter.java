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

/**
 * Class to encapsulate the password encription method. <br>
 * This is just a renamed filter for better usage. <br>
 * It will not do anything after one use!
 * 
 * @author Meldanor
 * 
 */
public class CreateAccountFilter extends LoginFilter {

    boolean wasUsed = false;

    /**
     * Temponary filter. Use this filter only once!
     * 
     * @param user
     *            The user in clear text
     * @param password
     *            The password in clear text
     */
    public CreateAccountFilter(String user, String password) {
        super(user, password);
    }

    @Override
    public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
        // Apply the filter only once!
        if (wasUsed) {
            throw new ClientHandlerException("CreateAccountFilter multiple used!");
        } else {
            wasUsed = true;
            return super.handle(cr);
        }
    }
}

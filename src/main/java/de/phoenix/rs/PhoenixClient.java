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

package de.phoenix.rs;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

/**
 * Wrapper class to have an client with Jackson and JodaTime support
 */
public class PhoenixClient extends Client {

    /**
     * Create a client for the phoenix webservice. This will add jackson
     * provider and joda time support
     */
    public PhoenixClient() {
        super(new URLConnectionClientHandler(), new DefaultClientConfig(JacksonJaxbJsonProvider.class, JacksonConfigurator.class));
    }

    /**
     * Create a client for the phoenix webservice. This will add jackson
     * provider and joda time support
     * 
     * @return Client with jackson and joda time
     */
    public static Client create() {
        return new PhoenixClient();
    }

}

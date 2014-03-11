/*
 * Copyright (C) 2014 Project-Phoenix
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

import java.util.List;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Util class for entity handeling with the jersey API
 */
public class EntityUtil {

    private EntityUtil() {

    }

    /**
     * Wrapper for
     * <p>
     * <code>return (List<T>) response.getEntity(List.class);</code>
     * <p>
     * to prevent the unchecked warning.
     * 
     * @param response
     *            The response containing a list
     * @return Extracted list casted to correct type
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> extractEntityList(ClientResponse response) {
        return (List<T>) response.getEntity(List.class);
    }

    /**
     * Wrapper for
     * <p>
     * <code>EntityUtil.extractEntityList(response).get(0);</code>
     * 
     * @param response
     *            Response containing a list
     * @return Extracted, first element of the list
     */
    public static <T> T extractEntity(ClientResponse response) {
        List<T> list = extractEntityList(response);
        if (list == null || list.isEmpty())
            return null;
        else
            return list.get(0);
    }

}

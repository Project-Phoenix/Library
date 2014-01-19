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

package de.phoenix.rs.key;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SelectEntity<T extends PhoenixEntity> {

    @JsonProperty
    protected Map<String, Object> values = new HashMap<String, Object>();

    public SelectEntity() {
    }

    public SelectEntity<T> addKey(String name, Object obj) {
        if (obj instanceof PhoenixEntity)
            obj = KeyReader.createSelect((PhoenixEntity) obj);

        values.put(name, obj);
        return this;
    }

    /**
     * 
     * @param name
     * @param clazz
     * @return
     * @deprecated Use {@link #get(String)} without class definition
     */
    public <E> E get(String name, Class<? extends E> clazz) {
        @SuppressWarnings("unchecked")
        E value = (E) values.get(name);
        return value;
    }

    public <E> E get(String attributeName) {
        @SuppressWarnings("unchecked")
        E value = (E) values.get(attributeName);
        return value;
    }
}

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

/**
 * Class to select entities of an entity type using simple AND operation between
 * the attributes
 * 
 * @param <T>
 */
public class SelectEntity<T extends PhoenixEntity> {

    @JsonProperty
    protected Map<String, Object> values = new HashMap<String, Object>();

    /**
     * Construct a SelectEntity of the entity type without any keys.
     */
    public SelectEntity() {

    }

    /**
     * Add a single attribute as a key for the SelectEntity. All entites having
     * this attribute are selected and retrieved
     * 
     * @param keyName
     *            The name of the attribute. When the object is an instance of
     *            PhoenixEntity, it will automatically create a SelectEntity for
     *            the value and add it as a key.
     * @param value
     *            The value of the attribute
     * @return This SelectEntity with new added key
     */
    public SelectEntity<T> addKey(String keyName, Object value) {
        if (value instanceof PhoenixEntity)
            value = KeyReader.createSelect((PhoenixEntity) value);

        values.put(keyName, value);
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

    /**
     * Return the value for the key name
     * 
     * @param keyName
     *            The key name to identify
     * @return <code>null</code> if, and only if, the key name was not set.
     *         Otherwise the value of the key
     */
    public <E> E get(String keyName) {
        @SuppressWarnings("unchecked")
        E value = (E) values.get(keyName);
        return value;
    }

    @Override
    public String toString() {
        return String.format("SelectEntity={ValueMap=%s}", values);
    }
}

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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@formatter:off
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = UpdateEntity.class, name = "updateEntity"),
                @Type(value = SelectAllEntity.class, name = "selectAllEntity")})
//@formatter:on
public class SelectEntity<T extends PhoenixEntity> {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @JsonProperty
    protected Map<String, String> values = new HashMap<String, String>();

    public SelectEntity() {
    }

    public SelectEntity<T> addKey(String name, Object obj) {

        try {
            String value = JSON_MAPPER.writeValueAsString(obj);
            values.put(name, value);
            return this;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't serialize '" + obj + "'!");
        }
    }

    public <E> E get(String name, Class<? extends E> clazz) {
        try {
            String value = values.get(name);
            return JSON_MAPPER.readValue(value, clazz);
        } catch (Exception e) {
            return null;
        }
    }
}

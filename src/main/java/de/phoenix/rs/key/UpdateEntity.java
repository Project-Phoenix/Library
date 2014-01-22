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

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateEntity<T extends PhoenixEntity> extends SelectEntity<T> {

    @JsonProperty
    private T newObject;

    protected UpdateEntity() {
    }

    public UpdateEntity(T newObject) {
        if (newObject == null) {
            throw new NullPointerException("The new object cannot be null!");
        }
        this.newObject = newObject;
    }

    public UpdateEntity(T newObject, SelectEntity<T> entity) {
        this(newObject);
        this.values = new HashMap<String, Object>(entity.values);
    }

    public T getNewObject() {
        return newObject;
    }

    @Override
    public UpdateEntity<T> addKey(String name, Object obj) {
        return (UpdateEntity<T>) super.addKey(name, obj);
    }

    @Override
    public String toString() {
        return String.format("UpdateEntity={%s;NewObject=%s}", super.toString(), newObject);
    }
}

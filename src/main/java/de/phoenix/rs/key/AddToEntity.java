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

package de.phoenix.rs.key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddToEntity<T extends PhoenixEntity, E extends PhoenixEntity> extends SelectEntity<T> {

    @JsonProperty
    private List<E> attachedEntities;

    protected AddToEntity() {

    }

    public AddToEntity(E... entities) {
        this.attachedEntities = Arrays.asList(entities);
    }

    protected AddToEntity(SelectEntity<T> selectEntity, E... entities) {
        this(entities);
        this.values = new HashMap<String, Object>(selectEntity.values);
    }

    public AddToEntity(List<E> entityList) {
        this.attachedEntities = new ArrayList<E>(entityList);
    }

    protected AddToEntity(SelectEntity<T> selectEntity, List<E> entityList) {
        this(entityList);
        this.values = new HashMap<String, Object>(selectEntity.values);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AddToEntity<T, E> addKey(String name, Object obj) {
        return (AddToEntity<T, E>) super.addKey(name, obj);
    }

    public List<E> getAttachedEntities() {
        return new ArrayList<E>(attachedEntities);
    }

    @Override
    public String toString() {
        return String.format("AddToEntity={%s;AttachedEntities=%s}", super.toString(), attachedEntities);
    }
}

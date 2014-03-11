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

/**
 * Add some other entities of type E to one entity of type T
 * 
 * @param <T>
 *            One entity of this type
 * @param <E>
 *            Many entities of this type
 */
public class AddToEntity<T extends PhoenixEntity, E extends PhoenixEntity> extends SelectEntity<T> {

    @JsonProperty
    private List<E> attachedEntities;

    /**
     * Empty constructor for json-transport
     */
    protected AddToEntity() {

    }

    /**
     * Construct AddToEntity with entities as to attached entities.
     * 
     * @param entities
     *            Entities to add to an entity identified by keys
     * @deprecated Use {@link #AddToEntity(List)} with
     *             {@link Arrays#asList(Object...)} as argument
     */
    public AddToEntity(E... entities) {
        this.attachedEntities = Arrays.asList(entities);
    }

    /**
     * Construct AddToEntity with entities as to attached entities copying all
     * key values from the selectEntity to this AddToEntity
     * 
     * @param selectEntity
     *            SelectEntity to copy the values from.
     * @param entities
     *            Entities to add to the entity matches by the select entity
     * @deprecated Use {@link #AddToEntity(List)} with
     *             {@link Arrays#asList(Object...)}
     */
    protected AddToEntity(SelectEntity<T> selectEntity, E... entities) {
        this(entities);
        this.values = new HashMap<String, Object>(selectEntity.values);
    }

    /**
     * Construct AddToEntity with entities as to attached entities.
     * 
     * @param entityList
     *            Entities to add to an entity identified by keys
     */
    public AddToEntity(List<E> entityList) {
        this.attachedEntities = new ArrayList<E>(entityList);
    }

    /**
     * Construct AddToEntity with entities as to attached entities copying all
     * key values from the selectEntity to this AddToEntity
     * 
     * @param selectEntity
     *            SelectEntity to copy the values from.
     * @param entityList
     *            Entities to add to the entity matches by the select entity
     */
    public AddToEntity(SelectEntity<T> selectEntity, List<E> entityList) {
        this(entityList);
        this.values = new HashMap<String, Object>(selectEntity.values);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AddToEntity<T, E> addKey(String name, Object obj) {
        return (AddToEntity<T, E>) super.addKey(name, obj);
    }

    /**
     * @return Copy of the attached entities
     */
    public List<E> getAttachedEntities() {
        return new ArrayList<E>(attachedEntities);
    }

    @Override
    public String toString() {
        return String.format("AddToEntity={%s;AttachedEntities=%s}", super.toString(), attachedEntities);
    }
}

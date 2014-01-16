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

public class ConnectWithEntity<T extends PhoenixEntity, E extends PhoenixEntity> extends SelectEntity<T> {

    private List<SelectEntity<E>> toConnectEntities;

    protected ConnectWithEntity() {
        super();
    }

    public ConnectWithEntity(E... entities) {
        this(Arrays.asList(entities));
    }

    protected ConnectWithEntity(SelectEntity<T> selectEntity, E... entities) {
        this(entities);
        this.values = new HashMap<String, String>(selectEntity.values);
    }

    public ConnectWithEntity(List<E> entities) {
        this.toConnectEntities = new ArrayList<SelectEntity<E>>(entities.size());
        addEntities(entities);
    }

    protected ConnectWithEntity(SelectEntity<T> selectEntity, List<E> entities) {
        this(entities);
        this.values = new HashMap<String, String>(selectEntity.values);
    }

    public void addEntity(E entity) {
        SelectEntity<E> selectEntity = KeyReader.createSelect(entity);
        toConnectEntities.add(selectEntity);
    }

    public void addEntities(E... entities) {
        this.addEntities(Arrays.asList(entities));
    }

    public void addEntities(List<E> entities) {
        for (E e : entities) {
            addEntity(e);
        }
    }

    public List<SelectEntity<E>> getToConnectEntities() {
        return new ArrayList<SelectEntity<E>>(toConnectEntities);
    }

}

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

/**
 * Decorate class to select all entities from an entity type using no keys. Has
 * the same effect as:<br>
 * <code>
 * SelectEntity<T> sel = new SelectEntity<T>(); <br>
 * // not adding keys
 * </code>
 * 
 * It forbid the operation {@link #addKey(String, Object)}
 * 
 * @param <T>
 */
public class SelectAllEntity<T extends PhoenixEntity> extends SelectEntity<T> {

    @Override
    public SelectEntity<T> addKey(String name, Object obj) {
        throw new UnsupportedOperationException();
    }
}

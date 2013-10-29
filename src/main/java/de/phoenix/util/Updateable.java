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

package de.phoenix.util;

/**
 * Wrapper to update an existing entity. The existing entity(find by the oldKey)
 * will be replaced/updated by the Updated entity
 * 
 * @author Meldanor
 * 
 * @param <Updated>
 *            The new entity
 * @param <OldKey>
 *            The key to find the old
 */
public class Updateable<Updated, OldKey> {

    private Updated val;
    private OldKey key;

    /**
     * Constructor for json
     */
    protected Updateable() {

    }

    public Updateable(Updated val, OldKey key) {
        this.val = val;
        this.key = key;
    }

    public OldKey getKey() {
        return key;
    }

    public Updated getVal() {
        return val;
    }
}

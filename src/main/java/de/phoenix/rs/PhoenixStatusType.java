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

import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import de.phoenix.rs.key.SelectEntity;

/**
 * Custom defined http codes
 * 
 */
public enum PhoenixStatusType implements StatusType {

    //@formatter:off
    /**
     * When a {@link SelectEntity} matches more than one entity, but should match only one. Usually in delete or updates functions.
     */
    MULTIPLE_ENTITIES       (Family.CLIENT_ERROR,   460,    "Multiple entities!"),
    /**
     * When a {@link SelectEntity} matches no entity, but should match one. Usually in delete or updates functions.
     */
    NO_ENTITIES             (Family.CLIENT_ERROR,   461,    "No entities!"),
    /**
     * When there is already another entity with the same name.
     */
    DUPLIATE_ENTITY         (Family.CLIENT_ERROR,   462,    "Duplicate entity!"),
    /**
     * When there is already another entity with the same name.
     */
    NO_CURRENT_TASKSHEET    (Family.OTHER,          463,    "No Current Tasksheet!");
    //@formatter:on

    private final Family family;
    private final int statusCode;
    private final String phrase;

    private PhoenixStatusType(Family family, int statusCode, String phrase) {
        this.family = family;
        this.statusCode = statusCode;
        this.phrase = phrase;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public Family getFamily() {
        return family;
    }

    @Override
    public String getReasonPhrase() {
        return phrase;
    }

}

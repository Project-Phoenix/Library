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

package de.phoenix.rs.entity.connection;

import de.phoenix.rs.entity.PhoenixLectureGroup;
import de.phoenix.rs.entity.PhoenixTaskSheet;
import de.phoenix.rs.key.DisconnectEntity;
import de.phoenix.rs.key.SelectEntity;

/**
 * Decorator class to delete a single lecture group task sheet
 * 
 */
public class LectureGroupTaskSheetDisconnection extends DisconnectEntity {

    /**
     * Empty constructor for json-transport
     */
    protected LectureGroupTaskSheetDisconnection() {

    }

    /**
     * Disconnect entity to delete a single lecture group tasksheet using the
     * entities
     * 
     * @param lectureGroup
     *            The lecture group
     * @param taskSheet
     *            The task sheet
     */
    public LectureGroupTaskSheetDisconnection(PhoenixLectureGroup lectureGroup, PhoenixTaskSheet taskSheet) {
        addEntity(taskSheet);
        addEntity(lectureGroup);
    }

    /**
     * Disconnect entity to delet a single lecture group tasksheet using
     * selectors. The selectors must match only one
     * 
     * @param lectureGroupSelector
     *            Selector to match exact one lecture group
     * @param taskSheetSelector
     *            Selector to match exact one task sheet
     */
    public LectureGroupTaskSheetDisconnection(SelectEntity<PhoenixLectureGroup> lectureGroupSelector, SelectEntity<PhoenixTaskSheet> taskSheetSelector) {
        addSelectEntity(PhoenixLectureGroup.class, lectureGroupSelector);
        addSelectEntity(PhoenixTaskSheet.class, taskSheetSelector);
    }
}

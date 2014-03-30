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

package de.phoenix.rs.entity.disconnection;

import de.phoenix.rs.entity.PhoenixTask;
import de.phoenix.rs.entity.PhoenixTaskSheet;
import de.phoenix.rs.key.ConnectionEntity;
import de.phoenix.rs.key.SelectEntity;

/**
 * Decorator class for a {@link ConnectionEntity} removing a single task from a
 * task sheet
 */
public class DisconnectTaskTaskSheet extends ConnectionEntity {

    protected DisconnectTaskTaskSheet() {
        // Empty constructor for json transort
    }

    /**
     * Creates wrapper class using the select entities
     * 
     * @param taskSelector
     *            The task to remove from taskSheet
     * @param taskSheetSelector
     *            The tasksheet the task will be removed from
     */
    public DisconnectTaskTaskSheet(SelectEntity<PhoenixTask> taskSelector, SelectEntity<PhoenixTaskSheet> taskSheetSelector) {
        addSelectEntity(PhoenixTask.class, taskSelector);
        addSelectEntity(PhoenixTaskSheet.class, taskSheetSelector);
    }

    /**
     * Creates wrapper class using the entities
     * 
     * @param taskSelector
     *            The task to remove from taskSheet
     * @param taskSheetSelector
     *            The tasksheet the task will be removed from
     */
    public DisconnectTaskTaskSheet(PhoenixTask task, PhoenixTaskSheet taskSheet) {
        addEntity(task);
        addEntity(taskSheet);
    }

}

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

import de.phoenix.rs.entity.PhoenixTaskSheet;
import de.phoenix.rs.key.SelectEntity;

public class TaskSheetUpdate {

    private SelectEntity<PhoenixTaskSheet> taskSheetSelector;
    private String newTitle;

    protected TaskSheetUpdate() {

    }

    public TaskSheetUpdate(SelectEntity<PhoenixTaskSheet> taskSheetSelector, String newTitle) {
        this.newTitle = newTitle;
        this.taskSheetSelector = taskSheetSelector;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public SelectEntity<PhoenixTaskSheet> getTaskSheetSelector() {
        return taskSheetSelector;
    }

}

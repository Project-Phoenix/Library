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

import org.joda.time.DateTime;

import de.phoenix.rs.entity.PhoenixLectureGroupTaskSheet;
import de.phoenix.rs.entity.PhoenixTask;
import de.phoenix.rs.entity.PhoenixTaskSubmissionDates;
import de.phoenix.rs.key.ConnectionEntity;
import de.phoenix.rs.key.SelectEntity;

/**
 * Decorate class for a {@link ConnectionEntity} to create a
 * {@link PhoenixTaskSubmissionDates}
 */
public class TaskSubmissionDatesConnection extends ConnectionEntity {

    /**
     * Empty constructor for json-transport
     */
    protected TaskSubmissionDatesConnection() {

    }

    /**
     * Creates a Connection entity with all neccessary information
     * 
     * @param deadline
     *            The deadline for the task
     * @param releasedate
     *            The release date of the task
     * @param taskSheet
     *            The tasksheet the task is in
     * @param task
     *            The task itself
     */
    public TaskSubmissionDatesConnection(DateTime deadline, DateTime releasedate, PhoenixLectureGroupTaskSheet taskSheet, PhoenixTask task) {
        addEntity(task);
        addEntity(taskSheet);

        addAttribute("deadline", deadline);
        addAttribute("releaseDate", releasedate);
    }

    /**
     * Creates a Connection entity with all neccessary information
     * 
     * @param deadline
     *            The deadline for the task
     * @param releasedate
     *            The release date of the task
     * @param taskSheetSelector
     *            The tasksheet the task is in
     * @param taskSelector
     *            The task itself
     */
    public TaskSubmissionDatesConnection(DateTime deadline, DateTime releasedate, SelectEntity<PhoenixLectureGroupTaskSheet> taskSheetSelector, SelectEntity<PhoenixTask> taskSelector) {
        addSelectEntity(PhoenixLectureGroupTaskSheet.class, taskSheetSelector);
        addSelectEntity(PhoenixTask.class, taskSelector);

        addAttribute("deadline", deadline);
        addAttribute("releaseDate", releasedate);
    }
}

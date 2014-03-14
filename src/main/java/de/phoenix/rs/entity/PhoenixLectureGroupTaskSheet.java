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

package de.phoenix.rs.entity;

import java.util.List;

import org.joda.time.DateTime;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

/**
 * A single task sheet is nothing with a group assigned to this.
 */
public class PhoenixLectureGroupTaskSheet implements PhoenixEntity {

    public static final String WEB_RESOURCE_ROOT = "lectureGroupTaskSheet";

    public static final String WEB_RESOURCE_CREATE = "create";

    public static final String WEB_RESOURCE_GET = "get";

    @Key
    private PhoenixLectureGroup lectureGroup;

    private List<PhoenixDatedTask> tasks;

    /**
     * Empty constructor for json-transport
     */
    protected PhoenixLectureGroupTaskSheet() {

    }

    /**
     * Constructor for server
     * 
     * @param tasks
     * @param lectureGroup
     */
    public PhoenixLectureGroupTaskSheet(PhoenixLectureGroup lectureGroup, List<PhoenixDatedTask> tasks) {
        this.tasks = tasks;
        this.lectureGroup = lectureGroup;
    }

    /**
     * @return The lecture group having this tasksheet
     */
    public PhoenixLectureGroup getLectureGroup() {
        return lectureGroup;
    }

    /**
     * @return The tasks combined with their invidual release and deadline dates
     */
    public List<PhoenixDatedTask> getTasks() {
        return tasks;
    }

    /**
     * Decorator class to combine a task with its release and dead line dates
     * 
     * @author Kilian
     * 
     */
    public static class PhoenixDatedTask {

        private DateTime releaseDate;
        private DateTime deadlineDate;
        private PhoenixTask task;

        /**
         * Empty constructor for json-transport
         */
        protected PhoenixDatedTask() {

        }

        /**
         * Constructor for server
         * 
         * @param releaseDate
         * @param deadlineDate
         * @param task
         */
        public PhoenixDatedTask(DateTime releaseDate, DateTime deadlineDate, PhoenixTask task) {
            this.releaseDate = releaseDate;
            this.deadlineDate = deadlineDate;
            this.task = task;
        }

        /**
         * @return The deadline until a user can submit a solution for the task
         */
        public DateTime getDeadlineDate() {
            return deadlineDate;
        }

        /**
         * @return The release date for the task
         */
        public DateTime getReleaseDate() {
            return releaseDate;
        }

        /**
         * @return The task itself
         */
        public PhoenixTask getTask() {
            return task;
        }

        @Override
        public String toString() {
            return "PhoenixDatedTask={Deadline:" + deadlineDate + ";ReleaseDate:" + releaseDate + ";Task:" + task + "}";
        }
    }

    /**
     * Resource needs: <xmp> ConnectionEntity </xmp> Duplicate titles will
     * return an error
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The create webresource for PhoenixLectureGroupTaskSheet
     */
    public static WebResource createResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CREATE);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixLectureGroupTaskSheet> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The create webresource for PhoenixLectureGroupTaskSheet
     */
    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

    @Override
    public String toString() {
        return String.format("PhoenixLectureGroupTaskSheet={PhoenixDatedTasks:%s;PhoenixLectureGroup=%s}", tasks, lectureGroup);
    }

}

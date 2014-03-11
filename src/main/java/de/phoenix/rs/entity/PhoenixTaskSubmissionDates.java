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

import org.joda.time.DateTime;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

/**
 * Information for a single task in a {@link PhoenixLectureGroupTaskSheet} about
 * its own release and deadline date
 */
public class PhoenixTaskSubmissionDates implements PhoenixEntity {

    public static final String WEB_RESOURCE_ROOT = "taskSubmissionDates";

    public static final String WEB_RESOURCE_CREATE = "create";

    public static final String WEB_RESOURCE_GET = "get";

    private DateTime deadline;
    private DateTime releaseDate;

    @Key
    private PhoenixLectureGroupTaskSheet lectureGroupTaskSheet;
    @Key
    private PhoenixTask task;

    /**
     * Empty constructor for json-transport
     */
    protected PhoenixTaskSubmissionDates() {

    }

    /**
     * Constructor for client / server
     * 
     * @param deadline
     *            The dead line until a user can submit solutions for a task
     * @param releaseDate
     *            The release date of the task. After this date, all user can
     *            see and provide solutions for this task
     * @param lectureGroupTaskSheet
     *            The assigned task sheet
     * @param task
     *            The task
     */
    public PhoenixTaskSubmissionDates(DateTime deadline, DateTime releaseDate, PhoenixLectureGroupTaskSheet lectureGroupTaskSheet, PhoenixTask task) {
        this.deadline = deadline;
        this.releaseDate = releaseDate;
        this.lectureGroupTaskSheet = lectureGroupTaskSheet;
        this.task = task;
    }

    /**
     * @return The dead line until a user can submit solutions for a task
     */
    public DateTime getDeadline() {
        return deadline;
    }

    /**
     * @return The release date of the task. After this date, all user can see
     *         and provide solutions for this task
     */
    public DateTime getReleaseDate() {
        return releaseDate;
    }

    /**
     * @return Its lecture group
     */
    public PhoenixLectureGroupTaskSheet getLectureGroupTaskSheet() {
        return lectureGroupTaskSheet;
    }

    /**
     * @return The task
     */
    public PhoenixTask getTask() {
        return task;
    }

    /**
     * Resource needs: <xmp> ConnectionEntity </xmp> Duplicate titles will
     * return an error
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The create webresource for PhoenixTaskSubmissionDates
     */
    public static WebResource createResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CREATE);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixTaskSubmissionDates> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixTaskSubmissionDates
     */
    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

    @Override
    public String toString() {
        return String.format("PhoenixTaskSubmissionDates={Deadline=%s;ReleaseDate=%s;PhoenixTask=%s;PhoenixLectureGroupTaskSheet=%s};", deadline, releaseDate, task, lectureGroupTaskSheet);
    }
}

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
 * A single task sheet is nothing with a group assigned to this.
 */
public class PhoenixLectureGroupTaskSheet implements PhoenixEntity {

    public static final String WEB_RESOURCE_ROOT = "lectureGroupTaskSheet";

    public static final String WEB_RESOURCE_CREATE = "create";

    public static final String WEB_RESOURCE_GET = "get";

    private DateTime defaultDeadline;
    private DateTime defaultReleaseDate;

    @Key
    private PhoenixTaskSheet taskSheet;

    @Key
    private PhoenixLectureGroup lectureGroup;

    /**
     * Empty constructor for json-transport
     */
    protected PhoenixLectureGroupTaskSheet() {

    }

    /**
     * Constructor for client
     * 
     * @param defaultDeadline
     *            The default deadline for its tasks. If not further described
     *            by a {@link PhoenixTaskSubmissionDates}, all tasks of this
     *            tasksheet will have this dead line
     * @param defaultReleaseDate
     *            The default release date for this group task sheet. If not
     *            further * described by a {@link PhoenixTaskSubmissionDates},
     *            all tasks of this tasksheet will have this release date
     */
    public PhoenixLectureGroupTaskSheet(DateTime defaultDeadline, DateTime defaultReleaseDate) {
        this.defaultDeadline = defaultDeadline;
        this.defaultReleaseDate = defaultReleaseDate;
    }

    /**
     * Constructor for server
     * 
     * @param defaultDeadline
     * @param defaultReleaseDate
     * @param taskSheet
     * @param lectureGroup
     */
    public PhoenixLectureGroupTaskSheet(DateTime defaultDeadline, DateTime defaultReleaseDate, PhoenixTaskSheet taskSheet, PhoenixLectureGroup lectureGroup) {
        this.defaultDeadline = defaultDeadline;
        this.defaultReleaseDate = defaultReleaseDate;
        this.taskSheet = taskSheet;
        this.lectureGroup = lectureGroup;
    }

    /**
     * @return The default deadline for this task sheet and its tasks
     */
    public DateTime getDefaultDeadline() {
        return defaultDeadline;
    }

    /**
     * @return The default release date for this task sheet and its tasks
     */
    public DateTime getDefaultReleaseDate() {
        return defaultReleaseDate;
    }

    /**
     * @return The lecture group having this tasksheet
     */
    public PhoenixLectureGroup getLectureGroup() {
        return lectureGroup;
    }

    /**
     * @return The tasksheet assigned to the lecture group
     */
    public PhoenixTaskSheet getTaskSheet() {
        return taskSheet;
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
        return String.format("PhoenixLectureGroupTaskSheet={DefaultDeadline=%s;DefaultReleaseDate=%s;PhoenixTaskSheet=%s;PhoenixLectureGroup=%s}", defaultDeadline, defaultReleaseDate, taskSheet, lectureGroup);
    }

}

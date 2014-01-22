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

    protected PhoenixTaskSubmissionDates() {

    }

    public PhoenixTaskSubmissionDates(DateTime deadline, DateTime releaseDate, PhoenixLectureGroupTaskSheet lectureGroupTaskSheet, PhoenixTask task) {
        this.deadline = deadline;
        this.releaseDate = releaseDate;
        this.lectureGroupTaskSheet = lectureGroupTaskSheet;
        this.task = task;
    }

    public DateTime getDeadline() {
        return deadline;
    }

    public DateTime getReleaseDate() {
        return releaseDate;
    }

    public PhoenixLectureGroupTaskSheet getLectureGroupTaskSheet() {
        return lectureGroupTaskSheet;
    }

    public PhoenixTask getTask() {
        return task;
    }

    public static WebResource createResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CREATE);
    }

    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

    /**
     * private DateTime deadline; private DateTime releaseDate;
     * 
     * @Key private PhoenixLectureGroupTaskSheet lectureGroupTaskSheet;
     * @Key private PhoenixTask task;
     */

    @Override
    public String toString() {
        return String.format("PhoenixTaskSubmissionDates={Deadline=%s;ReleaseDate=%s;PhoenixTask=%s;PhoenixLectureGroupTaskSheet=%s};", deadline, releaseDate, task, lectureGroupTaskSheet);
    }
}

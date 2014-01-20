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

    protected PhoenixLectureGroupTaskSheet() {

    }

    public PhoenixLectureGroupTaskSheet(DateTime defaultDeadline, DateTime defaultReleaseDate) {
        this.defaultDeadline = defaultDeadline;
        this.defaultReleaseDate = defaultReleaseDate;
    }

    public PhoenixLectureGroupTaskSheet(DateTime defaultDeadline, DateTime defaultReleaseDate, PhoenixTaskSheet taskSheet, PhoenixLectureGroup lectureGroup) {
        this.defaultDeadline = defaultDeadline;
        this.defaultReleaseDate = defaultReleaseDate;
        this.taskSheet = taskSheet;
        this.lectureGroup = lectureGroup;
    }

    public DateTime getDefaultDeadline() {
        return defaultDeadline;
    }

    public DateTime getDefaultReleaseDate() {
        return defaultReleaseDate;
    }

    public PhoenixLectureGroup getLectureGroup() {
        return lectureGroup;
    }

    public PhoenixTaskSheet getTaskSheet() {
        return taskSheet;
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

}

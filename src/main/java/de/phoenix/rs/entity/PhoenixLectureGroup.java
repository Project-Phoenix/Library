/*
 * Copyright (C) 2013 Project-Phoenix
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

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.date.Weekday;
import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

/**
 * Wrapper class for a single group assigned to a lecture
 */
public class PhoenixLectureGroup implements PhoenixEntity {

    /** URI of the lecture group resource */
    public static final String WEB_RESOURCE_ROOT = "lectureGroup";

    public static final String WEB_RESOURCE_GET = "get";

    public static final String WEB_RESOURCE_DELETE = "delete";

    public static final String WEB_RESOURCE_UPDATE = "update";

    @Key
    private String name;
    private String description;
    private int maxMember;

    private Weekday submissionDeadlineWeekday;
    private LocalTime submissionDeadlineTime;

    private List<PhoenixDetails> details;

    @Key
    private PhoenixLecture lecture;

    /**
     * Constructor for json-transport
     */
    protected PhoenixLectureGroup() {

    }

    /**
     * Constructor for server
     * 
     * @param name
     *            The name of the group
     * @param maxMember
     *            The max possible member of the group
     * @param submissionDeadlineWeekday
     *            The weekday of the default submission.
     * @param submissionDeadlineTime
     *            The default deadline time for this group to assign submission.
     *            The day of the deadline is defined by
     *            submissionDeadlineWeekyday
     * @param details
     *            Detail information about the lecture group (room, time and how
     *            often)
     * @param lecture
     *            The lecture the group is assigned to
     * @deprecated Use
     *             {@link #PhoenixLectureGroup(String, String, int, Weekday, LocalTime, List, PhoenixLecture)}
     *             with its description
     */
    public PhoenixLectureGroup(String name, int maxMember, Weekday submissionDeadlineWeekday, LocalTime submissionDeadlineTime, List<PhoenixDetails> details, PhoenixLecture lecture) {
        this.name = name;
        this.maxMember = maxMember;
        this.submissionDeadlineWeekday = submissionDeadlineWeekday;
        this.submissionDeadlineTime = submissionDeadlineTime;
        this.details = new ArrayList<PhoenixDetails>(details);
        this.lecture = lecture;
    }

    /**
     * Constructor for server
     * 
     * @param name
     *            The name of the group
     * @param description
     *            The detailed description for this lecture group
     * @param maxMember
     *            The max possible member of the group
     * @param submissionDeadlineWeekday
     *            The weekday of the default submission.
     * @param submissionDeadlineTime
     *            The default deadline time for this group to assign submission.
     *            The day of the deadline is defined by
     *            submissionDeadlineWeekyday
     * @param details
     *            Detail information about the lecture group (room, time and how
     *            often)
     * @param lecture
     *            The lecture the group is assigned to
     */
    public PhoenixLectureGroup(String name, String description, int maxMember, Weekday submissionDeadlineWeekday, LocalTime submissionDeadlineTime, List<PhoenixDetails> details, PhoenixLecture lecture) {
        this.name = name;
        this.description = description;
        this.maxMember = maxMember;
        this.submissionDeadlineWeekday = submissionDeadlineWeekday;
        this.submissionDeadlineTime = submissionDeadlineTime;
        this.details = new ArrayList<PhoenixDetails>(details);
        this.lecture = lecture;
    }

    /**
     * Constructor for client
     * 
     * @param name
     *            The name of the group
     * @param maxMember
     *            The max possible member of the group
     * @param submissionDeadlineWeekday
     *            The weekday of the default submission.
     * @param submissionDeadlineTime
     *            The default deadline time for this group to assign submission.
     *            The day of the deadline is defined by
     *            submissionDeadlineWeekyday
     * @param details
     *            Detail information about the lecture group (room, time and how
     *            often)
     * @deprecated Use
     *             {@link #PhoenixLectureGroup(String, String, int, Weekday, LocalTime, List)}
     *             with the description
     */
    public PhoenixLectureGroup(String name, int maxMember, Weekday submissionDeadlineWeekday, LocalTime submissionDeadlineTime, List<PhoenixDetails> details) {
        this(name, "", maxMember, submissionDeadlineWeekday, submissionDeadlineTime, details);
    }

    /**
     * Constructor for client
     * 
     * @param name
     *            The name of the group
     * @param maxMember
     *            The max possible member of the group
     * @param submissionDeadlineWeekday
     *            The weekday of the default submission.
     * @param submissionDeadlineTime
     *            The default deadline time for this group to assign submission.
     *            The day of the deadline is defined by
     *            submissionDeadlineWeekyday
     * @param details
     *            Detail information about the lecture group (room, time and how
     *            often)
     */
    public PhoenixLectureGroup(String name, String description, int maxMember, Weekday submissionDeadlineWeekday, LocalTime submissionDeadlineTime, List<PhoenixDetails> details) {
        this.name = name;
        this.description = description;
        this.maxMember = maxMember;
        this.submissionDeadlineWeekday = submissionDeadlineWeekday;
        this.submissionDeadlineTime = submissionDeadlineTime;
        this.details = new ArrayList<PhoenixDetails>(details);
    }

    /**
     * @return The maximum member count of this group
     */
    public int getMaxMember() {
        return maxMember;
    }

    /**
     * @return The group name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description of this lecture group
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Returns the default deadline time for this group to assign
     *         submission. The day of the deadline is defined by
     *         {@link #getSubmissionDeadlineWeekday()}
     */
    public LocalTime getSubmissionDeadlineTime() {
        return submissionDeadlineTime;
    }

    /**
     * @return The weekday of the default submission.
     */
    public Weekday getSubmissionDeadlineWeekday() {
        return submissionDeadlineWeekday;
    }

    /**
     * @return Copy of detail information list about the lecture group (room,
     *         time and how often)
     */
    public List<PhoenixDetails> getDetails() {
        return new ArrayList<PhoenixDetails>(details);
    }

    /**
     * @return The assigned lecture of this group
     */
    public PhoenixLecture getLecture() {
        return lecture;
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixLectureGroup> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixLectureGroup
     */
    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixLectureGroup> </xmp> The
     * SelectEntity must match only one entity to delete, otherwise it will
     * return NOT OK
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The delete webresource for PhoenixLectureGroup
     */
    public static WebResource deleteResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_DELETE);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixLectureGroup> </xmp> The
     * UpdateEntity must match only one entity to delete, otherwise it will
     * return NOT OK
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The update webresource for PhoenixLectureGroup
     */
    public static WebResource updateResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_UPDATE);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

    @Override
    public String toString() {
        return "PhoenixLectureGroup [name=" + name + ", description=" + description + ", maxMember=" + maxMember + ", submissionDeadlineWeekday=" + submissionDeadlineWeekday + ", submissionDeadlineTime=" + submissionDeadlineTime + ", details=" + details + ", lecture=" + lecture + "]";
    }

}

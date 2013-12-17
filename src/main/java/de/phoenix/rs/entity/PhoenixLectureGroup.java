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

import javax.ws.rs.core.GenericEntity;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalTime;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class PhoenixLectureGroup {

    /** URI of the lecture group resource */
    public static final String WEB_RESOURCE_ROOT = "lectureGroup";

    /**
     * SubURI of the lecture group resource to create a lecture group and assign
     * it to a lecture
     * 
     * @param {@link PhoenixLectureGroup} Task to create
     */
    public static final String WEB_RESOURCE_CREATE = "create";

    /**
     * SubURI of the lecture group resource to get all lecture groups
     * 
     * @return List<{@link PhoenixLectureGroup}> All lecture groups
     */
    public static final String WEB_RESOURCE_GETALL = "getAll";

    private String name;
    private int maxMember;

    private int submissionDeadlineWeekyday;
    private LocalTime submissionDeadlineTime;

    private List<PhoenixDetails> details;

    private PhoenixLecture lecture;

    /**
     * Constructor for json-transport
     */
    protected PhoenixLectureGroup() {

    }

    /**
     * Constructor for client
     * 
     * @param name
     *            The name of the group
     * @param maxMember
     *            The max possible member of the group
     * @param submissionDeadlineWeekyday
     *            The weekday of the default submission. Use
     *            {@link DateTimeConstants} Monday-Sunday
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
    public PhoenixLectureGroup(String name, int maxMember, int submissionDeadlineWeekyday, LocalTime submissionDeadlineTime, List<PhoenixDetails> details, PhoenixLecture lecture) {
        this.name = name;
        this.maxMember = maxMember;
        this.submissionDeadlineWeekyday = submissionDeadlineWeekyday;
        this.submissionDeadlineTime = submissionDeadlineTime;
        this.details = new ArrayList<PhoenixDetails>(details);
        this.lecture = lecture;
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
     * @return Returns the default deadline time for this group to assign
     *         submission. The day of the deadline is defined by
     *         {@link #getSubmissionDeadlineWeekyday()}
     */
    public LocalTime getSubmissionDeadlineTime() {
        return submissionDeadlineTime;
    }

    /**
     * @return The weekday of the default submission. Use
     *         {@link DateTimeConstants} Monday-Sunday
     */
    public int getSubmissionDeadlineWeekyday() {
        return submissionDeadlineWeekyday;
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
     * Generic Type for {@link PhoenixLectureGroup}
     */
    private final static GenericType<List<PhoenixLectureGroup>> GENERIC_TYPE = new GenericType<List<PhoenixLectureGroup>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link PhoenixLectureGroup}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<PhoenixLectureGroup>> toSendableList(List<PhoenixLectureGroup> list) {
        return new GenericEntity<List<PhoenixLectureGroup>>(list, GENERIC_TYPE.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link PhoenixLectureGroup}
     */
    public final static List<PhoenixLectureGroup> fromSendableList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE);
    }

}

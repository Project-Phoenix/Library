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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

/**
 * Wrapper class to store information about a single lecture
 */
public class PhoenixLecture implements PhoenixEntity {

    /** URI of the lecture resource */
    public static final String WEB_RESOURCE_ROOT = "lecture";

    /**
     * SubURI of the lecture resource to create a lecture
     * 
     * @param {@link PhoenixLecture} Task to create
     */
    public static final String WEB_RESOURCE_CREATE = "create";

    /**
     * SubURI of the lecture resource to get search for lectures
     * 
     * @param SelectEntity
     *            <{@link PhoenixLecture}>
     * @return List<{@link PhoenixLecture}> All lectures matching the select
     *         values
     */
    public static final String WEB_RESOURCE_GET = "get";

    public static final String WEB_RESOURCE_UPDATE = "update";

    public static final String WEB_RESOURCE_DELETE = "delete";

    public static final String WEB_RESOURCE_ADD_GROUP = "addGroup";

    public static final String WEB_RESOURCE_ADD_DETAIL = "addDetail";

    public static final String WEB_RESOURCE_GET_ONLY_TITLES = "getTitles";

    @Key
    private String title;

    private String description;

    private List<PhoenixDetails> lectureDetails;

    /**
     * Empty constructor for json transport
     */
    protected PhoenixLecture() {

    }

    /**
     * Constructor for client/server
     * 
     * @param title
     *            The title of the lecture
     * @param details
     *            Details for this lectures (different dates of the lecture for
     *            example)
     * @deprecated Use {@link #PhoenixLecture(String, String, List)} with the
     *             description
     */
    public PhoenixLecture(String title, List<PhoenixDetails> details) {
        this(title, "", details);
    }

    /**
     * Constructor for client/server
     * 
     * @param title
     *            The title of the lecture
     * @param description
     *            The further description
     * @param details
     *            Details for this lectures (different dates of the lecture for
     *            example)
     */
    public PhoenixLecture(String title, String description, List<PhoenixDetails> details) {
        this.title = title;
        this.description = description;
        this.lectureDetails = new ArrayList<PhoenixDetails>(details);

    }

    /**
     * @return The description of this lecture
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The title of this lecture
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Copy of lecture details
     */
    public List<PhoenixDetails> getLectureDetails() {
        return new ArrayList<PhoenixDetails>(lectureDetails);
    }

    /**
     * Resource needs: <xmp> PhoenixLecture </xmp> Duplicate titles will return
     * an error
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The create webresource for PhoenixLecture
     */
    public static WebResource createResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CREATE);
    }

    /**
     * Resource needs: <xmp> UpdateEntity<PhoenixLecture> </xmp> The
     * LectureEntity must match only one entity to update, otherwise it will
     * return NOT OK
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The update webresource for PhoenixLecture
     */
    public static WebResource updateResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_UPDATE);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixLecture> </xmp> The
     * SelectEntity must match only one entity to delete, otherwise it will
     * return NOT OK
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The delete webresource for PhoenixLecture
     */
    public static WebResource deleteResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_DELETE);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixLecture> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixLecture
     */
    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    /**
     * Resource needs: <xmp> AddToEntity<PhoenixLecture, PhoenixGroup> </xmp>
     * The AddToEntity must match only one entity, otherwise it will return NOT
     * Ok
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The add group webresource for PhoenixLecture
     */
    public static WebResource addGroupResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_ADD_GROUP);
    }

    /**
     * Resource needs: <xmp> AddToEntity<PhoenixLecture, PhoenixDetails> </xmp>
     * The AddToEntity must match only one entity, otherwise it will return NOT
     * Ok
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The create webresource for PhoenixLecture
     */
    public static WebResource addDetailResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_ADD_DETAIL);
    }

    /**
     * Resource needs: <xmp> Nothing </xmp> The resource returns all available
     * 
     * @param client
     * @param baseURL
     * @return
     */
    public static WebResource getOnlyTitlesResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET_ONLY_TITLES);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

    @Override
    public String toString() {
        return "PhoenixLecture [title=" + title + ", description=" + description + ", lectureDetails=" + lectureDetails + "]";
    }

}

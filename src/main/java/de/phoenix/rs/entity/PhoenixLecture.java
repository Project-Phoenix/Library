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

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

import de.phoenix.rs.EntityUtil;
import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

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
     * SubURI of the lecture resource to get all lectures
     * 
     * @return List<{@link PhoenixLecture}> All lectures
     */
    @Deprecated
    public static final String WEB_RESOURCE_GETALL = "getAll";

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

    @Key
    private String title;

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
     */
    public PhoenixLecture(String title, List<PhoenixDetails> details) {
        this.title = title;
        this.lectureDetails = new ArrayList<PhoenixDetails>(details);
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
     * Generic Type for {@link PhoenixLecture}
     * 
     * @deprecated No longer necessary for sending and receiving lists
     */

    private final static GenericType<List<PhoenixLecture>> GENERIC_TYPE = new GenericType<List<PhoenixLecture>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link PhoenixLecture}
     * @return Generic Entity to send via JX-RS
     * @deprecated No longer necessary for sending and receiving lists
     */
    public final static GenericEntity<List<PhoenixLecture>> toSendableList(List<PhoenixLecture> list) {
        return new GenericEntity<List<PhoenixLecture>>(list, GENERIC_TYPE.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link PhoenixLecture}
     * @deprecated No longer necessary for sending and receiving lists. Use
     *             instead {@link EntityUtil#extractEntityList(ClientResponse)}
     */
    public final static List<PhoenixLecture> fromSendableList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE);
    }

}

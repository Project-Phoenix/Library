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
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.GenericEntity;

import org.joda.time.DateTime;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.EntityUtil;
import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

/**
 * Wrapper class for a TaskSheet to communicate with the phoenix RS service.
 */
public class PhoenixTaskSheet implements PhoenixEntity {

    /** URI of the task resource */
    public static final String WEB_RESOURCE_ROOT = "taskSheet";

    /**
     * SubURI of the tasksheet resource to create an TaskSheet
     * 
     * @param {@link PhoenixTaskSheet} TaskSheet to create
     * @deprecated
     */
    public static final String WEB_RESOURCE_CREATE = "create";

    /**
     * SubURI of the tasksheet resource to get all tasksheets
     * 
     * @return List<{@link PhoenixTaskSheet} All task sheets
     * @deprecated Use {@link #WEB_RESOURCE_GET} instead
     * */
    public static final String WEB_RESOURCE_GETALL = "getAll";

    public static final String WEB_RESOURCE_GET = "get";

    public static final String WEB_RESOURCE_CONNECT_TASKSHEET_WITH_TASK = "connectTasksheetWithTask";

    @Key
    private String title;

    private List<PhoenixTask> tasks;

    private DateTime creationDate;

    /**
     * Empty constructor for json transport
     */
    public PhoenixTaskSheet() {

    }

    /**
     * Constructor for server
     * 
     * @param tasks
     * @param creationDate
     * @deprecated
     */
    public PhoenixTaskSheet(List<PhoenixTask> tasks, DateTime creationDate) {
        this(tasks);
        this.creationDate = creationDate;
    }

    /**
     * Constructor for client
     * 
     * @param tasks
     *            Tasks concerning to the tasksheet
     * @deprecated
     */
    public PhoenixTaskSheet(List<PhoenixTask> tasks) {
        this.tasks = new ArrayList<PhoenixTask>(tasks);
    }

    /**
     * Constructor for client
     * 
     * @param Title
     *            The title of this task sheet
     */
    public PhoenixTaskSheet(String title) {
        this.title = title;
        this.tasks = Collections.emptyList();
    }

    /**
     * Constructor for server
     * 
     * @param Title
     *            The title of this task sheet
     * @param tasks
     *            The list of tasks attached to the task sheet
     * @param creationDate
     *            The creation date
     */
    public PhoenixTaskSheet(String title, List<PhoenixTask> tasks, DateTime creationDate) {
        this(title);
        this.tasks = new ArrayList<PhoenixTask>(tasks);
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    /**
     * @return Copy of task list
     */
    public List<PhoenixTask> getTasks() {
        return new ArrayList<PhoenixTask>(tasks);
    }

    /**
     * @return Creation date of this tasksheet
     */
    public DateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Generic Type for {@link PhoenixTaskSheet}
     * 
     * @deprecated No longer necessary for sending and receiving lists
     */
    private final static GenericType<List<PhoenixTaskSheet>> GENERIC_TYPE = new GenericType<List<PhoenixTaskSheet>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link PhoenixTaskSheet}
     * @return Generic Entity to send via JX-RS
     * @deprecated No longer necessary for sending and receiving lists
     */
    public final static GenericEntity<List<PhoenixTaskSheet>> toSendableList(List<PhoenixTaskSheet> list) {
        return new GenericEntity<List<PhoenixTaskSheet>>(list, GENERIC_TYPE.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link PhoenixTaskSheet}
     * @deprecated No longer necessary for sending and receiving lists. Use
     *             instead {@link EntityUtil#extractEntityList(ClientResponse)}
     */
    public final static List<PhoenixTaskSheet> fromSendableList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE);
    }

    public static WebResource createResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CREATE);
    }

    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    public static WebResource connectTaskSheetWithTaskResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CONNECT_TASKSHEET_WITH_TASK);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }
}

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

import org.joda.time.DateTime;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

/**
 * Wrapper class for a TaskSheet to communicate with the phoenix RS service.
 */
public class PhoenixTaskSheet implements PhoenixEntity {

    /** URI of the task resource */
    public static final String WEB_RESOURCE_ROOT = "taskSheet";

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
     * @param Title
     *            The title of this task sheet
     * @param tasks
     *            The list of tasks attached to the task sheet
     * @param creationDate
     *            The creation date
     */
    public PhoenixTaskSheet(String title, List<PhoenixTask> tasks, DateTime creationDate) {
        this.title = title;
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
     * Resource needs: <xmp> SelectEntity<PhoenixTaskSheet> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixTaskSheet
     */
    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    /**
     * Resource needs: <xmp> ConnectionEntity </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixTaskSheet
     */
    public static WebResource connectTaskSheetWithTaskResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CONNECT_TASKSHEET_WITH_TASK);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

    @Override
    public String toString() {
        return String.format("PhoenixTaskSheet={Title=%s;CreationDate=%s;Tasks=%s}", title, creationDate, tasks);
    }
}

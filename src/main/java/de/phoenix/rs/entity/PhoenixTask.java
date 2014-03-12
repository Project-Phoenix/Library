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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;
import de.phoenix.submission.DisallowedContent;

/**
 * Wrapper class for a Task to communicate with the phoenix RS service.
 */
public class PhoenixTask implements PhoenixEntity {

    /** URI of the task resource */
    public static final String WEB_RESOURCE_ROOT = "task";

    /** SubURI of the task resource to create an task */
    public static final String WEB_RESOURCE_CREATE = "create";

    /** SubURI of the task resource to update an task */
    public static final String WEB_RESOURCE_UPDATE = "update";

    /** SubURI of the task resource to delete an single task */
    public static final String WEB_RESOURCE_DELETE = "delete";

    /** SubURI of the task resource to get all tasks */
    public static final String WEB_RESOURCE_GETALL_TITLES = "getAllTitles";

    public static final String WEB_RESOURCE_GET = "get";

    public static final String WEB_RESOURCE_ADD_SUBMISSION = "submit";

    @Key
    private String title;
    private String description;

    private DisallowedContent disallowedContent;

    @JsonProperty
    private List<PhoenixAttachment> attachments;

    @JsonProperty
    private List<PhoenixText> answerPattern;

    /**
     * Empty constructor for json transport
     */
    protected PhoenixTask() {

    }

    /**
     * Constructor for the client/server and creates an task with a description
     * and answer pattern
     * 
     * @param title
     *            The title of the task, should be unique(firstly!)
     * @param attachments
     *            List of binary attachments which can be helpful for task
     *            solution
     * @param answerPattern
     *            Pattern to use to solve the task
     * @param description
     *            Descripe, what are the requirements of the tasks
     */
    public PhoenixTask(List<PhoenixAttachment> attachments, List<PhoenixText> answerPattern, String description, String title) {
        this.title = title;
        this.description = description;
        this.attachments = attachments;
        this.answerPattern = answerPattern;
    }

    /**
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    @JsonIgnore
    /**
     * @return Copy of list to the text pattern
     */
    public List<PhoenixText> getPattern() {
        return new ArrayList<PhoenixText>(answerPattern);
    }

    @JsonIgnore
    /**
     * @return Copy of list to the attachments
     */
    public List<PhoenixAttachment> getAttachments() {
        return new ArrayList<PhoenixAttachment>(attachments);
    }

    /**
     * Disallow certain content for this task. All submissions containing
     * anything of this content will rejected. This is useful to prevent
     * malicious code(like java.io access) or forbid Java implementation for
     * solutions
     * 
     * @param content
     *            The content to disallow
     */
    public void setDisallowedContent(DisallowedContent content) {
        this.disallowedContent = content;
    }

    /**
     * @return The disallowed content for this task - normally it is
     *         <code>null</code>
     */
    public DisallowedContent getDisallowedContent() {
        return disallowedContent;
    }

    /**
     * @return The title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Resource needs: <xmp> PhoenixTask </xmp> Duplicate titles will return an
     * error
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The create webresource for PhoenixTask
     */
    public static WebResource createResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_CREATE);
    }

    /**
     * Resource needs: <xmp> UpdateEntity<PhoenixTask> </xmp> The UpdateEntity
     * must match only one entity to update, otherwise it will return NOT OK
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The update webresource for PhoenixTask
     */
    public static WebResource updateResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_UPDATE);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixTask> </xmp> The SelectEntity
     * must match only one entity to delete, otherwise it will return NOT OK
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The delete webresource for PhoenixTask
     */
    public static WebResource deleteResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_DELETE);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixTask> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixTask
     */
    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    /**
     * Resource needs nothing
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The getAllTitles webresource for PhoenixTask
     */
    public static WebResource getAllTitlesResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GETALL_TITLES);
    }

    /**
     * Resource needs: <xmp> AddToEntity<PhoenixTask, PhoenixSubmission> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The submit webresource for PhoenixTask
     */
    public static WebResource submitResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_ADD_SUBMISSION);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

    @Override
    public String toString() {
        return String.format("PhoenixTask={Title=%s;Description=%s;Attachments=%s;Pattern=%s}", getTitle(), getDescription(), getAttachments(), getPattern());
    }

}

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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * Wrapper class for a Task to communicate with the phoenix RS service.
 */
public class PhoenixTask {

    /** URI of the task resource */
    public static final String WEB_RESOURCE_ROOT = "task";
    /**
     * SubURI of the task resource to create an task
     * 
     * @param PhoenixTask
     *            Task to create
     */
    public static final String WEB_RESOURCE_CREATE = "create";
    /**
     * SubURI of the task resource to update an task
     * 
     * @param Updateable
     *            <PhoenixTask, String> Task to update identified by the old
     *            title
     */
    public static final String WEB_RESOURCE_UPDATE = "update";
    /**
     * SubURI of the task resource to delete an single task
     * 
     * @param PhoenixTask
     *            Task to delete from the system
     */
    public static final String WEB_RESOURCE_DELETE = "delete";
    /** SubURI of the task resource to get all tasks */
    public static final String WEB_RESOURCE_GETALL = "getAll";
    /** SubURI of the task resource to get all tasks */
    public static final String WEB_RESOURCE_GETALL_TITLES = "getAllTitles";
    /**
     * SubURI of the task resource to look for a task by its title
     * 
     * @param String
     *            The title of the task
     */
    public static final String WEB_RESOURCE_GETBYTITLE = "getByTitle";

    private String title;
    private String description;

    private List<PhoenixAttachment> attachments;
    private List<PhoenixText> pattern;

    /**
     * Empty constructor for json transport
     */
    protected PhoenixTask() {

    }

    /**
     * Constructor for the server
     * 
     * @param title
     * @param attachments
     * @param pattern
     * @param description
     */
    public PhoenixTask(List<PhoenixAttachment> attachments, List<PhoenixText> pattern, String description, String title) {
        this.title = title;
        this.description = description;
        this.attachments = attachments;
        this.pattern = pattern;
    }

    /**
     * Constructor for the client
     * 
     * @param title
     *            The title of the task
     * @param description
     *            The task description
     * @param fileAttachments
     *            A list of binary files
     * @param filePattern
     *            A list of text based files
     * @throws IOException
     */
    public PhoenixTask(String title, String description, List<File> fileAttachments, List<File> filePattern) throws IOException {
        this.title = title;
        this.description = description;

        this.attachments = new ArrayList<PhoenixAttachment>(fileAttachments.size());
        for (File attachment : fileAttachments) {
            attachments.add(new PhoenixAttachment(attachment));
        }
        this.pattern = new ArrayList<PhoenixText>(filePattern.size());
        for (File patter : filePattern) {
            pattern.add(new PhoenixText(patter));
        }
    }

    /**
     * Constructor for the client
     * 
     * @param title
     *            The title of the task
     * @param texts
     *            A list of the answer pattern
     * @param description
     *            The task description
     * @param attachments
     *            A list of binary files
     * @throws IOException
     */
    public PhoenixTask(String title, List<PhoenixText> texts, String description, List<File> attachments) throws IOException {
        this.title = title;
        this.description = description;
        this.pattern = new ArrayList<PhoenixText>(texts);

        for (File file : attachments) {
            this.attachments.add(new PhoenixAttachment(file));
        }
    }

    /**
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Copy of list to the text pattern
     */
    public List<PhoenixText> getPattern() {
        return new ArrayList<PhoenixText>(pattern);
    }

    /**
     * @return Copy of list to the attachments
     */
    public List<PhoenixAttachment> getAttachments() {
        return new ArrayList<PhoenixAttachment>(attachments);
    }

    protected void setAttachments(List<PhoenixAttachment> attachments) {
        this.attachments = attachments;
    }

    protected void setPattern(List<PhoenixText> pattern) {
        this.pattern = pattern;
    }

    @JsonIgnore
    public int getAttachmentsSize() {
        return attachments.size();
    }

    @JsonIgnore
    public int getPatternSize() {
        return pattern.size();
    }

    /**
     * @return The title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Generic Type for {@link PhoenixTask}
     */
    private final static GenericType<List<PhoenixTask>> GENERIC_TYPE = new GenericType<List<PhoenixTask>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link PhoenixTask}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<PhoenixTask>> toSendableList(List<PhoenixTask> list) {
        return new GenericEntity<List<PhoenixTask>>(list, GENERIC_TYPE.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link PhoenixTask}
     */
    public final static List<PhoenixTask> fromSendableList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE);
    }
}

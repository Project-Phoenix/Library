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
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.entity.PhoenixSubmissionResult.SubmissionStatus;
import de.phoenix.rs.key.AddToEntity;
import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

/**
 * Submissions are submission for a single task containg texts and binary
 * attachments
 */
public class PhoenixSubmission implements PhoenixEntity {

    /** URI of the submission resource */
    public static final String WEB_RESOURCE_ROOT = "submission";
    /**
     * SubURI of the submission resource to submit a submission for a task
     * 
     * @param {@link PhoenixSubmission} Submission to submit
     * 
     * @return {@link PhoenixSubmissionResult} <br>
     *         Result containing the {@link SubmissionStatus} and the status
     *         text as a string
     * @deprecated Use {@link PhoenixTask#WEB_RESOURCE_ADD_SUBMISSION} instead
     */
    public static final String WEB_RESOURCE_SUBMIT = "submit";
    /**
     * SubURI of the submission resource to get all submissions by one task
     * 
     * @param {@link PhoenixTask} PhoenixTask holding submissions
     * 
     * @return List<{@link PhoenixSubmission}> <br>
     *         Submissions for the task
     * @deprecated Use the get resource
     */
    public static final String WEB_RESOURCE_GET_TASK_SUBMISSIONS = "getByTask";

    public static final String WEB_RESOURCE_GET = "get";

    public static final String WEB_RESOURCE_DELETE = "delete";

    @Key
    private DateTime date;

    @Key
    private int status;

    @Key
    private String statusText;

    private PhoenixTask task;

    private List<PhoenixAttachment> attachments;
    private List<PhoenixText> texts;

    /**
     * Empty constructor for json transport
     */
    public PhoenixSubmission() {
    }

    /**
     * Constructor for the client
     * 
     * @param task
     *            The task the submission is for
     * @param fileAttachments
     *            Binary files attached to this submission
     * @param fileTexts
     *            Text files attached to this submission
     * @throws IOException
     * @deprecated Use {@link #PhoenixSubmission(List, List)} in combination
     *             with {@link AddToEntity} and the
     *             {@link PhoenixTask#submitResource(Client, String)} resource
     *             to upload a submission. This will also send the complete task
     *             to the database, but only the title is neccessary for this!
     */

    public PhoenixSubmission(PhoenixTask task, List<File> fileAttachments, List<File> fileTexts) throws IOException {

        this.task = task;
        // Don't send the attachments via JSON
        this.task.setAttachments(Collections.<PhoenixAttachment> emptyList());
        this.task.setPattern(Collections.<PhoenixText> emptyList());

        this.attachments = new ArrayList<PhoenixAttachment>(fileAttachments.size());
        for (File attachment : fileAttachments) {
            attachments.add(new PhoenixAttachment(attachment, attachment.getName()));
        }

        this.texts = new ArrayList<PhoenixText>(fileTexts.size());
        for (File text : fileTexts) {
            texts.add(new PhoenixText(text, text.getName()));
        }
    }

    /**
     * Constructor for client
     * 
     * @param fileAttachments
     *            List of files as binary attachments
     * @param fileTexts
     *            List of files as text based attachments
     * @throws IOException
     */
    public PhoenixSubmission(List<File> fileAttachments, List<File> fileTexts) throws IOException {
        this.attachments = new ArrayList<PhoenixAttachment>(fileAttachments.size());
        for (File attachment : fileAttachments) {
            attachments.add(new PhoenixAttachment(attachment, attachment.getName()));
        }

        this.texts = new ArrayList<PhoenixText>(fileTexts.size());
        for (File text : fileTexts) {
            texts.add(new PhoenixText(text, text.getName()));
        }
    }

    /**
     * Constructor for the server
     * 
     * @param date
     * @param task
     * @param attachments
     * @param texts
     */
    public PhoenixSubmission(DateTime date, PhoenixTask task, int status, String statusText, List<PhoenixAttachment> attachments, List<PhoenixText> texts) {
        this.date = date;
        this.task = task;
        this.attachments = attachments;
        this.texts = texts;
        this.status = status;
        this.statusText = statusText;
    }

    /**
     * @return Copy of list to the texts
     */
    public List<PhoenixText> getTexts() {
        return new ArrayList<PhoenixText>(texts);
    }

    /**
     * @return Copy of list to the binary attachments
     */
    public List<PhoenixAttachment> getAttachments() {
        return new ArrayList<PhoenixAttachment>(attachments);
    }

    /**
     * @return The creation date
     */
    public DateTime getDate() {
        return date;
    }

    /**
     * @return The task the submission is for
     */
    public PhoenixTask getTask() {
        return task;
    }

    /**
     * @return The status of this submission.
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return The status text of the submission
     */
    public String getStatusText() {
        return statusText;
    }

    @Deprecated
    public static WebResource submitResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_SUBMIT);
    }

    @Deprecated
    public static WebResource getByTaskResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET_TASK_SUBMISSIONS);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixSubmission> </xmp>
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixSubmission
     */
    public static WebResource getResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_GET);
    }

    /**
     * Resource needs: <xmp> SelectEntity<PhoenixSubmission> </xmp> The
     * SelectEntity must match only one entity to delete, otherwise it will
     * return NOT OK
     * 
     * @param client
     *            Using for accessing the webresource
     * @param baseURL
     *            The baseURL of the REST webservice
     * @return The get webresource for PhoenixSubmission
     */
    public static WebResource deleteResource(Client client, String baseURL) {
        return base(client, baseURL).path(WEB_RESOURCE_DELETE);
    }

    private static WebResource base(Client client, String baseURL) {
        return client.resource(baseURL).path(WEB_RESOURCE_ROOT);
    }

    @Override
    public String toString() {
        return String.format("PhoenixSubmission={Date=%s;Status=%d;StatusText=%s;Task=%s;Attachments=%s;Texts=%s}", date, status, statusText, task, attachments, texts);
    }
}

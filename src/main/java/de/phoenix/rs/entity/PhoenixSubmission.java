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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.phoenix.rs.key.Key;
import de.phoenix.rs.key.PhoenixEntity;

/**
 * Submissions are submission for a single task containg texts and binary
 * attachments
 */
public class PhoenixSubmission implements PhoenixEntity {

    /** URI of the submission resource */
    public static final String WEB_RESOURCE_ROOT = "submission";

    public static final String WEB_RESOURCE_GET = "get";

    public static final String WEB_RESOURCE_DELETE = "delete";

    @Key
    private DateTime date;

    @Key
    private PhoenixSubmissionResult result;

    private PhoenixTask task;

    private List<PhoenixAttachment> attachments;
    private List<PhoenixText> texts;

    /**
     * Empty constructor for json transport
     */
    protected PhoenixSubmission() {
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
     * Constructor for client
     * 
     * @param attachments
     *            List of binary attachments
     * @param texts
     *            List of text encoded attachments, normally the source code
     */
    public PhoenixSubmission(Collection<PhoenixAttachment> attachments, Collection<PhoenixText> texts) {
        this.attachments = new ArrayList<PhoenixAttachment>(attachments);
        this.texts = new ArrayList<PhoenixText>(texts);
    }

    /**
     * Constructor for the server
     * 
     * @param date
     * @param task
     * @param attachments
     * @param texts
     */
    public PhoenixSubmission(DateTime date, PhoenixTask task, PhoenixSubmissionResult result, List<PhoenixAttachment> attachments, List<PhoenixText> texts) {
        this.date = date;
        this.task = task;
        this.attachments = attachments;
        this.texts = texts;
        this.result = result;
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
     * @return The result for the task. Is be <code>null</code> if submission is
     *         created via {@link #PhoenixSubmission(List, List)}
     */
    public PhoenixSubmissionResult getResult() {
        return result;
    }

    public Map<String, String> getPatternTaskMap() {
        return new HashMap<String, String>(); // TODO: IMPLEMENT ME
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
        return String.format("PhoenixSubmission={Date=%s;Result=%s;Task=%s;Attachments=%s;Texts=%s}", date, result, task, attachments, texts);
    }
}

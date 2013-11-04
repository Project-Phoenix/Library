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
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PhoenixSubmission implements Sendable {

    /** URI of the submission resource */
    public static final String WEB_RESOURCE_ROOT = "submission";
    /**
     * SubURI of the submission resource to submit a submission for a task
     * 
     * @param PhoenixSubmission
     *            Submission to submit
     */
    public static final String WEB_RESOURCE_SUBMIT = "submit";

    private Date date;

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
     */
    public PhoenixSubmission(PhoenixTask task, List<File> fileAttachments, List<File> fileTexts) throws IOException {

        this.task = task;
        // Don't send the attachments via JSON
        this.task.setAttachments(Collections.<PhoenixAttachment> emptyList());
        this.task.setPattern(Collections.<PhoenixText> emptyList());

        // TODO: Date creation only on the server!
        Date now = new Date();

        this.attachments = new ArrayList<PhoenixAttachment>(fileAttachments.size());
        for (File attachment : fileAttachments) {
            attachments.add(new PhoenixAttachment(attachment, now));
        }

        this.texts = new ArrayList<PhoenixText>(fileTexts.size());
        for (File text : fileTexts) {
            texts.add(new PhoenixText(text, now));
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
    public PhoenixSubmission(Date date, PhoenixTask task, List<PhoenixAttachment> attachments, List<PhoenixText> texts) {
        this.date = date;
        this.task = task;
        this.attachments = attachments;
        this.texts = texts;
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
    public Date getDate() {
        return date;
    }

    /**
     * @return The task the submission is for
     */
    public PhoenixTask getTask() {
        return task;
    }

    @JsonIgnore
    public int getAttachmentsSize() {
        return attachments.size();
    }

    @JsonIgnore
    public int getTextsSize() {
        return texts.size();
    }

    @Override
    public ClientResponse send(WebResource rs) {
        return rs.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, this);
    }

}

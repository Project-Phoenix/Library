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
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class PhoenixTask implements Sendable {

    private String describtion;

    private List<File> attachments;
    private List<File> pattern;

    public PhoenixTask(String description, List<File> attachments, List<File> pattern) {
        this.describtion = description;

        this.attachments = attachments;
        this.pattern = pattern;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public String getDescribtion() {
        return describtion;
    }

    public List<File> getPattern() {
        return pattern;
    }

    public ClientResponse send(WebResource rs) {
        FormDataMultiPart multiPart = new FormDataMultiPart();
        multiPart.bodyPart(new FormDataBodyPart("description", this.getDescribtion()));

        for (File attachment : attachments) {
            multiPart.bodyPart(new FileDataBodyPart("attachment", attachment, MediaType.APPLICATION_OCTET_STREAM_TYPE));
        }
        for (File patter : pattern) {
            multiPart.bodyPart(new FileDataBodyPart("pattern", patter, MediaType.APPLICATION_OCTET_STREAM_TYPE));
        }

        return rs.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class, multiPart);
    }
}

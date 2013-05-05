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

package de.phoenix.util;

import java.io.File;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

/**
 * Utility class for uploading files via jersey
 * 
 * @author Meldanor
 * 
 */
public class UploadHelper {

    /**
     * Upload a files to the web resource using FormDataMultiPart
     * 
     * @param resource
     *            Target webresource to upload the file
     * @param files
     *            The files to upload
     * @return The response from the webresource
     */
    public static ClientResponse uploadFile(WebResource resource, File... files) {

        // create file packet
        FormDataMultiPart multiPart = new FormDataMultiPart();
        // Add files to the packet
        for (File file : files) {
            multiPart.bodyPart(new FileDataBodyPart("file", file, MediaType.APPLICATION_OCTET_STREAM_TYPE));
        }

        // Send file to server
        return resource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class, multiPart);
    }
}

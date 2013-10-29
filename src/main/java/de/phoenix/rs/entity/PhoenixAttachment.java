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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Wrapper class for a Attachment to communicate with the phoenix RS service.
 */
public class PhoenixAttachment {

    private byte[] content;
    private Date creationDate;
    private String name;
    private String type;

    /**
     * Empty constructor for json transport
     */
    public PhoenixAttachment() {

    }

    /**
     * Constructor for the server
     * 
     * @param attachments
     * @param pattern
     * @param description
     */
    public PhoenixAttachment(byte[] content, Date creationDate, String name, String type) {
        this.content = content;
        this.creationDate = creationDate;
        this.name = name;
        this.type = type;
    }

    /**
     * Constructor for the client.
     * 
     * @param file
     *            The file containing binary data
     * @param creationDate
     *            The creation date of the file
     * @throws IOException
     */
    public PhoenixAttachment(File file, Date creationDate) throws IOException {
        read(file);

        this.creationDate = creationDate;

        this.name = file.getName();
        int fileSeperator = name.lastIndexOf('.');
        if (fileSeperator == -1) {
            this.type = "";
        } else {
            this.type = name.substring(fileSeperator + 1);
            name = name.substring(0, fileSeperator);
        }
    }

    private void read(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        this.content = new byte[(int) file.length()];
        fis.read(this.content);
        fis.close();
    }

    /**
     * Creates an temponary file containg the bytes of the attachment. The file
     * will deleted automatically when the VM terminates
     * 
     * @return A temponary file
     * @throws IOException
     */
    @JsonIgnore
    public File getFile() throws IOException {
        File f = File.createTempFile(name, type);
        f.deleteOnExit();
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(content);
        fout.close();
        return f;
    }

    /**
     * @return A stream on the bytes
     */
    @JsonIgnore
    public InputStream getStream() {
        return new ByteArrayInputStream(content);
    }

    /**
     * @return The name of the attachment
     */
    public String getName() {
        return name;
    }

    /**
     * @return The type ending of the attachment
     */
    public String getType() {
        return type;
    }

    /**
     * @return Attachments creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * WARNING! This will COPY the content! Depending on the size of the
     * attachments, this will take a while!
     * 
     * @return A copy of the content
     */
    public byte[] getContent() {
        return Arrays.copyOf(content, content.length);
    }

    /**
     * 
     * @return The size of the content
     */
    @JsonIgnore
    public int getContentSize() {
        return content.length;
    }
}

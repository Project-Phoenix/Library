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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Wrapper class for a Text to communicate with the phoenix RS service.
 */
public class PhoenixText {

    private String text;
    private Date creationDate;
    private String name;
    private String type;

    /**
     * Empty constructor for json transport
     */
    public PhoenixText() {

    }

    /**
     * Constructor for server side
     * 
     * @param text
     * @param creationDate
     * @param name
     * @param type
     */
    public PhoenixText(String text, Date creationDate, String name, String type) {
        this.text = text;
        this.creationDate = creationDate;
        this.name = name;
        this.type = type;
    }

    /**
     * Constructor for client side. Creationdate is not saved in unix files, so
     * you have to give it manually
     * 
     * @param file
     *            The file containing the text
     * @param creationDate
     *            Creation date of the file
     * @throws IOException
     */
    public PhoenixText(File file, Date creationDate) throws IOException {
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
        byte[] tmp = new byte[(int) file.length()];
        fis.read(tmp);
        fis.close();

        this.text = new String(tmp);
    }

    /**
     * @return The text of the file
     */
    public String getText() {
        return text;
    }

    /**
     * @return The name of the text
     */
    public String getName() {
        return name;
    }

    /**
     * @return The type ending of the text
     */
    public String getType() {
        return type;
    }

    /**
     * @return The creation date of the text
     */
    public Date getCreationDate() {
        return creationDate;
    }
}

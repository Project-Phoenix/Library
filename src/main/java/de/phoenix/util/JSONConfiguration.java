/*
 * Copyright (C) 2014 Project-Phoenix
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Implementation of {@link Configuration} using a jackson JSON based
 * serialization of the attributes. It saves the values to a json formatted file
 * every call.<br>
 * Also loads the values from a json formatted file if file is existing
 */
public class JSONConfiguration extends Configuration {

    @JsonIgnore
    private File configurationFile;

    private Map<String, Object> values;

    private final static ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        // Configure the JSON mapper
        JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
        JSON_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        JSON_MAPPER.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    }

    /**
     * Create a configuration using the filename as a path to the file. <br>
     * Will load values from the file if the file is existing. Otherwise, the
     * file will be created at the first setX call
     * 
     * @param fileName
     *            The name of the file
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public JSONConfiguration(String fileName) throws JsonParseException, JsonMappingException, IOException {
        this(new File(fileName));
    }

    /**
     * Create a configuration at the file location <br>
     * Will load values from the file if the file is existing. Otherwise, the
     * file will be created at the first setX call
     * 
     * @param file
     *            The file to locate the configuration
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public JSONConfiguration(File file) throws JsonParseException, JsonMappingException, IOException {
        this.configurationFile = file;

        if (configurationFile.exists()) {
            this.values = load(file);
        } else
            this.values = new HashMap<String, Object>();

    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> load(File file) throws JsonParseException, JsonMappingException, IOException {
        return JSON_MAPPER.readValue(file, Map.class);
    }

    @Override
    public void setObject(String name, Object value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setString(String name, String value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setChar(String name, char value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setBoolean(String name, boolean value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setByte(String name, byte value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setShort(String name, short value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setInt(String name, int value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setLong(String name, long value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setFloat(String name, float value) {
        this.values.put(name, value);
        update();
    }

    @Override
    public void setDouble(String name, double value) {
        this.values.put(name, value);
        update();
    }

    private void update() {
        try {
            JSON_MAPPER.writeValue(configurationFile, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObject(String name) {
        return this.values.get(name);
    }

    @Override
    public String getString(String name) {
        return (String) this.values.get(name);
    }

    @Override
    public Character getChar(String name) {
        return (Character) this.values.get(name);
    }

    @Override
    public Boolean getBoolean(String name) {
        return (Boolean) this.values.get(name);
    }

    @Override
    public Byte getByte(String name) {
        return (Byte) this.values.get(name);
    }

    @Override
    public Short getShort(String name) {
        return (Short) this.values.get(name);
    }

    @Override
    public Integer getInt(String name) {
        return (Integer) this.values.get(name);
    }

    @Override
    public Long getLong(String name) {
        return (Long) this.values.get(name);
    }

    @Override
    public Float getFloat(String name) {
        return (Float) this.values.get(name);
    }

    @Override
    public Double getDouble(String name) {
        return (Double) this.values.get(name);
    }

    @Override
    public boolean exists(String name) {
        return this.values.containsKey(name);
    }

}

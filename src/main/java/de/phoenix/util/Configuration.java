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

/**
 * Abstract class definition for a Configuration class to store values of
 * different types
 */
public abstract class Configuration {

    public Configuration() {
        super();
    }

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Double getDouble(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Float getFloat(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Long getLong(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Integer getInt(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Short getShort(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Byte getByte(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Boolean getBoolean(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Character getChar(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract String getString(String name);

    /**
     * @param name
     *            The attributes name
     * @return <code>null</code> if the name was not found. Otherwise the value
     *         of it
     */
    public abstract Object getObject(String name);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setDouble(String name, double value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setFloat(String name, float value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setLong(String name, long value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setInt(String name, int value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setShort(String name, short value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setByte(String name, byte value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setBoolean(String name, boolean value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setChar(String name, char value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setString(String name, String value);

    /**
     * Stores the value for the name.
     * 
     * @param name
     *            Unique name
     * @param value
     *            Value
     */
    public abstract void setObject(String name, Object value);

    /**
     * Check, if the attribute name was set
     * 
     * @param name
     *            The name of the attribute to check
     * @return <code>true</code> if, and only if, the attribute was set.
     *         Otherwise <code>false</code>
     */
    public abstract boolean exists(String name);

}
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

public abstract class Configuration {

    public Configuration() {
        super();
    }

    public abstract Double getDouble(String name);

    public abstract Float getFloat(String name);

    public abstract Long getLong(String name);

    public abstract Integer getInt(String name);

    public abstract Short getShort(String name);

    public abstract Byte getByte(String name);

    public abstract Boolean getBoolean(String name);

    public abstract Character getChar(String name);

    public abstract String getString(String name);

    public abstract Object getObject(String name);

    public abstract void setDouble(String name, double value);

    public abstract void setFloat(String name, float value);

    public abstract void setLong(String name, long value);

    public abstract void setInt(String name, int value);

    public abstract void setShort(String name, short value);

    public abstract void setByte(String name, byte value);

    public abstract void setBoolean(String name, boolean value);

    public abstract void setChar(String name, char value);

    public abstract void setString(String name, String value);

    public abstract void setObject(String name, Object value);

    public abstract boolean exists(String name);

}
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

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * Utility class to containg methods for transform list of basic classes to
 * generic entities, which can sent via JX-RS and via versa
 * 
 */
public class RSLists {

    private RSLists() {

    }

    // ++++++
    // Boolean
    // ++++++

    /**
     * Generic Type for {@link Boolean}
     */
    private final static GenericType<List<Boolean>> GENERIC_TYPE_BOOL = new GenericType<List<Boolean>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link Boolean}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<Boolean>> toSendableBooleanList(List<Boolean> list) {
        return new GenericEntity<List<Boolean>>(list, GENERIC_TYPE_BOOL.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link Boolean}
     */
    public final static List<Boolean> fromSendableBooleanList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_BOOL);
    }

    // ++++++
    // Byte
    // ++++++

    /**
     * Generic Type for {@link Byte}
     */
    private final static GenericType<List<Byte>> GENERIC_TYPE_BYTE = new GenericType<List<Byte>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link Byte}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<Byte>> toSendableByteList(List<Byte> list) {
        return new GenericEntity<List<Byte>>(list, GENERIC_TYPE_BYTE.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link Byte}
     */
    public final static List<Byte> fromSendableByteList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_BYTE);
    }

    // ++++++
    // Short
    // ++++++

    /**
     * Generic Type for {@link Short}
     */
    private final static GenericType<List<Short>> GENERIC_TYPE_SHORT = new GenericType<List<Short>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link Short}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<Short>> toSendableShortList(List<Short> list) {
        return new GenericEntity<List<Short>>(list, GENERIC_TYPE_SHORT.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link Short}
     */
    public final static List<Short> fromSendableShortList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_SHORT);
    }

    // ++++++
    // Integer
    // ++++++

    /**
     * Generic Type for {@link Integer}
     */
    private final static GenericType<List<Integer>> GENERIC_TYPE_INTEGER = new GenericType<List<Integer>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link Integer}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<Integer>> toSendableIntList(List<Integer> list) {
        return new GenericEntity<List<Integer>>(list, GENERIC_TYPE_INTEGER.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link Integer}
     */
    public final static List<Integer> fromSendableIntList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_INTEGER);
    }

    // ++++++
    // Long
    // ++++++

    /**
     * Generic Type for {@link Long}
     */
    private final static GenericType<List<Long>> GENERIC_TYPE_LONG = new GenericType<List<Long>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link Long}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<Long>> toSendableLongList(List<Long> list) {
        return new GenericEntity<List<Long>>(list, GENERIC_TYPE_LONG.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link Long}
     */
    public final static List<Long> fromSendableLongList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_LONG);
    }

    // ++++++
    // Float
    // ++++++

    /**
     * Generic Type for {@link Float}
     */
    private final static GenericType<List<Float>> GENERIC_TYPE_FLOAT = new GenericType<List<Float>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link Float}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<Float>> toSendableFloatList(List<Float> list) {
        return new GenericEntity<List<Float>>(list, GENERIC_TYPE_FLOAT.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link Float}
     */
    public final static List<Float> fromSendableFloatList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_FLOAT);
    }

    // ++++++
    // Double
    // ++++++

    /**
     * Generic Type for {@link Double}
     */
    private final static GenericType<List<Double>> GENERIC_TYPE_DOUBLE = new GenericType<List<Double>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link Double}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<Double>> toSendableDoubleList(List<Double> list) {
        return new GenericEntity<List<Double>>(list, GENERIC_TYPE_DOUBLE.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link Double}
     */
    public final static List<Double> fromSendableDoubleList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_DOUBLE);
    }

    // ++++++
    // Character
    // ++++++

    /**
     * Generic Type for {@link Character}
     */
    private final static GenericType<List<Character>> GENERIC_TYPE_CHAR = new GenericType<List<Character>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link Character}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<Character>> toSendableCharList(List<Character> list) {
        return new GenericEntity<List<Character>>(list, GENERIC_TYPE_CHAR.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link Character}
     */
    public final static List<Character> fromSendableCharList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_CHAR);
    }

    // ++++++
    // String
    // ++++++

    /**
     * Generic Type for {@link String}
     */
    private final static GenericType<List<String>> GENERIC_TYPE_STRING = new GenericType<List<String>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link String}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<String>> toSendableStringList(List<String> list) {
        return new GenericEntity<List<String>>(list, GENERIC_TYPE_STRING.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link String}
     */
    public final static List<String> fromSendableStringList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE_STRING);
    }
}

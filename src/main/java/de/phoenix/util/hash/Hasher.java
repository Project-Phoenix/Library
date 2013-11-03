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

package de.phoenix.util.hash;

import java.io.InputStream;
import java.nio.charset.Charset;

public interface Hasher {

    /**
     * Generate an hash of the bytes from the stream
     * 
     * @param binaryStream
     *            The stream containing bytes to hash
     * @return Hex coded string
     */
    public String generate(InputStream binaryStream);

    /**
     * Generate an hash of the bytes from the string using default charset
     * encoding. For other charset, see {@link #generate(String, Charset)}
     * 
     * @param string
     *            String to hash
     * @return Hex coded string
     */
    public String generate(String string);

    /**
     * Generate an hash of the bytes from the string using given charset
     * encoding. For default charset, see {@link #generate(String)}
     * 
     * @param string
     *            String to hash
     * @param charset
     *            The charset to decode the string
     * @return Hex coded string
     */
    public String generate(String string, Charset charset);

    /**
     * Generate an hash of the bytess
     * 
     * @param bytes
     *            The bytes on which the hash is based
     * @return Hex coded string
     */
    public String generate(byte[] bytes);

}
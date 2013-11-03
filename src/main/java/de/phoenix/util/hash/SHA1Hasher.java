/*
 * Copyright (C) 2013 Project-Phoenix
 * 
 * This file is part of WebService.
 * 
 * WebService is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * WebService is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with WebService.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.phoenix.util.hash;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class SHA1Hasher implements Hasher {

    private static final String ALGORITHM = "SHA1";

    private byte[] buffer;

    /**
     * Construct an Hasher with the SHA1 algorithm provided by java
     */
    public SHA1Hasher() {
        buffer = new byte[1024];
    }

    public String generate(InputStream binaryStream) {

        try {
            MessageDigest ms = MessageDigest.getInstance(ALGORITHM);
            for (int read = 0; (read = binaryStream.read(buffer)) != -1;) {
                ms.update(buffer, 0, read);
            }
            return Hex.encodeHexString(ms.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generate(String string) {
        return generate(string.getBytes());
    }

    public String generate(String string, Charset charset) {
        return generate(string.getBytes(charset));
    }

    public String generate(byte[] bytes) {

        try {
            MessageDigest ms = MessageDigest.getInstance(ALGORITHM);
            ms.update(bytes);
            return Hex.encodeHexString(ms.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}

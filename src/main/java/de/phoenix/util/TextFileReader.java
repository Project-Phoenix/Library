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
import java.io.FileNotFoundException;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TextFileReader {

    private final static Pattern FILE_ENDING_PATTERN = Pattern.compile("\\Z");

    public TextFileReader() {
    }

    public String read(File file) throws FileNotFoundException, CharacterCodingException {
        return read(file, Charset.defaultCharset());
    }

    public String read(File file, Charset charset) throws FileNotFoundException, CharacterCodingException {
        Scanner scanner = new Scanner(file, charset.toString());
        scanner.useDelimiter(FILE_ENDING_PATTERN);
        try {
            return scanner.next();
        } catch (NoSuchElementException e) {
            throw new CharacterCodingException();
        } finally {
            scanner.close();
        }
    }
}

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

package de.phoenix.filter;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.Charset;

import org.junit.BeforeClass;
import org.junit.Test;

import de.phoenix.util.TextFileReader;

public class FilterTest {

    private static String fileContent;
    private static String filteredContent;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        TextFileReader reader = new TextFileReader();
        fileContent = reader.read(new File("src/test/resources/taskDescription.html"), Charset.forName("UTF-8"));
        filteredContent = reader.read(new File("src/test/resources/taskDescriptionFiltered.txt"), Charset.forName("UTF-8"));
    }

    @Test
    public void test() {
        assertEquals(filteredContent, EduFilter.INSTANCE.filter(fileContent));
    }

}

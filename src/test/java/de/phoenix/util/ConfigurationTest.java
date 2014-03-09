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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ConfigurationTest {

    @AfterClass
    public static void afterClass() {
        File f = new File("test.json");
        f.delete();
    }

    @Test
    public void test() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {

        // The file does not exists -> new configuration is created and saves to
        // this file
        Configuration config = new JSONConfiguration("test.json");
        assertFalse(config.exists("name"));
        assertFalse(config.exists("age"));
        assertFalse(config.exists("height"));

        // Gimli is older then higher...
        config.setString("name", "Gimli");
        config.setInt("age", 193);
        config.setDouble("height", 132);
        

        assertEquals("Gimli", config.getString("name"));
        assertEquals(193, config.getInt("age").intValue());
        assertEquals(132, config.getDouble("height"), 0.0);
        
        assertNull(config.getInt("lol"));
        
        // Create new configuration -> the file now exists and the values are
        // loaded from it
        config = new JSONConfiguration("test.json");
        assertEquals(193, config.getInt("age").intValue());
        assertEquals("Gimli", config.getString("name"));
        assertEquals(132, config.getDouble("height"), 0.0);

    }

}

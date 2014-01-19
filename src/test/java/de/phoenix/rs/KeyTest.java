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

package de.phoenix.rs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.phoenix.junit.OrderedRunner;
import de.phoenix.junit.OrderedRunner.Order;
import de.phoenix.rs.entity.PhoenixAttachment;
import de.phoenix.rs.entity.PhoenixAutomaticTask;
import de.phoenix.rs.entity.PhoenixTask;
import de.phoenix.rs.entity.PhoenixText;
import de.phoenix.rs.key.KeyReader;
import de.phoenix.rs.key.SelectEntity;
import de.phoenix.rs.key.UpdateEntity;

@RunWith(OrderedRunner.class)
public class KeyTest {

    @Test(expected = NullPointerException.class)
    @Order(1)
    public void indexFields() {
        // Create the index
        KeyReader.createSelect(null);

    }

    @Test
    @Order(2)
    public void testSelect() {
        String title = "Title";
        PhoenixAutomaticTask task = new PhoenixAutomaticTask(new ArrayList<PhoenixAttachment>(), new ArrayList<PhoenixText>(), "Desc", title, "testBackend", new ArrayList<PhoenixText>());
        SelectEntity<PhoenixAutomaticTask> selectEntity = KeyReader.createSelect(task);
        String keyTitle = selectEntity.get("title");

        assertNotNull(keyTitle);
        assertEquals("Title is indifferent!", title, keyTitle);
    }

    @Test(expected = NullPointerException.class)
    @Order(3)
    public void testNullUpdate() {

        // Should retrieve a null pointer
        UpdateEntity<PhoenixTask> updateEntity = new UpdateEntity<PhoenixTask>(null);
        updateEntity.getNewObject();
    }

}

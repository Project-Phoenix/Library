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

package de.phoenix.rs.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Wrapper class for task which are controlled by the system automatically
 * 
 */
public class PhoenixAutomaticTask extends PhoenixTask {

    private String backend;
    private List<PhoenixText> tests;

    /**
     * Cosntructor for json transport
     */
    protected PhoenixAutomaticTask() {
    }

    /**
     * Constructs an automatic task
     * 
     * @param attachments
     *            See
     *            {@link PhoenixTask#PhoenixTask(List, List, String, String)}
     * @param answerPattern
     *            See
     *            {@link PhoenixTask#PhoenixTask(List, List, String, String)}
     * @param description
     *            See
     *            {@link PhoenixTask#PhoenixTask(List, List, String, String)}
     * @param title
     *            See
     *            {@link PhoenixTask#PhoenixTask(List, List, String, String)}
     * @param backend
     *            The name of the backend to controll this task
     * @param tests
     *            The tests the system has to run
     */
    public PhoenixAutomaticTask(List<PhoenixAttachment> attachments, List<PhoenixText> answerPattern, String description, String title, String backend, List<PhoenixText> tests) {
        super(attachments, answerPattern, description, title);
        this.backend = backend;
        this.tests = new ArrayList<PhoenixText>(tests);
    }

    /**
     * @return The name of the backend to control this task
     */
    public String getBackend() {
        return backend;
    }

    /**
     * @return Copy list of the the tests the system has to run
     */
    public List<PhoenixText> getTests() {
        return new ArrayList<PhoenixText>(tests);
    }

    @JsonIgnore
    public int getTestsSize() {
        return tests.size();
    }

}

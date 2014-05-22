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

package de.phoenix.rs.entity;

/**
 * Defines information about a single test for an {@link PhoenixAutomaticTask}
 */
public class PhoenixTaskTest {

    private long timeout;
    private PhoenixText content;

    /**
     * Empty constructor for json-transport
     */
    protected PhoenixTaskTest() {

    }

    /**
     * Constructor for client / server
     * 
     * @param content
     *            A text containing the test
     */
    public PhoenixTaskTest(PhoenixText content) {
        this.content = content;
        this.setTimeout(10000);
    }

    /**
     * Set the timeout for the task. Default is 10 seconds. After the timeout is
     * reached, the test is finished and return an error. This will normally
     * happen for infinite loops
     * 
     * @param seconds
     *            How long the test can run before exiting
     */
    public void setTimeout(long milliseconds) {
        this.timeout = milliseconds;
    }

    /**
     * @return The test text
     */
    public PhoenixText getContent() {
        return content;
    }

    /**
     * @return The timeout in milliseconds
     */
    public long getTimeout() {
        return timeout;
    }

    @Override
    public String toString() {
        return "PhoenixTaskTest [timeout=" + timeout + ", content=" + content + "]";
    }
}
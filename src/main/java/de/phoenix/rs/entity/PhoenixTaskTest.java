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

public class PhoenixTaskTest {

    private long timeout;
    private PhoenixText content;

    protected PhoenixTaskTest() {

    }

    public PhoenixTaskTest(PhoenixText content) {
        this.content = content;
    }

    public void setTimeout(long seconds) {
        this.timeout = seconds;
    }

    public PhoenixText getContent() {
        return content;
    }

    public long getTimeout() {
        return timeout;
    }

    @Override
    public String toString() {
        return "PhoenixTest={timeout=" + timeout + ";content=" + content + "}";
    }
}
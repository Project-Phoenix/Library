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

/**
 * Result of submission for a task
 */
public class PhoenixSubmissionResult {

    public enum SubmissionStatus {

        ERROR(), SUBMITTED(), COMPILED(), TEST_FAILED(), OK();
    }

    private SubmissionStatus status;
    private String statusText;

    /**
     * Constructor for json-transport
     */
    protected PhoenixSubmissionResult() {
    }

    /**
     * 
     * @param status
     *            The status of the result
     * @param statusText
     *            A detailled message for the status
     */
    public PhoenixSubmissionResult(SubmissionStatus status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }

    /**
     * @return The status of the submission result
     */
    public SubmissionStatus getStatus() {
        return status;
    }

    /**
     * @return The detailled status text of the result
     */
    public String getStatusText() {
        return statusText;
    }

    @Override
    public String toString() {
        return "PhoenixSubmissionResult={Status=" + status + ";StatusText=" + statusText + "}";
    }
}
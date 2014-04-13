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

        /**
         * Generic error - check the status text
         */
        ERROR(),
        /**
         * The submission was successfully submitted, but not compiled nor
         * tested
         */
        SUBMITTED(),
        /**
         * The submission was successfully submitted and compiled. The
         * {@link PhoenixAutomaticTask} had no tests.
         */
        COMPILED(),
        /**
         * The submission was successfully submitted, but not compiled nor
         * tests, because of missing classes or misspelled names. Check the
         * status text for further information
         */
        MISSING_FILES(),
        /**
         * The submission was successfully submitted and compiled, but failed
         * some tests. Check the status text for further information
         */
        TEST_FAILED(),
        /**
         * The submission was successfully submitted, compiled and passed all
         * tests. Nice one!
         */
        OK();
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
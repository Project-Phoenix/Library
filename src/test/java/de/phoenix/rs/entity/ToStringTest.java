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

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.junit.BeforeClass;
import org.junit.Test;

import de.phoenix.date.Weekday;
import de.phoenix.rs.entity.PhoenixSubmissionResult.SubmissionStatus;

public class ToStringTest {

    private static PhoenixText pText;
    private static PhoenixAttachment pAttachment;
    private static PhoenixDetails pDetail;

    private static PhoenixTask pTask;
    private static PhoenixTaskTest pTest;
    private static PhoenixAutomaticTask pAutoTask;
    private static PhoenixSubmissionResult pSubmissionResult;
    private static PhoenixSubmission pSubmission;
    private static PhoenixTaskSheet pTaskSheet;
    private static PhoenixLectureGroupTaskSheet pLectureGroupTaskSheet;

    private static PhoenixTaskSubmissionDates pTaskSubmissionDate;

    private static PhoenixLecture pLecture;
    private static PhoenixLectureGroup pLectureGroup;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        // Changing this date will crash the test
        DateTime dateTime = new DateTime(1992, 3, 24, 9, 0);
        LocalTime time = dateTime.toLocalTime();
        LocalDate date = dateTime.toLocalDate();

        pText = new PhoenixText("This is an example text", dateTime, "ExampleText", "txt");
        pAttachment = new PhoenixAttachment(new byte[]{1, 2, 3, 4, 5, 6}, dateTime, "ExampleAttachment", "dat");
        pDetail = new PhoenixDetails("G1-123", Weekday.MONDAY, time, time.plusHours(1), Period.weeks(1), date, date.plusDays(1));

        pLecture = new PhoenixLecture("TestLecture", Arrays.asList(pDetail));
        pLectureGroup = new PhoenixLectureGroup("TestGroup", 1, Weekday.MONDAY, time, Arrays.asList(pDetail), pLecture);

        pTask = new PhoenixTask(Arrays.asList(pAttachment), Arrays.asList(pText), "TestDescription", "TestTask");
        pTest = new PhoenixTaskTest(pText);
        pTest.setTimeout(10);
        pAutoTask = new PhoenixAutomaticTask(Arrays.asList(pAttachment), Arrays.asList(pText), "Test Auto Description", "TestAutoTask", "TestBackend", Arrays.asList(pTest));
        pTaskSheet = new PhoenixTaskSheet("TestTaskSheet", Arrays.asList(pTask, pAutoTask), DateTime.now());
        pLectureGroupTaskSheet = new PhoenixLectureGroupTaskSheet(dateTime.plusDays(1), dateTime, pTaskSheet, pLectureGroup);
        pTaskSubmissionDate = new PhoenixTaskSubmissionDates(dateTime.plusHours(1), dateTime, pLectureGroupTaskSheet, pTask);

        pSubmissionResult = new PhoenixSubmissionResult(SubmissionStatus.OK, "TestStatus");
        pSubmission = new PhoenixSubmission(dateTime, pTask, pSubmissionResult, Arrays.asList(pAttachment), Arrays.asList(pText));
    }

    @Test
    public void testToString() {

        assertNotNull(pText.toString());
        assertNotNull(pAttachment.toString());
        assertNotNull(pDetail.toString());
        assertNotNull(pLecture.toString());
        assertNotNull(pLectureGroup.toString());
        assertNotNull(pTask.toString());
        assertNotNull(pTest.toString());
        assertNotNull(pAutoTask.toString());
        assertNotNull(pTaskSheet.toString());
        assertNotNull(pLectureGroupTaskSheet.toString());
        assertNotNull(pTaskSubmissionDate.toString());
        assertNotNull(pSubmission.toString());
    }

}
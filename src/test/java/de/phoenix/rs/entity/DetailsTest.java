/*
 * Copyright (C) 2014 Project-Phoenix
 * 
 * This file is part of Library.
 * 
 * Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Library.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.phoenix.rs.entity;

import static org.junit.Assert.assertNull;

import java.security.InvalidParameterException;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.junit.Test;

import de.phoenix.date.Weekday;

public class DetailsTest {

    private static LocalTime NOW = LocalTime.now();
    private static LocalDate TODAY = LocalDate.now();

    @Test(expected = InvalidParameterException.class)
    public void startTimeAfterEndTime() {
        PhoenixDetails detail = new PhoenixDetails("testRoom", Weekday.MONDAY, NOW.plusHours(1), NOW, Period.weeks(1), TODAY, TODAY.plusDays(1));
        assertNull(detail);
    }

    @Test(expected = InvalidParameterException.class)
    public void startDateAfterEndDate() {
        PhoenixDetails detail = new PhoenixDetails("testRoom", Weekday.MONDAY, NOW, NOW.plusHours(1), Period.weeks(1), TODAY.plusDays(1), TODAY);
        assertNull(detail);
    }
}

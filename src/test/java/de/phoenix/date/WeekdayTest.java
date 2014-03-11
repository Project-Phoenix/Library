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

package de.phoenix.date;

import static org.junit.Assert.*;

import org.joda.time.DateTimeConstants;
import org.junit.Test;

public class WeekdayTest {

    @Test
    public void getByNameTest() {
        Weekday weekday = Weekday.forName("monday");
        assertEquals(Weekday.MONDAY, weekday);

        weekday = Weekday.forName("MONDAY");
        assertEquals(Weekday.MONDAY, weekday);

        weekday = Weekday.forName("mOnDaY");
        assertEquals(Weekday.MONDAY, weekday);
    }

    @Test
    public void getByIDTest() {
        Weekday weekday = Weekday.forID(DateTimeConstants.MONDAY);
        assertEquals(Weekday.MONDAY, weekday);

        weekday = Weekday.forID(DateTimeConstants.TUESDAY);
        assertEquals(Weekday.TUESDAY, weekday);

        weekday = Weekday.forID(DateTimeConstants.WEDNESDAY);
        assertEquals(Weekday.WEDNESDAY, weekday);

        weekday = Weekday.forID(DateTimeConstants.THURSDAY);
        assertEquals(Weekday.THURSDAY, weekday);

        weekday = Weekday.forID(DateTimeConstants.FRIDAY);
        assertEquals(Weekday.FRIDAY, weekday);

        weekday = Weekday.forID(DateTimeConstants.SATURDAY);
        assertEquals(Weekday.SATURDAY, weekday);

        weekday = Weekday.forID(DateTimeConstants.SUNDAY);
        assertEquals(Weekday.SUNDAY, weekday);
    }

}

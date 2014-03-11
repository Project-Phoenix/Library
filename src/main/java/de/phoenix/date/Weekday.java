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

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.joda.time.DateTimeConstants;

public enum Weekday {

    //@formatter:off
    MONDAY(DateTimeConstants.MONDAY),
    TUESDAY(DateTimeConstants.TUESDAY),
    WEDNESDAY(DateTimeConstants.WEDNESDAY),
    THURSDAY(DateTimeConstants.THURSDAY),
    FRIDAY(DateTimeConstants.FRIDAY),
    SATURDAY(DateTimeConstants.SATURDAY),
    SUNDAY(DateTimeConstants.SUNDAY);
    //@formatter:on

    private final int dateTimeConstant;

    private Weekday(int dateTimeConstant) {
        this.dateTimeConstant = dateTimeConstant;
    }

    public int getDateTimeConstant() {
        return dateTimeConstant;
    }

    private static Map<String, Weekday> mapByName;
    private static Map<Integer, Weekday> mapByID;

    static {
        Weekday[] values = values();
        mapByName = new HashMap<String, Weekday>();
        mapByID = new IdentityHashMap<Integer, Weekday>(values.length);

        for (int i = 0; i < values.length; i++) {
            Weekday weekday = values[i];
            mapByName.put(weekday.name().toLowerCase(), weekday);
            mapByID.put(weekday.getDateTimeConstant(), weekday);
        }
    }

    public static Weekday forName(String name) {
        return mapByName.get(name.toLowerCase());
    }

    public static Weekday forID(int id) {
        return mapByID.get(id);
    }
}

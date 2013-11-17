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

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class PhoenixDetails {

    private String room;
    private int weekDay;

    private LocalTime startTime;
    private LocalTime endTime;

    private Period inverval;

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for json-transport
     */
    protected PhoenixDetails() {

    }

    /**
     * Constructor for client/server
     * 
     * @param room
     *            The room where the event is happening
     * @param weekDay
     *            The day of the week, defined by the constants of
     *            {@link DateTimeConstants} (Monday-Sunday)
     * @param startTime
     *            The time when the events starts
     * @param interval
     *            The interval of the event. Will be replaced by an enum
     * @param startDate
     *            The start date of the event(before this date, the event does
     *            not exist!)
     * @param endDate
     *            The end date of the event(after this date, the event does not
     *            exist!)
     */
    public PhoenixDetails(String room, int weekDay, LocalTime startTime, LocalTime endTime, Period interval, LocalDate startDate, LocalDate endDate) {
        this.room = room;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.inverval = interval;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return The room where the event is happening
     */
    public String getRoom() {
        return room;
    }

    /**
     * @return The day of the week, defined by the constants of
     *         {@link DateTimeConstants} (Monday-Sunday)
     */
    public int getWeekDay() {
        return weekDay;
    }

    /**
     * @return The start time of the event
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @return The end time of the event
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @return The inverval of the event
     */
    public Period getInverval() {
        return inverval;
    }

    /**
     * @return The start date of the event(before this date, the event does not
     *         exist!)
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @return The end date of the event(after this date, the event does not
     *         exist!)
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Generic Type for {@link PhoenixDetails}
     */
    private final static GenericType<List<PhoenixDetails>> GENERIC_TYPE = new GenericType<List<PhoenixDetails>>() {
    };

    /**
     * Convert a list to an generic entity to send it via JX-RS
     * 
     * @param list
     *            List containing {@link PhoenixDetails}
     * @return Generic Entity to send via JX-RS
     */
    public final static GenericEntity<List<PhoenixDetails>> toSendableList(List<PhoenixDetails> list) {
        return new GenericEntity<List<PhoenixDetails>>(list, GENERIC_TYPE.getType());
    }

    /**
     * Extract the entity from the response and convert it to a normal list
     * 
     * @param response
     *            Response containg an list from JX-RS
     * @return List containg values as {@link PhoenixDetails}
     */
    public final static List<PhoenixDetails> fromSendableList(ClientResponse response) {
        return response.getEntity(GENERIC_TYPE);
    }
}
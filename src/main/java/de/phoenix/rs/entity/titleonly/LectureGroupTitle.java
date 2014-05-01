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

package de.phoenix.rs.entity.titleonly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LectureGroupTitle implements Iterable<TaskSheetTitle> {

    private String title;
    private List<TaskSheetTitle> taskSheetTitles;

    protected LectureGroupTitle() {
        // Empty constructor for JSON transport
    }

    public LectureGroupTitle(String title, List<TaskSheetTitle> taskSheetTitles) {
        this.title = title;
        this.taskSheetTitles = new ArrayList<TaskSheetTitle>(taskSheetTitles);
    }

    public String getTitle() {
        return title;
    }

    public List<TaskSheetTitle> getTaskSheetTitles() {
        return new ArrayList<TaskSheetTitle>(taskSheetTitles);
    }

    @Override
    public Iterator<TaskSheetTitle> iterator() {
        return this.taskSheetTitles.iterator();
    }

    @Override
    public String toString() {
        return "LectureGroupTitle [title=" + title + ", taskSheetTitles=" + taskSheetTitles + "]";
    }
}

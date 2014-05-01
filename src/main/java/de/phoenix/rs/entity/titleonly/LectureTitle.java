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

public class LectureTitle implements Iterable<LectureGroupTitle> {

    private String title;
    private List<LectureGroupTitle> groupTitles;

    protected LectureTitle() {
        // Empty constructor for JSON transport
    }

    public LectureTitle(String title, List<LectureGroupTitle> groupTitles) {
        this.title = title;
        this.groupTitles = new ArrayList<LectureGroupTitle>(groupTitles);
    }

    public String getTitle() {
        return title;
    }

    public List<LectureGroupTitle> getGroupTitles() {
        return new ArrayList<LectureGroupTitle>(groupTitles);
    }

    @Override
    public Iterator<LectureGroupTitle> iterator() {
        return groupTitles.iterator();
    }

    @Override
    public String toString() {
        return "LectureTitle [title=" + title + ", groupTitles=" + groupTitles + "]";
    }

}

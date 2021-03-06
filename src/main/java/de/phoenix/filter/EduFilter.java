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

package de.phoenix.filter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Text filter based on jsoup HTML parsing to remove all HTML tags and provide a
 * plain text.
 * 
 */
public class EduFilter implements TextFilter {

    /**
     * Singleton of the filter implementation
     */
    public static final TextFilter INSTANCE = new EduFilter();

    private EduFilter() {

    }

    @Override
    public String filter(String original) {
        Document doc = Jsoup.parse(original);
        return doc.text();
    }

}

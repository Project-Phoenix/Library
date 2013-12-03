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

package de.phoenix.parser;

import java.util.regex.Pattern;

/**
 * Remove all HTML Tags
 * 
 */
public class HTMLFilter implements TextFilter {

    private final static TextFilter INSTANCE = new HTMLFilter();

    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>");

    private HTMLFilter() {

    }

    @Override
    public String filter(String original) {
        return HTML_TAG_PATTERN.matcher(original).replaceAll("");
    }

    public static TextFilter instance() {
        return INSTANCE;
    }
}

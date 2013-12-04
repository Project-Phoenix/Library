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

import java.util.regex.Pattern;

/**
 * Filter for removing format information like HTML tags or &nbsp; from the
 * original text. <br />
 * Replace all <\p> with a line break
 */
public class EduFilter implements TextFilter {

    // Remove all HTML tags
    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>");

    // Remove all secured spaces
    private static final Pattern SPACE_PATTERN = Pattern.compile("&nbsp;");

    // Change all section tags with a line separator
    private static final Pattern BREAK_PATTERN = Pattern.compile("<\\s*p>");

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public String filter(String original) {

        String result = original;

        result = BREAK_PATTERN.matcher(result).replaceAll(LINE_SEPARATOR);
        result = delete(result, HTML_TAG_PATTERN);
        result = delete(result, SPACE_PATTERN);

        return result;
    }

    private String delete(String text, Pattern p) {
        return p.matcher(text).replaceAll("");
    }

}

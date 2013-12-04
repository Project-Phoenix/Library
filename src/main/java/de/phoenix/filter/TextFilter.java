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

import de.phoenix.rs.entity.PhoenixText;

/**
 * 
 * Filter used for texts in the {@link PhoenixText}. They will remove all
 * filtered words from the original text, but not alter the original
 */
public interface TextFilter {

    /**
     * Apply the filter on the original String and return a filtered original (but doesn not 
     * @param original
     * @return
     */
    public String filter(String original);
}

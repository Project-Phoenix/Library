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

package de.phoenix.submission;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DisallowedContent {

    @JsonProperty
    private List<String> disallowedContent;

    public DisallowedContent() {
        this.disallowedContent = new ArrayList<String>();
    }

    public DisallowedContent disallow(String content) {
        this.disallowedContent.add(content);
        return this;
    }
    
    @JsonIgnore
    public List<String> getDisallowedContent() {
        return new ArrayList<String>(disallowedContent);
    }

    @Override
    public String toString() {
        return "DisallowedContent={" + disallowedContent + "}";
    }
}

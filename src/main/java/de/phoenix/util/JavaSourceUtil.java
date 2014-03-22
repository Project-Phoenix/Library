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

package de.phoenix.util;

public class JavaSourceUtil {

    /**
     * Extract the class name of the Java source code
     * 
     * @param javaSource
     *            String containing Java source code of a class
     * @return
     * @throws IllegalArgumentException
     *             When the source code does not contains a class or is invalid
     */
    public static String extractClassName(String javaSource) {
        // Remove all tabs
        javaSource = javaSource.replaceAll("\t", "    ");

        int classIndex = javaSource.indexOf("class ");
        if (classIndex == -1)
            throw new IllegalArgumentException("Is not a class!");
        int bracketIndex = javaSource.indexOf('{', classIndex);
        if (bracketIndex == -1)
            throw new IllegalArgumentException("{ not after class");

        classIndex += "class ".length();
        return javaSource.substring(classIndex, bracketIndex).trim();
    }

}

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

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaSourceUtilTest {

    @Test
    public void easyTest() {

        //@formatter:off
        String sourceCode = ""
                + "public class RunnerTest {"
                + "     "
                + "     @Test"
                + "     public void bla() {"
                + "     }"
                + ""
                + "}";
        //@formatter:on
        assertEquals("RunnerTest", JavaSourceUtil.extractClassName(sourceCode));
    }

    @Test
    public void tabTest() {

        //@formatter:off
        String sourceCode = ""
                + "public class\tRunnerTest {"
                + "     "
                + "     @Test"
                + "     public void bla() {"
                + "     }"
                + ""
                + "}";
        //@formatter:on
        assertEquals("RunnerTest", JavaSourceUtil.extractClassName(sourceCode));
    }

    @Test()
    public void noClassTest() {

        //@formatter:off
        String sourceCode = ""
                + "public interface RunnerTest {"
                + "     "
                + "     @Test"
                + "     public void bla();"
                + ""
                + "}";
        //@formatter:on
        try {
            JavaSourceUtil.extractClassName(sourceCode);
            fail("no exception thrown!");
        } catch (Exception e) {
        }

        //@formatter:off
        sourceCode = ""
                + "public enum RunnerTest {"
                + "     "
                + "     @Test"
                + "     public void bla();"
                + ""
                + "}";
        //@formatter:on        
        try {
            JavaSourceUtil.extractClassName(sourceCode);
            fail("no exception thrown!");
        } catch (Exception e) {
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void noClassNameTest() {

        //@formatter:off
        String sourceCode = ""
                + "public class {"
                + "     "
                + "     @Test"
                + "     public void bla();"
                + ""
                + "}";
        //@formatter:on
        // Throw exception
        JavaSourceUtil.extractClassName(sourceCode);
    }
}

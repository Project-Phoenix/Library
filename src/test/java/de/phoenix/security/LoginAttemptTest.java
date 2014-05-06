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

package de.phoenix.security;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import de.phoenix.security.login.LoginAttempt;
import de.phoenix.security.login.PlainLoginAttempt;

public class LoginAttemptTest {

    @Test
    public void samePasswords() {
        LoginAttempt log1 = new LoginAttempt("test", DigestUtils.sha512Hex("1234"));
        LoginAttempt log2 = new PlainLoginAttempt("test", "1234");
        assertEquals(log1.getPassword(), log2.getPassword());
        assertEquals(log1.getUsername(), log2.getUsername());

    }

}

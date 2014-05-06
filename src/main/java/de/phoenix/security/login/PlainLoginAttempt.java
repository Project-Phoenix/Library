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

package de.phoenix.security.login;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Storing the username and its password. The password in this constructor must
 * be in plain text. If you have already hashed the password, use
 * {@link LoginAttempt}.
 */
public class PlainLoginAttempt extends LoginAttempt {

    protected PlainLoginAttempt() {
        // Empty constructor for JSON transport
    }

    /**
     * Combines the username and its password.
     * 
     * @param username
     *            The username
     * @param password
     *            The password in plain text ( will be hashed in the constructor
     *            call)
     */
    public PlainLoginAttempt(String username, String password) {
        super(username, DigestUtils.sha512Hex(password));
    }
}

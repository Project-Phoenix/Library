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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Storing the username and the already SHA512 hashed password. If you want to
 * store a plain password and convert it to this, use the class
 * {@link PlainLoginAttempt}. <br>
 * This can be useful, if the password is stored in a configuration file
 */
public class LoginAttempt {

    private String username;
    @JsonProperty
    private String hashedPassword;

    protected LoginAttempt() {
        // Empty constructor for JSON transport
    }

    /**
     * Combines the username and a already hashed password with SHA512
     * 
     * @param username
     *            The username
     * @param hashedPassword
     *            The password, already hashed
     */
    public LoginAttempt(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    @JsonIgnore
    public String getPassword() {
        return hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "LoginAttempt [username=" + username + ", password=" + hashedPassword + "]";
    }
}

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

package de.phoenix.security.user;

import org.apache.commons.codec.digest.DigestUtils;

import de.phoenix.rs.key.ConnectionEntity;

public class CreatePhoenixUser extends ConnectionEntity {

    protected CreatePhoenixUser() {
        // Empty constructor for JSON transport
    }

    public CreatePhoenixUser(String password, PhoenixUser user) {
        addAttribute("password", DigestUtils.sha512Hex(password));
        addAttribute("user", user);
    }
}

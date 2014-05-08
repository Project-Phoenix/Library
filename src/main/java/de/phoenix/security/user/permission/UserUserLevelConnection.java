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

package de.phoenix.security.user.permission;

import de.phoenix.rs.key.ConnectionEntity;
import de.phoenix.rs.key.SelectEntity;
import de.phoenix.security.user.PhoenixUser;

public class UserUserLevelConnection extends ConnectionEntity {

    public UserUserLevelConnection(SelectEntity<PhoenixUser> userSelector, SelectEntity<PhoenixUserLevel> userLevelSelector) {
        addSelectEntity(PhoenixUser.class, userSelector);
        addSelectEntity(PhoenixUserLevel.class, userLevelSelector);
    }

}

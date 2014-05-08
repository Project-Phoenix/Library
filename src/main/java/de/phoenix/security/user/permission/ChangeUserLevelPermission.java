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

import java.util.List;

import de.phoenix.rs.key.ConnectionEntity;
import de.phoenix.rs.key.SelectEntity;

public abstract class ChangeUserLevelPermission extends ConnectionEntity {

    protected ChangeUserLevelPermission() {

    }

    public ChangeUserLevelPermission(SelectEntity<PhoenixUserLevel> userLevelSelector, List<String> permissionNodes, boolean add) {
        this.addSelectEntity(PhoenixUserLevel.class, userLevelSelector);
        this.addAttribute("permissionNodes", permissionNodes);
        this.addAttribute("add", add);
    }

    public static class AddPermissionsToUserLevel extends ChangeUserLevelPermission {

        protected AddPermissionsToUserLevel() {

        }

        public AddPermissionsToUserLevel(SelectEntity<PhoenixUserLevel> userLevelSelector, List<String> permissionNodes) {
            super(userLevelSelector, permissionNodes, true);
        }
    }

    public static class RemovePermissionsFromUserLevel extends ChangeUserLevelPermission {

        protected RemovePermissionsFromUserLevel() {

        }

        public RemovePermissionsFromUserLevel(SelectEntity<PhoenixUserLevel> userLevelSelector, List<String> permissionNodes) {
            super(userLevelSelector, permissionNodes, false);
        }
    }

}

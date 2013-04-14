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

package de.phoenix.security;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A token which expire after a certain time. It is unique and binded to own
 * user per time. It is used to grant access to restricted areas and
 * authentificate the user with his username or his password
 */
@XmlRootElement(name = "token")
public class Token {

    @XmlElement
    private final String ID;
    @XmlElement
    private final String owner;
    @XmlElement
    private final long expiration;

    public Token() {
        this.ID = null;
        this.owner = null;
        this.expiration = 0L;
    }

    public Token(final String ID, final String owner, final long expireDuration) {
        this.ID = ID;
        this.owner = owner;
        this.expiration = System.currentTimeMillis() + expireDuration;
    }

    /**
     * A unique, hard to guess order of chars. Generated by
     * {@link java.util.UUID#randomUUID()}
     * 
     * @return A unique and random string
     */
    public String getID() {
        return ID;
    }

    /**
     * @return The owner of the token
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @return Is the token valid or expired?
     */
    public boolean isExpired() {
        return expiration <= System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.format("Token={ID=%s;Owner=%s;Expiration=%d", ID, owner, expiration);
    }

}

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

package de.phoenix.rs.entity;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PhoenixSingleFileSubmission extends PhoenixSubmission {

    public PhoenixSingleFileSubmission(Collection<PhoenixAttachment> attachments, PhoenixText text) {
        super(attachments, Arrays.asList(text));
    }

    public PhoenixSingleFileSubmission(List<File> fileAttachments, File textFile) throws IOException {
        super(fileAttachments, Arrays.asList(textFile));
    }

}

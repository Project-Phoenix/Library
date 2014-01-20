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

package de.phoenix.rs.key;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.phoenix.rs.entity.PhoenixAttachment;
import de.phoenix.rs.entity.PhoenixAutomaticTask;
import de.phoenix.rs.entity.PhoenixDetails;
import de.phoenix.rs.entity.PhoenixLecture;
import de.phoenix.rs.entity.PhoenixLectureGroup;
import de.phoenix.rs.entity.PhoenixLectureGroupTaskSheet;
import de.phoenix.rs.entity.PhoenixSubmission;
import de.phoenix.rs.entity.PhoenixSubmissionResult;
import de.phoenix.rs.entity.PhoenixTask;
import de.phoenix.rs.entity.PhoenixTaskSheet;
import de.phoenix.rs.entity.PhoenixTaskSubmissionDates;
import de.phoenix.rs.entity.PhoenixText;

/**
 * Reader of {@link Key} fields from {@link PhoenixEntity} to create
 * {@link SelectEntity} and {@link UpdateEntity} easily.
 */
public class KeyReader {

    private KeyReader() {

    }

    /**
     * Pointer on all relevant methods for an class
     */
    private static Map<String, KeyFields> map;

    static {
        map = new HashMap<String, KeyFields>();

        indexEntities(map);
    }

    /**
     * Generate key fields for all {@link PhoenixEntity} classes
     * 
     * @param map
     *            The map to store them
     */
    private static void indexEntities(Map<String, KeyFields> map) {

        // Expand this list when a new entity is added
        indexEntity(PhoenixAttachment.class, map);
        indexEntity(PhoenixAutomaticTask.class, map);
        indexEntity(PhoenixDetails.class, map);
        indexEntity(PhoenixLecture.class, map);
        indexEntity(PhoenixLectureGroup.class, map);
        indexEntity(PhoenixLectureGroupTaskSheet.class, map);
        indexEntity(PhoenixSubmission.class, map);
        indexEntity(PhoenixSubmissionResult.class, map);
        indexEntity(PhoenixTask.class, map);
        indexEntity(PhoenixTaskSheet.class, map);
        indexEntity(PhoenixTaskSubmissionDates.class, map);
        indexEntity(PhoenixText.class, map);
    }

    /**
     * Generate an KeyFields object for this class and all its super classes
     * 
     * @param clazz
     *            The class with the Key fields
     * @param map
     *            Map to store the key entity
     */
    private static void indexEntity(Class<?> clazz, Map<String, KeyFields> map) {
        KeyFields entity = new KeyFields();

        String originClassName = clazz.getName();

        // Go higher in the hierachie as long the class has a super class
        do {
            // Index fields of this class and possible super classes!
            indexEntity(clazz, entity);
            clazz = clazz.getSuperclass();
        } while (clazz != null);

        map.put(originClassName, entity);
    }

    /**
     * Get all fields with the {@link Key} Annotation of the clazz and index
     * them by their name
     * 
     * @param clazz
     *            The class containg the fields
     * @param entity
     *            The entity to store the index fields
     */
    private static void indexEntity(Class<?> clazz, KeyFields entity) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            if (field.isAnnotationPresent(Key.class)) {
                field.setAccessible(true);
                entity.addField(field);
            }
        }
    }

    /**
     * Creates a {@link SelectEntity} object from the {@link PhoenixEntity} by
     * extracting all values from class attributes annotated with the
     * {@link Key} Annotation. <br>
     * Will create the same result as this example: <br>
     * <br>
     * <code>
     * SelectEntity<T> entity = new SelectEntity<T>(); <br>
     * entity.addKey(KEY_NAME, KEY_VALUE); <br>
     * entity.addKey(ANOTHER_KEY_NAME, ANOTHER_KEY_VALUE); <br>
     * ... 
     * </code>
     * 
     * @param entity
     *            Non-<code>null</code> entity to extract the key values
     * @param <T>
     *            Instance of {@link PhoenixEntity}
     * @return SelectEntity with filled keys from the entity
     */
    public static <T extends PhoenixEntity> SelectEntity<T> createSelect(T entity) {
        if (entity == null) {
            throw new NullPointerException("The element was null!");
        }

        KeyFields keyedEntity = map.get(entity.getClass().getName());
        if (keyedEntity == null) {
            throw new InvalidParameterException("The class " + entity.getClass() + " was not indexed! Maybe missing to add them to add to 'KeyReader.indexEntities()'?");
        }

        Map<String, Field> fields = keyedEntity.getFields();

        SelectEntity<T> selectEntity = new SelectEntity<T>();

        for (Entry<String, Field> entry : fields.entrySet()) {
            try {
                selectEntity.addKey(entry.getKey(), entry.getValue().get(entity));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        return selectEntity;
    }

    /**
     * Creates a {@link UpdateEntity} object from the {@link PhoenixEntity} by
     * extracting all values from class attributes annotated with the
     * {@link Key} Annotation. <br>
     * Will create the same result as this example: <br>
     * <br>
     * <code>
     * UpdateEntity<T> entity = new UpdateEntity<T>(newObject); <br>
     * entity.addKey(KEY_NAME, KEY_VALUE); <br>
     * entity.addKey(ANOTHER_KEY_NAME, ANOTHER_KEY_VALUE); <br>
     * ... 
     * </code>
     * 
     * @param oldElement
     *            Non-<code>null</code> entity to extract the key values. Its
     *            values will be used to find the current entity
     * @param newElement
     *            Non-<code>null</code> entity to extract the key values. Its
     *            values will replace the oldElement
     * @param <T>
     *            Instance of {@link PhoenixEntity}
     * @return UpdateEntity with filled keys from the entity
     * 
     */
    public static <T extends PhoenixEntity> UpdateEntity<T> createUpdate(T oldElement, T newElement) {
        return new UpdateEntity<T>(newElement, createSelect(oldElement));
    }

    /**
     * Creates a {@link AddToEntity} object from the {@link PhoenixEntity} by
     * extracting all values from class attributes annotated with the
     * {@link Key} Annotation. <br>
     * Will create the same result as this example:
     * <p>
     * 
     * <code>
     * AddToEntity<T,E> entity = new AddToEntity<T,E>(entity, attachedEntities); <br>
     * entity.addKey(KEY_NAME, KEY_VALUE); <br>
     * entity.addKey(ANOTHER_KEY_NAME, ANOTHER_KEY_VALUE); <br>
     * ... 
     * </code>
     * 
     * @param entity
     *            The phoenixEntity to add new other phoenix entitites to.
     * @param attachedEntities
     *            List of new phoenix entities to add to the entity
     * @return {@link AddToEntity} with filled keys from the entity
     */
    public static <T extends PhoenixEntity, E extends PhoenixEntity> AddToEntity<T, E> createAddTo(T entity, List<E> attachedEntities) {
        return new AddToEntity<T, E>(createSelect(entity), attachedEntities);
    }

    /**
     * Creates a {@link AddToEntity} object from the {@link PhoenixEntity} by
     * extracting all values from class attributes annotated with the
     * {@link Key} Annotation. <br>
     * Will create the same result as this example:
     * <p>
     * 
     * <code>
     * AddToEntity<T,E> entity = new AddToEntity<T,E>(entity, attachedEntities); <br>
     * entity.addKey(KEY_NAME, KEY_VALUE); <br>
     * entity.addKey(ANOTHER_KEY_NAME, ANOTHER_KEY_VALUE); <br>
     * ... 
     * </code>
     * 
     * @param entity
     *            The phoenixEntity to add new other phoenix entitites to.
     * @param attachedEntities
     *            The new phoenix entities to add to the entity
     * @return {@link AddToEntity} with filled keys from the entity
     */
    public static <T extends PhoenixEntity, E extends PhoenixEntity> AddToEntity<T, E> createAddTo(T entity, E... attachedEntities) {
        return new AddToEntity<T, E>(createSelect(entity), attachedEntities);
    }

    /**
     * Helper class to store the key fields from a class
     */
    private static class KeyFields {
        private Map<String, Field> fieldMap;

        public KeyFields() {
            fieldMap = new HashMap<String, Field>();
        }

        public void addField(Field f) {
            fieldMap.put(f.getName(), f);
        }

        public Map<String, Field> getFields() {
            return fieldMap;
        }
    }
}

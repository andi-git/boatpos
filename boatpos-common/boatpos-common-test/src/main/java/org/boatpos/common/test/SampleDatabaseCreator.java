package org.boatpos.common.test;

import javax.persistence.EntityManager;

/**
 * Component to create the database / fill the database with data.
 * <p>
 * At startup method {@link #fillDatabase(EntityManager)} has to be called.
 * <p>
 * On shutdown method {@link #clearDatabase(EntityManager)} has to be called.
 */
public interface SampleDatabaseCreator {

    /**
     * Fill the database with data.
     *
     * @param entityManager the current {@link EntityManager}
     */
    void fillDatabase(EntityManager entityManager) throws Exception;

    /**
     * Remove the data from database.
     *
     * @param entityManager the current {@link EntityManager}
     */
    void clearDatabase(EntityManager entityManager) throws Exception;
}

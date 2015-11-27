package org.boatpos.test.model;

import javax.persistence.EntityManager;

/**
 * Provides an instance of an {@link EntityManager}.
 */
public interface EntityManagerProvider {

    EntityManager getEntityManager();
}

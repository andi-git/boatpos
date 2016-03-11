package org.boatpos.common.test;

import javax.persistence.EntityManager;

/**
 * Provides an instance of an {@link EntityManager}.
 */
public interface EntityManagerProvider {

    EntityManager getEntityManager();
}

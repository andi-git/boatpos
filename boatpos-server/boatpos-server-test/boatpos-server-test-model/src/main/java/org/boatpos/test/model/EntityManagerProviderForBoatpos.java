package org.boatpos.test.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract class for {@link EntityManagerProvider} with {@link EntityManager} for boatpos.
 */
public abstract class EntityManagerProviderForBoatpos implements EntityManagerProvider {

    @PersistenceContext(unitName = "boatpos")
    protected EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

package org.boatpos.test.model;

import org.boatpos.common.test.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract class for {@link EntityManagerProvider} with {@link EntityManager} for boatpos.
 */
public abstract class EntityManagerProviderForBoatpos implements EntityManagerProvider {

    @PersistenceContext(unitName = "boatpos")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

package org.regkas.test.model;

import org.boatpos.common.test.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract class for {@link EntityManagerProvider} with {@link EntityManager} for regkas.
 */
public abstract class EntityManagerProviderForRegkas implements EntityManagerProvider {

    @PersistenceContext(unitName = "regkas")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

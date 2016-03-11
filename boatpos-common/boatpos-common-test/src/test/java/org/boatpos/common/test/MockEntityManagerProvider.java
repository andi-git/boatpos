package org.boatpos.common.test;

import org.mockito.Mockito;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;

@Dependent
public class MockEntityManagerProvider implements EntityManagerProvider {

    @Override
    public EntityManager getEntityManager() {
        return Mockito.mock(EntityManager.class);
    }
}

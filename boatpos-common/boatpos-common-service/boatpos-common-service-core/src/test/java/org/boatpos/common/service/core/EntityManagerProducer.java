package org.boatpos.common.service.core;

import org.boatpos.common.util.qualifiers.Current;
import org.mockito.Mockito;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @Current
    @ApplicationScoped
    public EntityManager getEntityManager() {
        return Mockito.mock(EntityManager.class);
    }
}

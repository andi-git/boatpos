package org.boatpos.service.core.util;

import org.boatpos.repository.api.BoatPosDB;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceContext(unitName = "boatpos")
    private EntityManager entityManager;

    @Produces
    @BoatPosDB
    @ApplicationScoped
    private EntityManager produce() {
        return entityManager;
    }
}

package org.boatpos.dao.core;

import org.boatpos.dao.api.BoatPosDB;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
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

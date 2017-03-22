package org.boatpos.common.repository.core;

import org.boatpos.common.repository.core.boat.BoatEntity;
import org.boatpos.common.test.SampleDatabaseCreator;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;

/**
 * Create a sample-database for tests.
 */
@SuppressWarnings("SqlNoDataSourceInspection")
@Dependent
public class SampleDatabaseCreatorCore implements SampleDatabaseCreator {

    @Resource
    private UserTransaction userTransaction;

    @Override
    @SuppressWarnings("OctalInteger")
    public void fillDatabase(EntityManager em) {
        // clear cache and reset sequence for ids
        em.flush();
        em.clear();
        em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();

        BoatEntity boat1 = new BoatEntity(null, 1, "E-Boot", new BigDecimal("16.8"), 1, true, "e", "et", 'a');
        BoatEntity boat2 = new BoatEntity(null, 1, "Tretboot klein", new BigDecimal("12.8"), 2, true, "t2", "t2t", 'b');
        BoatEntity boat3 = new BoatEntity(null, 1, "Tretboot groß", new BigDecimal("12.8"), 3, true, "t4", "t4t", 'c');
        BoatEntity boat4 = new BoatEntity(null, 1, "Tretboot Rutsche", new BigDecimal("13.8"), 4, true, "tr", "trt", 'd');
        BoatEntity boat5 = new BoatEntity(null, 1, "Liegeboot", new BigDecimal("9.8"), 5, true, "l", "lt", 'e');
        BoatEntity boat6 = new BoatEntity(null, 1, "Pönt", new BigDecimal("9.8"), 6, false, "p", "pt", 'f');

        em.persist(boat1);
        em.persist(boat2);
        em.persist(boat3);
        em.persist(boat4);
        em.persist(boat5);
        em.persist(boat6);
        em.flush();
    }


    @Override
    public void clearDatabase(EntityManager em) throws Exception {
        if (Status.STATUS_ACTIVE == userTransaction.getStatus()) {
            // clear all tables
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
            em.createNativeQuery("DELETE FROM boat").executeUpdate();
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
            // reset sequence for ids and clear cache
            em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
            em.flush();
            em.clear();
        }
    }
}

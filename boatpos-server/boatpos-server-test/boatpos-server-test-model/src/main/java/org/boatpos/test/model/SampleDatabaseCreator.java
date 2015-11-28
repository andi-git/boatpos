package org.boatpos.test.model;

import org.boatpos.model.Boat;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;

/**
 * Create a sample-database for tests.
 */
@Dependent
public class SampleDatabaseCreator {

    @Resource
    private UserTransaction userTransaction;

    public void fillDatabase(EntityManager em) {
        // clear cache and reset sequence for ids
        em.flush();
        em.clear();
        em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
        fillBoats(em);
    }

    private void fillBoats(EntityManager em) {
        em.persist(new Boat(null, 1, "E-Boot", "E", new BigDecimal("15.1"), new BigDecimal("8.1"), new BigDecimal("12.1"), 20));
        em.persist(new Boat(null, 1, "Tretboot klein", "T2", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10));
    }

    public void clearDatabase(EntityManager em) throws Exception {
        if (Status.STATUS_ACTIVE == userTransaction.getStatus()) {
            // clear all tables
            em.createNativeQuery("DELETE FROM boat").executeUpdate();
            // reset sequence for ids and clear cache
            em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
            em.flush();
            em.clear();
        }
    }
}

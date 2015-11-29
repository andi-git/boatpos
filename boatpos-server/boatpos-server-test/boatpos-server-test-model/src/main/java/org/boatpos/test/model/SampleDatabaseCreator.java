package org.boatpos.test.model;

import org.boatpos.model.Boat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;

/**
 * Create a sample-database for tests.
 */
@Dependent
public class SampleDatabaseCreator {

    private static final Logger log = LoggerFactory.getLogger(SampleDatabaseCreator.class);

    @Resource
    private UserTransaction userTransaction;

    public void fillDatabase(EntityManager em) {
        // clear cache and reset sequence for ids
        log.info("clear database and fill it with data");
        log.info("boat-count: " + String.valueOf(em.createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult()));
        em.flush();
        em.clear();
        em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
        fillBoats(em);
        log.info("boat-count: " + String.valueOf(em.createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult()));
    }

    private void fillBoats(EntityManager em) {
        log.info("insert boat #1");
        Boat boat = new Boat(null, 1, "E-Boot", "E", new BigDecimal("15.1"), new BigDecimal("8.1"), new BigDecimal("12.1"), 20);
        em.persist(boat);
        log.info("    id: " + boat.getId());
        log.info("insert boat #2");
        boat = new Boat(null, 1, "Tretboot klein", "T2", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10);
        em.persist(boat);
        log.info("    id: " + boat.getId());
    }

    public void clearDatabase(EntityManager em) throws Exception {
        if (Status.STATUS_ACTIVE == userTransaction.getStatus()) {
            log.info("boat-count: " + String.valueOf(em.createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult()));
            log.info("clear database (status: " + userTransaction.getStatus() + ")");
            // clear all tables
            em.createNativeQuery("DELETE FROM boat").executeUpdate();
            // reset sequence for ids and clear cache
            em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
            em.flush();
            em.clear();
            log.info("boat-count: " + String.valueOf(em.createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult()));
        }
    }
}

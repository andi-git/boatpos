package org.boatpos.test.model;

import org.boatpos.model.Boat;
import org.boatpos.model.Promotion;
import org.boatpos.model.Rental;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

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
        em.flush();
        em.clear();
        em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();

        Boat boat1 = new Boat(null, 1, "E-Boot", "E", new BigDecimal("15.1"), new BigDecimal("8.1"), new BigDecimal("12.1"), 20, new HashSet<>());
        Boat boat2 = new Boat(null, 1, "Tretboot klein", "T2", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, new HashSet<>());
        Promotion promotion = new Promotion(null, 1, "Fahr 3 zahl 2", 180, "pricePerHour * 2", new HashSet<>());
        Rental rental = new Rental(null, 1, 1, LocalDate.now(), boat1, LocalDateTime.now(), null, null, false, false, false, promotion, null, false);

        boat1.getRentals().add(rental);
        promotion.getRentals().add(rental);

        em.persist(boat1);
        em.persist(boat2);
        em.persist(promotion);
        em.persist(rental);
        em.flush();
    }


    public void clearDatabase(EntityManager em) throws Exception {
        if (Status.STATUS_ACTIVE == userTransaction.getStatus()) {
            // clear all tables
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
            em.createNativeQuery("DELETE FROM boat").executeUpdate();
            em.createNativeQuery("DELETE FROM rental").executeUpdate();
            em.createNativeQuery("DELETE FROM promotion").executeUpdate();
            em.createNativeQuery("DELETE FROM boat_rental").executeUpdate();
            em.createNativeQuery("DELETE FROM promotion_rental").executeUpdate();
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
            // reset sequence for ids and clear cache
            em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
            em.flush();
            em.clear();
        }
    }
}

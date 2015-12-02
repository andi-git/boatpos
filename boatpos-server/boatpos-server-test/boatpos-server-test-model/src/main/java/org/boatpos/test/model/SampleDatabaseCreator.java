package org.boatpos.test.model;

import com.google.common.collect.Sets;
import org.boatpos.model.*;

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
@SuppressWarnings("SqlNoDataSourceInspection")
@Dependent
public class SampleDatabaseCreator {

    @Resource
    private UserTransaction userTransaction;

    public void fillDatabase(EntityManager em) {
        // clear cache and reset sequence for ids
        em.flush();
        em.clear();
        em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();

        Boat boat1 = new Boat(null, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, new HashSet<>());
        Boat boat2 = new Boat(null, 1, "Tretboot klein", "T2", new BigDecimal("12.8"), new BigDecimal("7.5"), new BigDecimal("11.3"), 6, 2, new HashSet<>());
        Boat boat3 = new Boat(null, 1, "Tretboot groß", "T4", new BigDecimal("12.8"), new BigDecimal("7.5"), new BigDecimal("11.3"), 9, 3, new HashSet<>());
        Boat boat4 = new Boat(null, 1, "Tretboot Rutsche", "TR", new BigDecimal("13.8"), new BigDecimal("7.8"), new BigDecimal("11.7"), 3, 4, new HashSet<>());
        Boat boat5 = new Boat(null, 1, "Liegeboot", "L", new BigDecimal("9.8"), new BigDecimal("9.8"), new BigDecimal("9.8"), 5, 5, new HashSet<>());
        Promotion promotion1 = new PromotionBefore(null, 1, "Fahr 3 zahl 2", 180, "pricePerHour * 2", new HashSet<>());
        Promotion promotion2 = new PromotionBefore(null, 1, "Fahr 5 zahl 3", 300, "pricePerHour * 3", new HashSet<>());
        Promotion promotion3 = new PromotionAfter(null, 1, "HolliKnolli", "price / 3", new HashSet<>());
        Commitment commitment1 = new Commitment(null, 1, "Ausweis", true, new HashSet<>());
        Commitment commitment2 = new Commitment(null, 1, "EUR 50,-", false, new HashSet<>());
        Commitment commitment3 = new Commitment(null, 1, "EUR 100,-", false, new HashSet<>());
        Commitment commitment4 = new Commitment(null, 1, "Schlüssel", true, new HashSet<>());
        Commitment commitment5 = new Commitment(null, 1, "Diverses", true, new HashSet<>());
        Rental rental = new Rental(null, 1, 1, LocalDate.now(), boat1, LocalDateTime.now(), null, null, false, false, false, promotion1, Sets.newHashSet(commitment1, commitment2));

        boat1.getRentals().add(rental);
        promotion1.getRentals().add(rental);
        commitment1.getRentals().add(rental);

        em.persist(boat1);
        em.persist(boat2);
        em.persist(boat3);
        em.persist(boat4);
        em.persist(boat5);
        em.persist(promotion1);
        em.persist(promotion2);
        em.persist(promotion3);
        em.persist(commitment1);
        em.persist(commitment2);
        em.persist(commitment3);
        em.persist(commitment4);
        em.persist(commitment5);
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
            em.createNativeQuery("DELETE FROM commitment").executeUpdate();
            em.createNativeQuery("DELETE FROM boat_rental").executeUpdate();
            em.createNativeQuery("DELETE FROM rental_commitment").executeUpdate();
            em.createNativeQuery("DELETE FROM promotion_rental").executeUpdate();
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
            // reset sequence for ids and clear cache
            em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
            em.flush();
            em.clear();
        }
    }
}

package org.boatpos.test.model;

import org.boatpos.model.Boat;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

/**
 * Create a sample-database for tests.
 */
@Dependent
public class SampleDatabaseCreator {

    public void fillDatabase(EntityManager em) {
        fillBoats(em);
    }

    private void fillBoats(EntityManager em) {
        em.persist(new Boat(null, 1, "E-Boot", "E", new BigDecimal("15.1"), new BigDecimal("8.1"), new BigDecimal("12.1"), 20));
        em.persist(new Boat(null, 1, "Tretboot klein", "T2", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 20));
    }

    public void clearDatabase(EntityManager em) {
        em.createNativeQuery("DELETE FROM boat").executeUpdate();
    }
}

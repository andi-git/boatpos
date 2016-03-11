package org.boatpos.test.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

//import org.jboss.arquillian.transaction.api.annotation.Transactional;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@RunWith(Arquillian.class)
public class SampleDatabaseCreatorBoatPosTest {

    @Inject
    private SampleDatabaseCreatorBoatPos sampleDatabaseCreator;

    @PersistenceContext(unitName = "boatpos")
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testFillDatabase() throws Exception {
        sampleDatabaseCreator.fillDatabase(entityManager);
        assertEquals(new BigInteger("6"), entityManager.createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult());
        sampleDatabaseCreator.clearDatabase(entityManager);
        assertEquals(new BigInteger("0"), entityManager.createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult());
    }
}
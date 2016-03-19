package org.regkas.test.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.math.BigInteger;

import static org.junit.Assert.*;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@RunWith(Arquillian.class)
public class SampleDatabaseCreatorRegkasTest {

    @Inject
    private SampleDatabaseCreatorRegkas sampleDatabaseCreator;

    @PersistenceContext(unitName = "regkas")
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testFillDatabase() throws Exception {
        assertEquals(new BigInteger("0"), entityManager.createNativeQuery("SELECT COUNT(*) FROM company").getSingleResult());
        sampleDatabaseCreator.fillDatabase(entityManager);
        assertEquals(new BigInteger("1"), entityManager.createNativeQuery("SELECT COUNT(*) FROM company").getSingleResult());
        sampleDatabaseCreator.clearDatabase(entityManager);
        assertEquals(new BigInteger("0"), entityManager.createNativeQuery("SELECT COUNT(*) FROM company").getSingleResult());
    }
}
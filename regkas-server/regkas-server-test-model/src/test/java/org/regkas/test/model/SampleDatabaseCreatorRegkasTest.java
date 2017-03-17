package org.regkas.test.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.model.ReceiptEntity;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.model.ReceiptTypeStandardEntity;

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
        assertEquals(new BigInteger("3"), entityManager.createNativeQuery("SELECT COUNT(*) FROM company").getSingleResult());
        System.out.println(entityManager.createQuery("FROM ReceiptTypeEntity r WHERE name='Standard-Beleg'", ReceiptTypeEntity.class).getSingleResult().getClass().getName());
        System.out.println(entityManager.createQuery("FROM ReceiptEntity r WHERE r.receiptId='2015-0000001'", ReceiptEntity.class).getSingleResult().getReceiptType().getClass().getName());
        sampleDatabaseCreator.clearDatabase(entityManager);
        assertEquals(new BigInteger("0"), entityManager.createNativeQuery("SELECT COUNT(*) FROM company").getSingleResult());
    }
}
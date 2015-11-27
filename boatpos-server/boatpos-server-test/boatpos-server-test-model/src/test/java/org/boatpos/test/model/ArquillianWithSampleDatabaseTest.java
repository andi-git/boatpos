package org.boatpos.test.model;

import org.jboss.arquillian.core.api.InstanceProducer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.arquillian.transaction.spi.annotation.TransactionScope;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ArquillianWithSampleDatabaseTest extends EntityManagerProviderForBoatpos {

    @Test
    @Transactional
    public void testIfArquillianWithSampleDatabaseWorks() {
        assertEquals(new BigInteger("2"), entityManager.createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult());
    }

    @Test
    @Transactional
    public void testIfArquillianWithSampleDatabaseWorks2() {
        assertEquals(new BigInteger("2"), entityManager.createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult());
    }
}
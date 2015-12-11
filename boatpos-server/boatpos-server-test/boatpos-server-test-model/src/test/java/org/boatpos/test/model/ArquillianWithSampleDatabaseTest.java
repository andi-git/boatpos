package org.boatpos.test.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@RunWith(Arquillian.class)
public class ArquillianWithSampleDatabaseTest extends EntityManagerProviderForBoatpos {

    @Test
    @Transactional
    public void testIfArquillianWithSampleDatabaseWorks() {
        assertEquals(new BigInteger("6"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult());
    }

    @Test
    @Transactional
    public void testIfArquillianWithSampleDatabaseWorks2() {
        assertEquals(new BigInteger("6"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult());
    }
}
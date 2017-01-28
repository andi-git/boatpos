package org.regkas.repository.core.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ReceiptTypeStandardCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Test
    @Transactional
    public void testGetStandardBelegFromDatabase() {
        ReceiptType receiptType = receiptTypeRepository.loadBy(new Name("Standard-Beleg")).get();
        assertEquals(ReceiptTypeStandardCore.class, receiptType.getClass());
        assertEquals("Standard-Beleg", receiptType.getName().get());
    }

    @Test(expected = NullPointerException.class)
    @Transactional
    public void testGetStandardBelegFromDatabaseIllegalArgument() {
        Name name = null;
        receiptTypeRepository.loadBy(name).get();
    }

    @Test
    @Transactional
    public void testGetStandardBelegFromDatabaseWrongName() {
        try {
            receiptTypeRepository.loadBy(new Name("_unknown_")).get();
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("_unknown_"));
        }
    }
}
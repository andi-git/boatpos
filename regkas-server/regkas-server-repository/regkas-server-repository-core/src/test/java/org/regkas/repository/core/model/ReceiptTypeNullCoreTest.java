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

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptTypeNullCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Test
    @Transactional
    public void testGetStartBelegFromDatabase() {
        ReceiptType receiptType = receiptTypeRepository.loadBy(new Name("Null-Beleg")).get();
        assertEquals(ReceiptTypeNullCore.class, receiptType.getClass());
        assertEquals("Null-Beleg", receiptType.getName().get());
    }
}
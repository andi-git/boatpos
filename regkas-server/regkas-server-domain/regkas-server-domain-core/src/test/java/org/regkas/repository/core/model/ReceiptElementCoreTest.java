package org.regkas.repository.core.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.core.builder.ReceiptElementBuilderCoreTest;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptElementCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptElementBuilderCoreTest.ReceiptElementProducer receiptElementProducer;

    @Test
    @Transactional
    public void testGetter() {
        ReceiptElement receiptElement = receiptElementProducer.getReceiptElement();
        assertEquals("product-name", receiptElement.getProduct().getName().get());
        assertEquals(2, receiptElement.getAmount().get().intValue());
        assertEquals(new BigDecimal("6.00"), receiptElement.getTotalPrice().get());
    }
}
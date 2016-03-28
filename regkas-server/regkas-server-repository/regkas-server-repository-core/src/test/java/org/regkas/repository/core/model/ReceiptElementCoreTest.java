package org.regkas.repository.core.model;

import org.junit.Test;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.core.builder.ReceiptElementBuilderCoreTest;
import org.regkas.repository.core.builder.ReceiptTypeBuilderCoreTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ReceiptElementCoreTest {

    @Test
    public void testGetter() {
        ReceiptElement receiptElement = ReceiptElementBuilderCoreTest.build();
        assertEquals("product-name", receiptElement.getProduct().getName().get());
        assertEquals(2, receiptElement.getAmount().get().intValue());
        assertEquals(new BigDecimal("6.00"), receiptElement.getTotalPrice().get());
    }
}
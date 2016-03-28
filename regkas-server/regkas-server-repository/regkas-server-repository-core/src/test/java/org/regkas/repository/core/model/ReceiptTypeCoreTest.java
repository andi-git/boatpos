package org.regkas.repository.core.model;

import org.junit.Test;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.core.builder.ReceiptTypeBuilderCoreTest;
import org.regkas.repository.core.builder.TaxSetBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class ReceiptTypeCoreTest {

    @Test
    public void testGetter() {
        ReceiptType receiptType = ReceiptTypeBuilderCoreTest.build();
        assertEquals("receipttype-name", receiptType.getName().get());
    }
}
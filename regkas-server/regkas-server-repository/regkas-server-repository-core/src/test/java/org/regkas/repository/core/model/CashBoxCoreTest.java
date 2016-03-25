package org.regkas.repository.core.model;

import org.junit.Test;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.core.builder.CashBoxBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class CashBoxCoreTest {

    @Test
    public void testGetter() {
        CashBox cashBox = CashBoxBuilderCoreTest.build();
        assertEquals("cashbox-id", cashBox.getName().get());
        assertEquals("xyz", cashBox.getSignatureCertificateSerialNumber().get());
    }
}
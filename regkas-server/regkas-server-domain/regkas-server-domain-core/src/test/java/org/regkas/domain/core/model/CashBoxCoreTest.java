package org.regkas.domain.core.model;

import org.junit.Test;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.core.builder.CashBoxBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class CashBoxCoreTest {

    @Test
    public void testGetter() {
        CashBox cashBox = CashBoxBuilderCoreTest.build();
        assertEquals("cashbox-id", cashBox.getName().get());
        assertEquals("xyz", cashBox.getSignatureCertificateSerialNumber().get());
        assertEquals("192.168.0.11", cashBox.getPrinterIpAddress().get());
        assertEquals("AT0", cashBox.getCertificationServiceProvider().get());
    }
}
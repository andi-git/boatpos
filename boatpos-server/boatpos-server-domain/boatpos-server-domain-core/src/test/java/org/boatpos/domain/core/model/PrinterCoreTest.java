package org.boatpos.domain.core.model;

import org.boatpos.domain.api.model.Printer;
import org.boatpos.domain.core.TestUtil;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PrinterCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private TestUtil.PrinterUtil printerUtil;

    @Test
    @Transactional
    public void testPersist() {
        printerUtil.assertDatabasePrinterCount(1);
        Printer printer = printerUtil.createDummyPrinter().persist();
        printerUtil.assertDatabasePrinterCount(2);
        printer.delete();
        printerUtil.assertDatabasePrinterCount(1);
    }

    @Test
    @Transactional
    public void testEntity() {
        assertEquals("192.168.0.11", new PrinterCore(printerUtil.createDummyPrinter().asEntity()).getIpAddress().get());
    }

    @Test
    @Transactional
    public void testDto() {
        assertEquals("192.168.0.11", new PrinterCore(printerUtil.createDummyPrinter().asDto()).getIpAddress().get());
    }
}

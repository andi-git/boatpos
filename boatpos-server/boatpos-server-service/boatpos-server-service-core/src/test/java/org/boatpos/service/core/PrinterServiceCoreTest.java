package org.boatpos.service.core;

import org.boatpos.service.api.PrinterService;
import org.boatpos.service.api.bean.IpAddressBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PrinterServiceCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private PrinterService printerService;

    @Test
    @Transactional
    public void testLoad() throws Exception {
        assertEquals("192.168.0.11", printerService.load().getIpAddress());
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        assertEquals("192.168.0.11", printerService.load().getIpAddress());
        printerService.save(new IpAddressBean("192.168.0.12"));
        assertEquals("192.168.0.12", printerService.load().getIpAddress());
    }
}

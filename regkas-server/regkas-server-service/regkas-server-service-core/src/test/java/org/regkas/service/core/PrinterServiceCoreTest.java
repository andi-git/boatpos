package org.regkas.service.core;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.PrinterService;
import org.regkas.service.api.bean.IpAddressBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PrinterServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private PrinterService printerService;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Test
    @Transactional
    public void testLoadIp() throws Exception {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertEquals("192.168.0.11", printerService.loadIp().getIpAddress());
        cashBoxContext.clear();
    }

    @Test
    @Transactional
    public void testSaveIp() throws Exception {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertEquals("192.168.0.11", printerService.loadIp().getIpAddress());
        printerService.saveIp(new IpAddressBean("192.168.0.12"));
        assertEquals("192.168.0.12", printerService.loadIp().getIpAddress());
        cashBoxContext.clear();
    }
}
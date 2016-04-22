package org.boatpos.repository.core.repository;

import org.boatpos.repository.api.repository.PrinterRepository;
import org.boatpos.repository.api.values.IpAddress;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PrinterRepositoryCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private PrinterRepository printerRepository;

    @Test
    @Transactional
    public void testLoad() {
        assertEquals("192.168.0.11", printerRepository.load().getIpAddress().get());
    }

    @Test
    @Transactional
    public void testSave() {
        assertEquals("192.168.0.11", printerRepository.load().getIpAddress().get());
        printerRepository.load().setIpAddress(new IpAddress("192.168.0.12")).persist();
        assertEquals("192.168.0.12", printerRepository.load().getIpAddress().get());
    }
}

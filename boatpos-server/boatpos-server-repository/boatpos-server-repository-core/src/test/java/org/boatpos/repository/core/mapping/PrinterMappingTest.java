package org.boatpos.repository.core.mapping;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.model.PrinterEntity;
import org.boatpos.repository.core.TestUtil;
import org.boatpos.service.api.bean.PrinterBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PrinterMappingTest {

    @Inject
    private PrinterMapping printerMapping;

    @Inject
    private TestUtil.PrinterUtil printerUtil;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    public void testPrinterToPrinterBean() {
        PrinterEntity printerEntity = printerUtil.createDummyPrinter().asEntity();
        PrinterBean printerBean = printerMapping.mapEntity(printerEntity);
        assertEquals("192.168.0.11", printerBean.getIpAddress());
    }

    @Test
    public void testPrinterBeanToPrinter() {
        PrinterBean printerBean = printerUtil.createDummyPrinterBean();
        PrinterEntity printerEntity = printerMapping.mapDto(printerBean);
        assertEquals("192.168.0.11", printerEntity.getIpAddress());
    }
}
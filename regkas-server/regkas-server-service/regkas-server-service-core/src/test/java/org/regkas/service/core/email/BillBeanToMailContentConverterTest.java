package org.regkas.service.core.email;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.core.DateTimeHelperMock;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BillBeanToMailContentConverterTest {

    @Inject
    private BillBeanToMailContentConverter billBeanToMailContentConverter;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    public void testConvertToMailContent() throws Exception {
        BillBean billBean = new BillBean();
        billBean.setCashBoxID("cashbox1");
        billBean.setReceiptIdentifier("001");
        billBean.setReceiptType("standard");
        billBean.setReceiptDateAndTime(dateTimeHelper.currentTime());
        String expected = "cash-box: cashbox1\nreceipt-id: 001\nreceipt-time: " + dateTimeHelper.currentTime() + "\nreceipt-type: standard";
        assertEquals(expected, billBeanToMailContentConverter.convertToMailContent(billBean));
    }

}

package org.regkas.service.core.receipt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.BillBean;

@RunWith(Arquillian.class)
public class SignatureDeviceIsDamagedFirstTimeTest extends HandleSignatureDeviceAvailabilityTest {

    @Inject
    private SignatureDeviceIsDamagedFirstTime signatureDeviceIsDamagedFirstTime;

    @Test
    @Transactional
    public void testCanHandle() throws Exception {
        assertTrue(signatureDeviceIsDamagedFirstTime.canHandle(receiptSignatureDeviceNotAvailable, receiptSignatureDeviceAvailable));
        assertFalse(signatureDeviceIsDamagedFirstTime.canHandle(receiptSignatureDeviceAvailable, receiptSignatureDeviceNotAvailable));
        assertFalse(signatureDeviceIsDamagedFirstTime.canHandle(receiptSignatureDeviceAvailable, receiptSignatureDeviceAvailable));
        assertFalse(signatureDeviceIsDamagedFirstTime.canHandle(receiptSignatureDeviceNotAvailable, receiptSignatureDeviceNotAvailable));
    }

    @Test
    @Transactional
    public void testHandle() throws Exception {
        BillBean billBean = new BillBean();
        billBean = signatureDeviceIsDamagedFirstTime.handle(billBean);
        assertNull(billBean.getNullBill());
        assertTrue(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());
        assertTrue(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
    }
}

package org.regkas.service.core.receipt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.BillBean;

@RunWith(Arquillian.class)
public class SignatureDeviceIsAvailableAgainTest extends HandleSignatureDeviceAvailabilityTest {

    @Inject
    private SignatureDeviceIsAvailableAgain signatureDeviceIsAvailableAgain;

    @Test
    @Transactional
    public void testCanHandle() throws Exception {
        assertTrue(signatureDeviceIsAvailableAgain.canHandle(receiptSignatureDeviceAvailable, receiptSignatureDeviceNotAvailable));
        assertFalse(signatureDeviceIsAvailableAgain.canHandle(receiptSignatureDeviceNotAvailable, receiptSignatureDeviceAvailable));
        assertFalse(signatureDeviceIsAvailableAgain.canHandle(receiptSignatureDeviceAvailable, receiptSignatureDeviceAvailable));
        assertFalse(signatureDeviceIsAvailableAgain.canHandle(receiptSignatureDeviceNotAvailable, receiptSignatureDeviceNotAvailable));
    }

    @Test
    @Transactional
    public void testHandle() throws Exception {
        BillBean billBean = new BillBean();
        billBean = signatureDeviceIsAvailableAgain.handle(billBean);
        assertNotNull(billBean.getNullBill());
        assertTrue(mailSenderMock.isSendCalled());
        assertTrue(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
    }
}

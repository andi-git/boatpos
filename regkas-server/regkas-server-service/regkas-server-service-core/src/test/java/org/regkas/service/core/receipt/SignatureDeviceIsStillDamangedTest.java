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
public class SignatureDeviceIsStillDamangedTest extends HandleSignatureDeviceAvailabilityTest {

    @Inject
    private SignatureDeviceIsStillDamaged signatureDeviceIsStillDamaged;

    @Test
    @Transactional
    public void testCanHandle() throws Exception {
        assertTrue(signatureDeviceIsStillDamaged.canHandle(receiptSignatureDeviceNotAvailable, receiptSignatureDeviceNotAvailable));
        assertFalse(signatureDeviceIsStillDamaged.canHandle(receiptSignatureDeviceAvailable, receiptSignatureDeviceNotAvailable));
        assertFalse(signatureDeviceIsStillDamaged.canHandle(receiptSignatureDeviceNotAvailable, receiptSignatureDeviceAvailable));
        assertFalse(signatureDeviceIsStillDamaged.canHandle(receiptSignatureDeviceAvailable, receiptSignatureDeviceAvailable));
    }

    @Test
    @Transactional
    public void testHandle() throws Exception {
        BillBean billBean = new BillBean();
        billBean = signatureDeviceIsStillDamaged.handle(billBean);
        assertNull(billBean.getSammelBeleg());
        assertTrue(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
    }
}

package org.regkas.service.core.receipt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.service.api.bean.BillBean;

@RunWith(Arquillian.class)
public class SignatureDeviceIsStillDamangedTest extends HandleSignatureDeviceAvailabilityTest {

    @Inject
    private SignatureDeviceIsStillDamaged signatureDeviceIsStillDamaged;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private ReceiptRepository receiptRepository;


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
        Receipt receipt = receiptRepository.loadBy(new ReceiptId("2015-0000001"), cashBoxRepository.loadBy(new Name("RegKas1")).get()).get();
        receipt = signatureDeviceIsStillDamaged.handle(receipt);
        assertNull(receipt.getSammelBeleg());
        assertTrue(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
    }
}

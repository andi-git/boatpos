package org.regkas.service.core.receipt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptTypeSammel;
import org.regkas.domain.api.model.ReceiptTypeStandard;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.service.api.bean.Period;
import org.regkas.service.core.DateTimeHelperMock;

@SuppressWarnings("ConstantConditions")
@RunWith(Arquillian.class)
public class SignatureDeviceIsAvailableAgainTest extends HandleSignatureDeviceAvailabilityTest {

    @Inject
    private SignatureDeviceIsAvailableAgain signatureDeviceIsAvailableAgain;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private ReceiptCreator receiptCreator;

    @Inject
    private FirstSale firstSale;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private CashBoxContext cashBoxContext;

    @Override
    @Before
    public void before() {
        super.before();
        resetRkOnlineSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
        rkOnlineContext.setEnvironment(Environment.TEST);
        mailSenderMock.reset();
        financialOfficeSenderMock.reset();
        dateTimeHelper.setTime(LocalDateTime::now);
        financialOfficeSenderMock.reset();
    }

    @Override
    @After
    public void after() {
        super.after();
        resetRkOnlineSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
        dateTimeHelper.resetTime();
        financialOfficeSenderMock.reset();
    }

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
        addReceiptsWhereSignatureDeviceWasDamagedAndOneReceiptWhereSignatureDeviceIsAvailableAgain();
        CashBox regKas1 = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(6, receiptRepository.loadBy(Period.untilNow(), regKas1).size());

        Receipt receipt5 = receiptRepository.loadBy(new ReceiptId("2015-0000005"), regKas1).get();
        receipt5 = signatureDeviceIsAvailableAgain.handle(receipt5);

        String compactJwsRepresentationBefore = receipt5.getCompactJwsRepresentation().getCompactJwsRepresentation();
        assertEquals(new ReceiptId("2015-0000006"), receipt5.getSammelBeleg().getReceiptId());
        assertEquals(compactJwsRepresentationBefore, receipt5.getCompactJwsRepresentation().getCompactJwsRepresentation());
        assertTrue(mailSenderMock.isSendCalled());
        assertTrue(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());

        assertEquals(7, receiptRepository.loadBy(Period.untilNow(), regKas1).size());
        receipt5 = receiptRepository.loadBy(new ReceiptId("2015-0000005"), regKas1).get();
        assertTrue(receipt5.getSignatureDeviceAvailable().get());
        assertTrue(receipt5.getReceiptType() instanceof ReceiptTypeStandard);
        Receipt receipt6 = receiptRepository.loadBy(new ReceiptId("2015-0000006"), regKas1).get();
        assertTrue(receipt6.getSignatureDeviceAvailable().get());
        assertTrue(receipt6.getReceiptType() instanceof ReceiptTypeSammel);
    }

    @Test
    @Transactional
    public void testHandleWhenSignatureDeviceIsNotAvailableOnSammelBeleg() throws Exception {
        addReceiptsWhereSignatureDeviceWasDamagedAndOneReceiptWhereSignatureDeviceIsAvailableAgain();
        CashBox regKas1 = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(6, receiptRepository.loadBy(Period.untilNow(), regKas1).size());

        rkOnlineSessionThrowsException();
        Receipt receipt5 = receiptRepository.loadBy(new ReceiptId("2015-0000005"), regKas1).get();
        String compactJwsRepresentationBefore = receipt5.getCompactJwsRepresentation().getCompactJwsRepresentation();
        receipt5 = signatureDeviceIsAvailableAgain.handle(receipt5);

        assertNotEquals(compactJwsRepresentationBefore, receipt5.getCompactJwsRepresentation().getCompactJwsRepresentation());
        assertEquals("U2ljaGVyaGVpdHNlaW5yaWNodHVuZyBhdXNnZWZhbGxlbg", receipt5.getCompactJwsRepresentation().getSignatureOriginal());
        assertNull(receipt5.getSammelBeleg());
        assertTrue(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());

        assertEquals(6, receiptRepository.loadBy(Period.untilNow(), regKas1).size());
        receipt5 = receiptRepository.loadBy(new ReceiptId("2015-0000005"), regKas1).get();
        assertFalse(receipt5.getSignatureDeviceAvailable().get());
        assertTrue(receipt5.getReceiptType() instanceof ReceiptTypeStandard);
    }

    private void addReceiptsWhereSignatureDeviceWasDamagedAndOneReceiptWhereSignatureDeviceIsAvailableAgain() {
        rkOnlineSessionThrowsException();
        receiptCreator.createReceipt(firstSale.createDefaultSale());
        receiptCreator.createReceipt(firstSale.createDefaultSale());

        resetRkOnlineSession();
        receiptCreator.createReceipt(firstSale.createDefaultSale());
    }

    private void resetRkOnlineSession() {
        rkOnlineContext.resetSessions();
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
    }

    private void rkOnlineSessionThrowsException() {
        rkOnlineContext.resetSessions();
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());
    }
}

package org.regkas.service.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.context.CompanyContext;
import org.regkas.repository.api.context.UserContext;
import org.regkas.repository.api.model.CashboxJournal;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CashboxJournalRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.signature.RkOnlineResourceFactory;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.Period;
import org.regkas.service.core.email.MailSenderFactory;
import org.regkas.service.core.email.MailSenderMock;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderFactory;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderMock;
import org.regkas.service.core.receipt.FirstSale;
import org.regkas.service.core.receipt.RkOnlineResourceSessionThrowingException;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import java.util.List;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "FieldCanBeLocal"})
@RunWith(Arquillian.class)
public class SaleServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private SaleService saleService;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserContext userContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private FirstSale firstSale;

    @Inject
    private MailSenderFactory mailSenderFactory;

    @Inject
    private FinancialOfficeSenderFactory financialOfficeSenderFactory;

    private MailSenderMock mailSenderMock = new MailSenderMock();

    private FinancialOfficeSenderMock financialOfficeSenderMock = new FinancialOfficeSenderMock();

    @Inject
    private CashboxJournalRepository cashboxJournalRepository;

    @SuppressWarnings("Duplicates")
    @Before
    public void before() {
        System.setProperty("boatpos.crypto.complement.rep.bytes", "8");
        mailSenderMock.reset();
        mailSenderFactory.setMailSender(mailSenderMock);
        financialOfficeSenderMock.reset();
        financialOfficeSenderFactory.setFinancialOfficeSender(financialOfficeSenderMock);
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
        rkOnlineContext.setEnvironment(Environment.TEST);
        rkOnlineContext.resetSessions();
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
    }

    @After
    public void after() {
        mailSenderFactory.reset();
        financialOfficeSenderFactory.reset();
        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
        System.clearProperty("boatpos.crypto.complement.rep.bytes");
    }

    @Test
    @Transactional
    public void testSale() throws Exception {
        assertEquals(1300, cashBoxContext.get().getTurnoverCountCent().get().intValue());

        BillBean bill = saleService.sale(firstSale.createDefaultSale());
        firstSale.assertEqualsWhenSignatureDeviceIsAvailable(bill);

        Receipt storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        firstSale.assertEqualsWhenSignatureDeviceIsAvailable(storedReceipt);
        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertFalse(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());

        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
    }

    @Test
    @Transactional
    public void testSaleWhenSignatureDeviceIsNotAvailable() throws Exception {
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());

        BillBean bill = saleService.sale(firstSale.createDefaultSale());
        Receipt storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        firstSale.assertEqualsWhenSignatureDeviceIsNotAvailable(storedReceipt);
        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertTrue(mailSenderMock.isSendCalled());
        assertTrue(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());

        List<CashboxJournal> cashboxJournals = cashboxJournalRepository.loadBy(cashBoxContext.get());
        assertEquals(2, cashboxJournals.size());
        assertEquals("create receipt 2015-0000003, Standard-Beleg", cashboxJournals.get(0).getJournalMessage().get());
        assertEquals("signature-device 123 is damaged", cashboxJournals.get(1).getJournalMessage().get());

        rkOnlineResourceFactory.resetRkOnlineResourceSession();
    }

    @Test
    @Transactional
    public void testSaleWithNullReceipt() throws Exception {
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());

        BillBean bill = saleService.sale(firstSale.createDefaultSale());
        Receipt storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertEquals(FirstSale.expectedPayloadEncoded, storedReceipt.getCompactJwsRepresentation().getPayload());
        assertEquals(FirstSale.expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertNull(bill.getSammelBeleg());
        assertTrue(mailSenderMock.isSendCalled());
        assertTrue(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());

        mailSenderMock.reset();
        financialOfficeSenderMock.reset();

        bill = saleService.sale(firstSale.createDefaultSale());
        storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(4200, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000004_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_HbazrfakqjI=_123_EvYwLdR4uNc=",
            storedReceipt.getCompactJwsRepresentation().getPayload());
        assertEquals(FirstSale.expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertNull(bill.getSammelBeleg());
        assertTrue(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());

        mailSenderMock.reset();
        financialOfficeSenderMock.reset();
        rkOnlineResourceFactory.resetRkOnlineResourceSession();

        bill = saleService.sale(firstSale.createDefaultSale());
        storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(5650, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000005_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_Myra5avKkxs=_123_1dl3KaiYnAQ=",
            storedReceipt.getCompactJwsRepresentation().getPayload());
        assertEquals(88, storedReceipt.getCompactJwsRepresentation().getSignature().length());
        assertNotEquals(FirstSale.expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertNotNull(bill.getSammelBeleg());
        storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getSammelBeleg().getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000006_2015-07-01T15:00:00_0,00_0,00_0,00_0,00_0,00_bsH02gJiJH4=_123",
            storedReceipt
                .getCompactJwsRepresentation()
                .getPayload()
                .substring(0, storedReceipt.getCompactJwsRepresentation().getPayload().lastIndexOf('_')));
        assertEquals(88, storedReceipt.getCompactJwsRepresentation().getSignature().length());
        assertNotEquals(FirstSale.expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertTrue(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertTrue(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());

        mailSenderMock.reset();
        financialOfficeSenderMock.reset();

        bill = saleService.sale(firstSale.createDefaultSale());
        storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(7100, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000007_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_6LReYsW6VPs=_123",
            storedReceipt
                .getCompactJwsRepresentation()
                .getPayload()
                .substring(0, storedReceipt.getCompactJwsRepresentation().getPayload().lastIndexOf('_')));
        assertEquals(88, storedReceipt.getCompactJwsRepresentation().getSignature().length());
        assertNotEquals(FirstSale.expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertNull(bill.getSammelBeleg());
        assertFalse(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());
    }

}

package org.regkas.service.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.context.CompanyContext;
import org.regkas.domain.api.context.UserContext;
import org.regkas.domain.api.model.*;
import org.regkas.domain.api.repository.*;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.email.MailSenderFactory;
import org.regkas.service.core.email.MailSenderMock;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderFactory;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderMock;
import org.regkas.service.core.receipt.FirstSale;
import org.regkas.service.core.receipt.RkOnlineResourceSessionThrowingException;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "FieldCanBeLocal", "ConstantConditions"})
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

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private ProductRepository productRepository;

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
        dateTimeHelper.reset();
    }

    @Test
    @Transactional
    public void testSale() {
        assertEquals(1300, cashBoxContext.get().getTurnoverCountCent().get().intValue());

        BillBean bill = saleService.sale(firstSale.createDefaultSale());
        firstSale.assertEqualsWhenSignatureDeviceIsAvailable(bill);

        Receipt storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        firstSale.assertEqualsWhenSignatureDeviceIsAvailable(storedReceipt);
        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertTrue(bill.getSignatureDeviceAvailable());
        assertFalse(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());

        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
    }

    @Test
    @Transactional
    public void testSaleWhenSignatureDeviceIsNotAvailable() {
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());

        BillBean bill = saleService.sale(firstSale.createDefaultSale());
        Receipt storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        firstSale.assertEqualsWhenSignatureDeviceIsNotAvailable(storedReceipt);
        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertTrue(mailSenderMock.isSendCalled());
        assertTrue(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());

        List<CashboxJournal> cashboxJournals = cashboxJournalRepository.loadBy(cashBoxContext.get());
        assertEquals(1, cashboxJournals.size());
        assertEquals("signature-device 123 is damaged", cashboxJournals.get(0).getJournalMessage().get());

        rkOnlineResourceFactory.resetRkOnlineResourceSession();
    }

    @Test
    @Transactional
    public void testSaleWithNullReceipt() {
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
        assertNull(bill.getSammelBelegStart());
        assertNull(bill.getSammelBelegEnd());
        assertEquals(LocalDateTime.of(2015, 7, 1, 15, 0, 0), bill.getSammelBeleg().getSammelBelegStart());
        assertEquals(LocalDateTime.of(2015, 7, 1, 15, 0, 0), bill.getSammelBeleg().getSammelBelegEnd());
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

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testSaleWithoutLastReceipt() {
        userContext.set(userRepository.loadBy(new Name("Max Mustermann")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas2")));
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Standard-Beleg");
        saleService.sale(sale);
    }

    @Test
    @Transactional
    public void testSaleStartReceipt() {
        userContext.set(userRepository.loadBy(new Name("Max Mustermann")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas2")));
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Start-Beleg");
        BillBean billBean = saleService.sale(sale);
        assertEquals(new BigDecimal("0.00"), billBean.getSumTotal());
        assertEquals("create receipt 2015-0000001, Start-Beleg", cashboxJournalRepository.loadBy(cashBoxContext.get()).get(0).getJournalMessage().get());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testSaleStartReceiptWhenStartWasAlreadyCreated() {
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Start-Beleg");
        saleService.sale(sale);
    }

    @Test
    @Transactional
    public void testSaleTagesReceipt() {
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Tages-Beleg");
        BillBean billBean = saleService.sale(sale);
        assertEquals(new BigDecimal("22.00"), billBean.getIncomeBean().getTotalIncome());
        assertEquals("create receipt 2015-0000003, Tages-Beleg", cashboxJournalRepository.loadBy(cashBoxContext.get()).get(0).getJournalMessage().get());
    }

    @Test
    @Transactional
    public void testSaleMonatsReceipt() {
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Monats-Beleg");
        BillBean billBean = saleService.sale(sale);
        assertEquals(new BigDecimal("22.00"), billBean.getIncomeBean().getTotalIncome());
        assertEquals("create receipt 2015-0000003, Monats-Beleg", cashboxJournalRepository.loadBy(cashBoxContext.get()).get(0).getJournalMessage().get());
    }

    @Test
    @Transactional
    public void testSaleJahresReceipt() {
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Jahres-Beleg");
        BillBean billBean = saleService.sale(sale);
        assertEquals(new BigDecimal("22.00"), billBean.getIncomeBean().getTotalIncome());
        assertEquals("create receipt 2015-0000003, Jahres-Beleg", cashboxJournalRepository.loadBy(cashBoxContext.get()).get(0).getJournalMessage().get());
    }

    @Test
    @Transactional
    public void testIsSignatureDeviceDamaged() {
        assertTrue(saleService.isSignatureDeviceAvailable());

        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());
        BillBean billBean = saleService.sale(firstSale.createDefaultSale());
        assertFalse(billBean.getSignatureDeviceAvailable());
        assertFalse(saleService.isSignatureDeviceAvailable());

        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        saleService.sale(firstSale.createDefaultSale());
        assertTrue(saleService.isSignatureDeviceAvailable());
    }

    @Test
    @Transactional
    public void testWithElementsThatHaveTooManyPositionsBehindTheComma() {
        SaleBean saleBean = firstSale.createDefaultSale();
        saleBean.getSaleElements().get(0).setTotalPrice(new BigDecimal("2.500001"));
        BillBean bill = saleService.sale(saleBean);
        assertEquals(new BigDecimal("14.50"), bill.getSumTotal());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testSchlussReceiptWhenStartReceiptNotAvailable() {
        userContext.set(userRepository.loadBy(new Name("Max Mustermann")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas2")));
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Schluss-Beleg");
        saleService.sale(sale);
    }

    @Test
    @Transactional
    public void testSchlussReceipt() {
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Schluss-Beleg");
        BillBean billBean = saleService.sale(sale);
        assertEquals("Schluss-Beleg", billBean.getReceiptType());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testReceiptAfterSchlussReceiptWasCreated() {
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Schluss-Beleg");
        saleService.sale(sale);
        saleService.sale(firstSale.createDefaultSale());
    }

    @Test
    @Transactional
    public void testCorona() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas3")));

        assertEquals(1300, cashBoxContext.get().getTurnoverCountCent().get().intValue());

        ProductBean normal = productRepository.loadBy(new Name("Normal"), cashBoxContext.get()).get().asDto();
        ProductBean corona = productRepository.loadBy(new Name("Corona"), cashBoxContext.get()).get().asDto();
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Standard-Beleg");
        sale.setSaleElements(
                Lists.newArrayList(
                        new ReceiptElementBean(normal, 2, new BigDecimal("5.00")),
                        new ReceiptElementBean(corona, 3, new BigDecimal("6.00"))));

        BillBean bill = saleService.sale(sale);

        assertEquals("RegKas3", bill.getCashBoxID());
        assertEquals("2015-0000003", bill.getReceiptIdentifier());
        assertTrue(bill.getReceiptDateAndTime().until(dateTimeHelper.currentTime(), ChronoUnit.SECONDS) < 10);
        assertEquals("company", bill.getCompany().getName());
        assertEquals(new BigDecimal("11.00"), bill.getSumTotal());
        assertEquals(new BigDecimal("5.00"), bill.getSumTaxSetNormal());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetErmaessigt1());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetErmaessigt2());
        assertEquals(new BigDecimal("6.00"), bill.getSumTaxSetNull());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetBesonders());
        assertTrue(bill.getJwsCompact().contains("_2015-07-01T15:00:00_"));
        assertEquals(2, bill.getBillTaxSetElements().size());
        assertEquals("Normal", bill.getBillTaxSetElements().get(0).getName());
        assertEquals(new BigDecimal("5.00"), bill.getBillTaxSetElements().get(0).getPriceAfterTax());
        assertEquals(20, bill.getBillTaxSetElements().get(0).getTaxPercent().intValue());
        assertEquals("Corona", bill.getBillTaxSetElements().get(1).getName());
        assertEquals(new BigDecimal("6.00"), bill.getBillTaxSetElements().get(1).getPriceAfterTax());
        assertEquals(5, bill.getBillTaxSetElements().get(1).getTaxPercent().intValue());

        Receipt receipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();

        assertEquals("RegKas3", receipt.getCashBox().getName().get());
        assertEquals("2015-0000003", receipt.getReceiptId().get());
        assertEquals(LocalDateTime.of(2015, 7, 1, 15, 0, 0), receipt.getReceiptDate().get());
        assertEquals(new BigDecimal("5.00"), receipt.getTotalPriceAfterTaxOf(TaxSetNormal.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetErmaessigt1.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetErmaessigt2.class).get());
        assertEquals(new BigDecimal("6.00"), receipt.getTotalPriceAfterTaxOf(TaxSetNull.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetBesonders.class).get());
        assertEquals(new BigDecimal("11.00"), receipt.getTotalPrice().get());
        assertEquals(2400L, receipt.getCashBox().getTurnoverCountCent().get().longValue());

        assertTrue(bill.getSignatureDeviceAvailable());
        assertFalse(mailSenderMock.isSendCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());

        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
    }

}

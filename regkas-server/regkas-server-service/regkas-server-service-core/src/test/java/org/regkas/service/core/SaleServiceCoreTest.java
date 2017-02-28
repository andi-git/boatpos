package org.regkas.service.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.signature.RkOnlineResourceFactory;
import org.regkas.repository.api.signature.RkOnlineResourceSession;
import org.regkas.repository.api.signature.SignatureDeviceNotAvailableException;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import com.google.common.collect.Lists;

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
    private ProductRepository productRepository;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    private String expectedProtectedHeader = "eyJhbGciOiJFUzI1NiJ9";

    private String expectedPayloadEncoded = "_R1-AT0_RegKas1_2015-0000003_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_GFcSnbVWfIw=_123_dpzooBjO1F4=";

    private String expectedPayloadDecoded = "X1IxLUFUMF9SZWdLYXMxXzIwMTUtMDAwMDAwM18yMDE1LTA3LTAxVDE1OjAwOjAwXzcsNTBfNywwMF8wLDAwXzAsMDBfMCwwMF9HRmNTbmJWV2ZJdz1fMTIzX2Rwem9vQmpPMUY0PQ";

    private String expectedSignatureWhenDeviceIsNotAvailable = "U2ljaGVyaGVpdHNlaW5yaWNodHVuZyBhdXNnZWZhbGxlbg==";

    @Before
    public void before() {
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
        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
    }

    @Test
    @Transactional
    public void testSale() throws Exception {

        assertEquals(1300, cashBoxContext.get().getTurnoverCountCent().get().intValue());

        BillBean bill = saleService.sale(createSale());

        assertEquals("RegKas1", bill.getCashBoxID());
        assertEquals("2015-0000003", bill.getReceiptIdentifier());
        assertTrue(bill.getReceiptDateAndTime().until(dateTimeHelper.currentTime(), ChronoUnit.SECONDS) < 10);
        assertEquals("company", bill.getCompany().getName());
        assertEquals(new BigDecimal("14.50"), bill.getSumTotal());
        assertEquals(new BigDecimal("7.50"), bill.getSumTaxSetNormal());
        assertEquals(new BigDecimal("7.00"), bill.getSumTaxSetErmaessigt1());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetErmaessigt2());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetNull());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetBesonders());
        assertEquals(4, bill.getBillTaxSetElements().size());
        assertEquals("Snack", bill.getBillTaxSetElements().get(0).getName());
        assertEquals(2, bill.getBillTaxSetElements().get(0).getAmount().intValue());
        assertEquals(new BigDecimal("2.50"), bill.getBillTaxSetElements().get(0).getPriceAfterTax());
        assertEquals(new BigDecimal("2.27"), bill.getBillTaxSetElements().get(0).getPricePreTax());
        assertEquals(new BigDecimal("0.23"), bill.getBillTaxSetElements().get(0).getPriceTax());
        assertEquals("Cola", bill.getBillTaxSetElements().get(1).getName());
        assertEquals(3, bill.getBillTaxSetElements().get(1).getAmount().intValue());
        assertEquals(new BigDecimal("7.50"), bill.getBillTaxSetElements().get(1).getPriceAfterTax());
        assertEquals(new BigDecimal("6.25"), bill.getBillTaxSetElements().get(1).getPricePreTax());
        assertEquals(new BigDecimal("1.25"), bill.getBillTaxSetElements().get(1).getPriceTax());
        assertEquals("Cornetto", bill.getBillTaxSetElements().get(2).getName());
        assertEquals(1, bill.getBillTaxSetElements().get(2).getAmount().intValue());
        assertEquals(new BigDecimal("2.00"), bill.getBillTaxSetElements().get(2).getPriceAfterTax());
        assertEquals(new BigDecimal("1.82"), bill.getBillTaxSetElements().get(2).getPricePreTax());
        assertEquals(new BigDecimal("0.18"), bill.getBillTaxSetElements().get(2).getPriceTax());
        assertEquals("Cornetto", bill.getBillTaxSetElements().get(3).getName());
        assertEquals(1, bill.getBillTaxSetElements().get(3).getAmount().intValue());
        assertEquals(new BigDecimal("2.50"), bill.getBillTaxSetElements().get(3).getPriceAfterTax());
        assertEquals(new BigDecimal("2.27"), bill.getBillTaxSetElements().get(3).getPricePreTax());
        assertEquals(new BigDecimal("0.23"), bill.getBillTaxSetElements().get(3).getPriceTax());

        Receipt storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        BillBean dep = serializer.deserialize(storedReceipt.getDEP().get(), BillBean.class);
        assertEquals("RegKas1", dep.getCashBoxID());
        assertEquals("2015-0000003", dep.getReceiptIdentifier());
        assertEquals(LocalDateTime.of(2015, 7, 1, 15, 0, 0), dep.getReceiptDateAndTime());
        assertEquals(new BigDecimal("7.50"), dep.getSumTaxSetNormal());
        assertEquals(new BigDecimal("7.00"), dep.getSumTaxSetErmaessigt1());
        assertEquals(new BigDecimal("0.00"), dep.getSumTaxSetErmaessigt2());
        assertEquals(new BigDecimal("0.00"), dep.getSumTaxSetNull());
        assertEquals(new BigDecimal("0.00"), dep.getSumTaxSetBesonders());
        assertEquals(new BigDecimal("14.50"), storedReceipt.getTotalPrice().get());
        assertEquals(2750L, storedReceipt.getCashBox().getTurnoverCountCent().get().longValue());
        assertEquals("R1-AT0", storedReceipt.getSuiteId().get());
        assertEquals("GFcSnbVWfIw=", storedReceipt.getEncryptedTurnoverValue().get());
        assertEquals("ONRcz49yLDIo2FgwNhe9Q5fSiZFEies97uRMzeAAPkI=", storedReceipt.getCashBox().getAesKeyBase64().get());
        assertEquals("AT0", storedReceipt.getCashBox().getCertificationServiceProvider().get());
        assertEquals("123", storedReceipt.getCashBox().getSignatureCertificateSerialNumber().get());
        assertEquals("dpzooBjO1F4=", storedReceipt.getSignatureValuePreviousReceipt().get());
        assertEquals(expectedProtectedHeader, storedReceipt.getCompactJwsRepresentation().getCompactJwsRepresentation().split("\\.")[0]);
        assertEquals(expectedPayloadDecoded, storedReceipt.getCompactJwsRepresentation().getCompactJwsRepresentation().split("\\.")[1]);
        assertEquals(86, storedReceipt.getCompactJwsRepresentation().getCompactJwsRepresentation().split("\\.")[2].length());
        assertTrue(storedReceipt.getSignatureDeviceAvailable().get());

        String machineReadableRepresentation = storedReceipt.getReceiptMachineReadableRepresentation().get();
        assertEquals(expectedPayloadEncoded, machineReadableRepresentation.substring(0, machineReadableRepresentation.lastIndexOf('_')));
        assertEquals(88, machineReadableRepresentation.substring(machineReadableRepresentation.lastIndexOf('_') + 1).length());

        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
    }

    @Test
    @Transactional
    public void testSaleWhenSignatureDeviceIsNotAvailable() throws Exception {
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());

        BillBean bill = saleService.sale(createSale());
        Receipt storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();

        assertEquals(expectedProtectedHeader, storedReceipt.getCompactJwsRepresentation().getProtectedHeader());
        assertEquals(expectedPayloadEncoded, storedReceipt.getCompactJwsRepresentation().getPayload());
        assertEquals(expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());

        rkOnlineResourceFactory.resetRkOnlineResourceSession();
    }

    @Test
    @Transactional
    public void testSaleWithNullReceipt() throws Exception {
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());

        BillBean bill = saleService.sale(createSale());
        Receipt storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertEquals(expectedPayloadEncoded, storedReceipt.getCompactJwsRepresentation().getPayload());
        assertEquals(expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertNull(bill.getNullBill());
        System.out.println(bill);

        bill = saleService.sale(createSale());
        storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(4200, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000004_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_HbazrfakqjI=_123_EvYwLdR4uNc=",
            storedReceipt.getCompactJwsRepresentation().getPayload());
        assertEquals(expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertNull(bill.getNullBill());

        rkOnlineResourceFactory.resetRkOnlineResourceSession();

        bill = saleService.sale(createSale());
        storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(5650, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000005_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_Myra5avKkxs=_123_1dl3KaiYnAQ=",
            storedReceipt.getCompactJwsRepresentation().getPayload());
        assertEquals(88, storedReceipt.getCompactJwsRepresentation().getSignature().length());
        assertNotEquals(expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertNotNull(bill.getNullBill());
        storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getNullBill().getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000006_2015-07-01T15:00:00_0,00_0,00_0,00_0,00_0,00_bsH02gJiJH4=_123",
            storedReceipt
                .getCompactJwsRepresentation()
                .getPayload()
                .substring(0, storedReceipt.getCompactJwsRepresentation().getPayload().lastIndexOf('_')));
        assertEquals(88, storedReceipt.getCompactJwsRepresentation().getSignature().length());
        assertNotEquals(expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());

        bill = saleService.sale(createSale());
        storedReceipt = receiptRepository.loadBy(new ReceiptId(bill.getReceiptIdentifier()), cashBoxContext.get()).get();
        assertEquals(7100, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000007_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_6LReYsW6VPs=_123",
                storedReceipt
                        .getCompactJwsRepresentation()
                        .getPayload()
                        .substring(0, storedReceipt.getCompactJwsRepresentation().getPayload().lastIndexOf('_')));
        assertEquals(88, storedReceipt.getCompactJwsRepresentation().getSignature().length());
        assertNotEquals(expectedSignatureWhenDeviceIsNotAvailable, storedReceipt.getCompactJwsRepresentation().getSignature());
        assertNull(bill.getNullBill());
    }

    private SaleBean createSale() {
        ProductBean snack = productRepository.loadBy(new Name("Snack"), cashBoxContext.get()).get().asDto();
        ProductBean cola = productRepository.loadBy(new Name("Cola"), cashBoxContext.get()).get().asDto();
        ProductBean cornetto1 = productRepository.loadBy(new Name("Cornetto"), cashBoxContext.get()).get().asDto();
        ProductBean cornetto2 = productRepository.loadBy(new Name("Cornetto"), cashBoxContext.get()).get().asDto();
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Standard-Beleg");
        sale.setSaleElements(
            Lists.newArrayList(
                new ReceiptElementBean(snack, 2, new BigDecimal("2.50")),
                new ReceiptElementBean(cola, 3, new BigDecimal("7.50")),
                new ReceiptElementBean(cornetto1, 1, new BigDecimal("2.00")),
                new ReceiptElementBean(cornetto2, 1, new BigDecimal("2.50"))));
        return sale;
    }

    private static class RkOnlineResourceSessionThrowingException implements RkOnlineResourceSession {

        @Override
        public void loginSession() throws SignatureDeviceNotAvailableException {
            throw new SignatureDeviceNotAvailableException("just to throw the exception");
        }
    }
}

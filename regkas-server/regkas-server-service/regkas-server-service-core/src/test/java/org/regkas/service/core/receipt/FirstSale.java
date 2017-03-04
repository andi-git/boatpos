package org.regkas.service.core.receipt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.TaxSetBesonders;
import org.regkas.repository.api.model.TaxSetErmaessigt1;
import org.regkas.repository.api.model.TaxSetErmaessigt2;
import org.regkas.repository.api.model.TaxSetNormal;
import org.regkas.repository.api.model.TaxSetNull;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.DateTimeHelperMock;

import com.google.common.collect.Lists;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "FieldCanBeLocal", "WeakerAccess"})
@ApplicationScoped
public class FirstSale {

    public static final String expectedProtectedHeader = "eyJhbGciOiJFUzI1NiJ9";

    public static final String expectedPayloadEncoded = "_R1-AT0_RegKas1_2015-0000003_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_GFcSnbVWfIw=_123_dpzooBjO1F4=";

    public static final String expectedPayloadDecoded = "X1IxLUFUMF9SZWdLYXMxXzIwMTUtMDAwMDAwM18yMDE1LTA3LTAxVDE1OjAwOjAwXzcsNTBfNywwMF8wLDAwXzAsMDBfMCwwMF9HRmNTbmJWV2ZJdz1fMTIzX2Rwem9vQmpPMUY0PQ";

    public static final String expectedSignatureWhenDeviceIsNotAvailable = "U2ljaGVyaGVpdHNlaW5yaWNodHVuZyBhdXNnZWZhbGxlbg==";

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    public SaleBean createDefaultSale() {
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

    public void assertEqualsWhenSignatureDeviceIsAvailable(BillBean bill) {
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
    }

    public void assertEqualsWhenSignatureDeviceIsAvailable(Receipt receipt) {
        assertEquals("RegKas1", receipt.getCashBox().getName().get());
        assertEquals("2015-0000003", receipt.getReceiptId().get());
        assertEquals(LocalDateTime.of(2015, 7, 1, 15, 0, 0), receipt.getReceiptDate().get());
        assertEquals(new BigDecimal("7.50"), receipt.getTotalPriceAfterTaxOf(TaxSetNormal.class).get());
        assertEquals(new BigDecimal("7.00"), receipt.getTotalPriceAfterTaxOf(TaxSetErmaessigt1.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetErmaessigt2.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetNull.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetBesonders.class).get());

        assertEquals(new BigDecimal("14.50"), receipt.getTotalPrice().get());
        assertEquals(2750L, receipt.getCashBox().getTurnoverCountCent().get().longValue());
        assertEquals("R1-AT0", receipt.getSuiteId().get());
        assertEquals("GFcSnbVWfIw=", receipt.getEncryptedTurnoverValue().get());
        assertEquals("ONRcz49yLDIo2FgwNhe9Q5fSiZFEies97uRMzeAAPkI=", receipt.getCashBox().getAesKeyBase64().get());
        assertEquals("AT0", receipt.getCashBox().getCertificationServiceProvider().get());
        assertEquals("123", receipt.getCashBox().getSignatureCertificateSerialNumber().get());
        assertEquals("dpzooBjO1F4=", receipt.getSignatureValuePreviousReceipt().get());
        assertEquals(expectedProtectedHeader, receipt.getCompactJwsRepresentation().getCompactJwsRepresentation().split("\\.")[0]);
        assertEquals(expectedPayloadDecoded, receipt.getCompactJwsRepresentation().getCompactJwsRepresentation().split("\\.")[1]);
        assertEquals(86, receipt.getCompactJwsRepresentation().getCompactJwsRepresentation().split("\\.")[2].length());
        assertTrue(receipt.getSignatureDeviceAvailable().get());

        String machineReadableRepresentation = receipt.getReceiptMachineReadableRepresentation().get();
        assertEquals(expectedPayloadEncoded, machineReadableRepresentation.substring(0, machineReadableRepresentation.lastIndexOf('_')));
        assertEquals(88, machineReadableRepresentation.substring(machineReadableRepresentation.lastIndexOf('_') + 1).length());
    }

    public void assertEqualsWhenSignatureDeviceIsNotAvailable(Receipt receipt) {
        assertEquals(expectedProtectedHeader, receipt.getCompactJwsRepresentation().getProtectedHeader());
        assertEquals(expectedPayloadEncoded, receipt.getCompactJwsRepresentation().getPayload());
        assertEquals(expectedSignatureWhenDeviceIsNotAvailable, receipt.getCompactJwsRepresentation().getSignature());
    }
}

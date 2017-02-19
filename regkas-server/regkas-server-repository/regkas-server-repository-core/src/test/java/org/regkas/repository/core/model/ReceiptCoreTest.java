package org.regkas.repository.core.model;

import com.google.common.collect.Lists;
import org.boatpos.common.model.PaymentMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.TaxSetBesonders;
import org.regkas.repository.api.model.TaxSetErmaessigt1;
import org.regkas.repository.api.model.TaxSetErmaessigt2;
import org.regkas.repository.api.model.TaxSetNormal;
import org.regkas.repository.api.model.TaxSetNull;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.repository.core.builder.ReceiptBuilderCoreTest;
import org.regkas.repository.core.builder.ReceiptElementBuilderCoreTest;
import org.regkas.service.api.bean.BillBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class ReceiptCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptBuilderCoreTest.ReceiptProducer receiptProducer;

    @Inject
    private ReceiptElementBuilderCoreTest.ReceiptElementProducer receiptElementProducer;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelperMock;

    @Test
    @Transactional
    public void testGetter() {
        Receipt receipt = receiptProducer.getReceipt();
        assertEquals("receipt-id", receipt.getReceiptId().get());
        assertNotNull(receipt.getReceiptDate().get());
        assertEquals("etv", receipt.getEncryptedTurnoverValue().get());
        assertEquals("svpr", receipt.getSignatureValuePreviousReceipt().get());
        assertEquals("Standard-Beleg", receipt.getReceiptType().getName().get());
        assertEquals("company-name", receipt.getCompany().getName().get());
        assertEquals("user-name", receipt.getUser().getName().get());
        assertEquals("cashbox-id", receipt.getCashBox().getName().get());
        assertEquals(PaymentMethod.CASH, receipt.getPaymentMethod());

        receipt.clearReceiptElements();
        receipt.addReceiptElement(receiptElementProducer.getReceiptElement());
        assertEquals(1, receipt.getReceiptElements().size());
        receipt.clearReceiptElements();
        receipt.addReceiptElements(Lists.newArrayList(receiptElementProducer.getReceiptElement()));
        assertEquals(1, receipt.getReceiptElements().size());
    }

    @Test
    @Transactional
    public void testTotalPriceAfterTaxOf() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Receipt receipt = receiptRepository.loadBy(new ReceiptId("2015-0000001"), cashBox).get();
        assertEquals(new BigDecimal("11.00"), receipt.getTotalPrice().get());
        assertEquals(new BigDecimal("5.00"), receipt.getTotalPriceAfterTaxOf(TaxSetNormal.class).get());
        assertEquals(new BigDecimal("6.00"), receipt.getTotalPriceAfterTaxOf(TaxSetErmaessigt1.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetErmaessigt2.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetNull.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceAfterTaxOf(TaxSetBesonders.class).get());
    }

    @Test
    @Transactional
    public void testTotalPriceBeforeTaxOf() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Receipt receipt = receiptRepository.loadBy(new ReceiptId("2015-0000001"), cashBox).get();
        assertEquals(new BigDecimal("11.00"), receipt.getTotalPrice().get());
        assertEquals(new BigDecimal("4.17"), receipt.getTotalPriceBeforeTaxOf(TaxSetNormal.class).get());
        assertEquals(new BigDecimal("5.45"), receipt.getTotalPriceBeforeTaxOf(TaxSetErmaessigt1.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceBeforeTaxOf(TaxSetErmaessigt2.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceBeforeTaxOf(TaxSetNull.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalPriceBeforeTaxOf(TaxSetBesonders.class).get());
    }

    @Test
    @Transactional
    public void testTotalTaxOf() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Receipt receipt = receiptRepository.loadBy(new ReceiptId("2015-0000001"), cashBox).get();
        assertEquals(new BigDecimal("11.00"), receipt.getTotalPrice().get());
        assertEquals(new BigDecimal("0.83"), receipt.getTotalTaxOf(TaxSetNormal.class).get());
        assertEquals(new BigDecimal("0.55"), receipt.getTotalTaxOf(TaxSetErmaessigt1.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalTaxOf(TaxSetErmaessigt2.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalTaxOf(TaxSetNull.class).get());
        assertEquals(new BigDecimal("0.00"), receipt.getTotalTaxOf(TaxSetBesonders.class).get());
    }

    @Test
    @Transactional
    public void testAsBillBean() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Receipt receipt = receiptRepository.loadBy(new ReceiptId("2015-0000001"), cashBox).get();
        BillBean bill = receipt.asBillBean();
        assertEquals("company", bill.getCompany().getName());
        assertEquals("RegKas1", bill.getCashBoxID());
        assertEquals("2015-0000001", bill.getReceiptIdentifier());
        assertEquals(LocalDateTime.of(2015, 7, 1, 12, 0, 13), bill.getReceiptDateAndTime());
        assertEquals("12345", bill.getEncryptedTurnoverValue());
        assertEquals("123", bill.getSignatureCertificateSerialNumber());
        assertEquals("sign", bill.getSignatureValuePreviousReceipt());
        assertEquals(new BigDecimal("11.00"), bill.getSumTotal());
        assertEquals(new BigDecimal("5.00"), bill.getSumTaxSetNormal());
        assertEquals(new BigDecimal("6.00"), bill.getSumTaxSetErmaessigt1());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetErmaessigt2());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetNull());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetBesonders());
        assertEquals(2, bill.getBillTaxSetElements().size());
        assertEquals("Cola", bill.getBillTaxSetElements().get(0).getName());
        assertEquals(2, bill.getBillTaxSetElements().get(0).getAmount().intValue());
        assertEquals(new BigDecimal("5.00"), bill.getBillTaxSetElements().get(0).getPriceAfterTax());
        assertEquals(new BigDecimal("4.17"), bill.getBillTaxSetElements().get(0).getPricePreTax());
        assertEquals(new BigDecimal("0.83"), bill.getBillTaxSetElements().get(0).getPriceTax());
        assertEquals("Cornetto", bill.getBillTaxSetElements().get(1).getName());
        assertEquals(3, bill.getBillTaxSetElements().get(1).getAmount().intValue());
        assertEquals(new BigDecimal("6.00"), bill.getBillTaxSetElements().get(1).getPriceAfterTax());
        assertEquals(new BigDecimal("5.45"), bill.getBillTaxSetElements().get(1).getPricePreTax());
        assertEquals(new BigDecimal("0.55"), bill.getBillTaxSetElements().get(1).getPriceTax());
    }


    @Test
    @Transactional
    public void testGetReceiptString() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Receipt receipt = receiptRepository.loadBy(new ReceiptId("2015-0000001"), cashBox).get();
        String expectedReceiptString = "" +
                "_R1-AT0" +
                "_RegKas1" +
                "_2015-0000001" +
                "_2015-07-01T12:00:13" +
                "_5,00" +
                "_6,00" +
                "_0,00" +
                "_0,00" +
                "_0,00" +
                "_12345" +
                "_123" +
                "_sign";
        assertEquals(expectedReceiptString, receipt.getReceiptString().get());
    }
}
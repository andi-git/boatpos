package org.regkas.service.core;

import com.google.common.collect.Lists;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.*;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.*;
import org.regkas.service.core.context.CashBoxContext;
import org.regkas.service.core.context.CompanyContext;
import org.regkas.service.core.context.UserContext;
import org.regkas.service.core.serializer.NonPrettyPrintingGson;
import org.regkas.service.core.serializer.Serializer;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.zip.GZIPOutputStream;

import static org.junit.Assert.assertEquals;

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
    private DateTimeHelperMock dateTimeHelperMock;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Test
    @Transactional
    public void testSale() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));

        ProductBean snack = productRepository.loadBy(new Name("Snack"), cashBoxContext.get()).get().asDto();
        ProductBean cola = productRepository.loadBy(new Name("Cola"), cashBoxContext.get()).get().asDto();
        ProductBean cornetto1 = productRepository.loadBy(new Name("Cornetto"), cashBoxContext.get()).get().asDto();
        ProductBean cornetto2 = productRepository.loadBy(new Name("Cornetto"), cashBoxContext.get()).get().asDto();

        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Standard-Beleg");
        sale.setSaleElements(Lists.newArrayList(
                new ReceiptElementBean(snack, 2, new BigDecimal("2.50")),
                new ReceiptElementBean(cola, 3, new BigDecimal("7.50")),
                new ReceiptElementBean(cornetto1, 1, new BigDecimal("2.00")),
                new ReceiptElementBean(cornetto2, 1, new BigDecimal("2.50")))
        );

        BillBean bill = saleService.sale(sale);

        assertEquals("RegKas1", bill.getCashBoxID());
        assertEquals("2015-0000003", bill.getReceiptIdentifier());
        assertEquals(dateTimeHelperMock.currentTime(), bill.getReceiptDateAndTime());
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
        assertEquals("{\"Kassen-ID\":\"RegKas1\",\"Belegnummer\":\"2015-0000003\",\"Beleg-Datum-Uhrzeit\":\"2015-07-01T15:00:00\",\"Betrag-Satz-Normal\":7.50,\"Betrag-Satz-Ermaessigt-1\":7.00,\"Betrag-Satz-Ermaessigt-2\":0.00,\"Betrag-Satz-Null\":0.00,\"Betrag-Satz-Besonders\":0.00,\"Belegelemente\":[{\"Produkt\":\"Snack\",\"Steuersatz\":10,\"Anzahl\":2,\"Netto\":2.27,\"Brutto\":2.50,\"Steuer\":0.23},{\"Produkt\":\"Cola\",\"Steuersatz\":20,\"Anzahl\":3,\"Netto\":6.25,\"Brutto\":7.50,\"Steuer\":1.25},{\"Produkt\":\"Cornetto\",\"Steuersatz\":10,\"Anzahl\":1,\"Netto\":1.82,\"Brutto\":2.00,\"Steuer\":0.18},{\"Produkt\":\"Cornetto\",\"Steuersatz\":10,\"Anzahl\":1,\"Netto\":2.27,\"Brutto\":2.50,\"Steuer\":0.23}],\"Gesamtbetrag\":14.50,\"Beleg-Art\":\"Standard-Beleg\"}", storedReceipt.getDEP().get());
        BillBean dep = serializer.deserialize(storedReceipt.getDEP().get(), BillBean.class);
        assertEquals("RegKas1", dep.getCashBoxID());
        assertEquals("2015-0000003", dep.getReceiptIdentifier());
        assertEquals(LocalDateTime.of(2015, 7, 1, 15, 0, 0, 0), dep.getReceiptDateAndTime());
        assertEquals(new BigDecimal("7.50"), dep.getSumTaxSetNormal());
        assertEquals(new BigDecimal("7.00"), dep.getSumTaxSetErmaessigt1());
        assertEquals(new BigDecimal("0.00"), dep.getSumTaxSetErmaessigt2());
        assertEquals(new BigDecimal("0.00"), dep.getSumTaxSetNull());
        assertEquals(new BigDecimal("0.00"), dep.getSumTaxSetBesonders());

        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
    }

    public static String compress(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return str;
        }
        System.out.println("String length : " + str.length());
        ByteArrayOutputStream obj=new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str.getBytes("UTF-8"));
        gzip.close();
        String outStr = obj.toString("UTF-8");
        System.out.println("Output String length : " + outStr.length());
        return outStr;
    }
}
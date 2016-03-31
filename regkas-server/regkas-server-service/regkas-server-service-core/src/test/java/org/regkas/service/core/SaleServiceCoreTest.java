package org.regkas.service.core;

import com.google.common.collect.Lists;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.context.CashBoxContext;
import org.regkas.service.core.context.CompanyContext;
import org.regkas.service.core.context.UserContext;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.math.BigDecimal;

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

    @Test
    @Transactional
    public void testSale() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));

        ProductBean snack = productRepository.loadBy(new Name("Snack")).get().asDto();
        ProductBean cola = productRepository.loadBy(new Name("Cola")).get().asDto();
        ProductBean cornetto = productRepository.loadBy(new Name("Cornetto")).get().asDto();

        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Standard-Beleg");
        sale.setSaleElements(Lists.newArrayList(
                new ReceiptElementBean(snack, 2, new BigDecimal("2.50")),
                new ReceiptElementBean(cola, 3, new BigDecimal("7.50")),
                new ReceiptElementBean(cornetto, 1, new BigDecimal("2.00")))
        );

        BillBean bill = saleService.sale(sale);

        assertEquals("RegKas1", bill.getCashBoxID());
        assertEquals("2015-0000003", bill.getReceiptIdentifier());
        assertEquals("2015-0000003", bill.getReceiptIdentifier());
        assertEquals(dateTimeHelperMock.currentTime(), bill.getReceiptDateAndTime());
        assertEquals("company", bill.getCompany().getName());
        assertEquals(new BigDecimal("7.50"), bill.getSumTaxSetNormal());
        assertEquals(new BigDecimal("4.50"), bill.getSumTaxSetErmaessigt1());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetErmaessigt2());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetNull());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetBesonders());
        assertEquals(new BigDecimal("12.00"), bill.getSumTotal());
        assertEquals(2, bill.getBillTaxSetElements().size());
        assertEquals(20, bill.getBillTaxSetElements().get(0).getTaxPercent().intValue());
        assertEquals(new BigDecimal("7.50"), bill.getBillTaxSetElements().get(0).getPriceAfterTax());
        assertEquals(new BigDecimal("6.25"), bill.getBillTaxSetElements().get(0).getPricePreTax());
        assertEquals(new BigDecimal("1.25"), bill.getBillTaxSetElements().get(0).getPriceTax());
        assertEquals(10, bill.getBillTaxSetElements().get(1).getTaxPercent().intValue());
        assertEquals(new BigDecimal("4.50"), bill.getBillTaxSetElements().get(1).getPriceAfterTax());
        assertEquals(new BigDecimal("4.09"), bill.getBillTaxSetElements().get(1).getPricePreTax());
        assertEquals(new BigDecimal("0.41"), bill.getBillTaxSetElements().get(1).getPriceTax());

        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
    }
}
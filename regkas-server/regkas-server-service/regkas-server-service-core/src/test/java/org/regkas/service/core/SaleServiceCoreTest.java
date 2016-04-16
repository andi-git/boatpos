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

        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
    }
}
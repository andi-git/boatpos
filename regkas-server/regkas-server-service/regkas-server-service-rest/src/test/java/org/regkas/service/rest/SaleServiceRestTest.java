package org.regkas.service.rest;

import com.google.common.collect.Lists;
import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class SaleServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Inject
    private DateTimeHelperMock dateTimeHelperMock;

    @Test
    public void testSale() throws Exception {
        ProductBean snack = helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("product/Snack")).get().readEntity(ProductBean.class);
        ProductBean cola = helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("product/Cola")).get().readEntity(ProductBean.class);
        ProductBean cornetto1 = helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("product/Cornetto")).get().readEntity(ProductBean.class);
        ProductBean cornetto2 = helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("product/Cornetto")).get().readEntity(ProductBean.class);

        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Standard-Beleg");
        sale.setSaleElements(Lists.newArrayList(
                new ReceiptElementBean(snack, 2, new BigDecimal("2.50")),
                new ReceiptElementBean(cola, 3, new BigDecimal("7.50")),
                new ReceiptElementBean(cornetto1, 1, new BigDecimal("2.00")),
                new ReceiptElementBean(cornetto2, 1, new BigDecimal("2.50")))
        );

        Response response = helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("sale")).post(Entity.json(sale));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        BillBean bill = response.readEntity(BillBean.class);

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
    }
}

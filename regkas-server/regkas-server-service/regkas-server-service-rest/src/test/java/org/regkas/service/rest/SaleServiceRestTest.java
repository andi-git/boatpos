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
        ProductBean cornetto = helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("product/Cornetto")).get().readEntity(ProductBean.class);

        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Standard-Beleg");
        sale.setSaleElements(Lists.newArrayList(
                new ReceiptElementBean(snack, 2, new BigDecimal("2.50")),
                new ReceiptElementBean(cola, 3, new BigDecimal("7.50")),
                new ReceiptElementBean(cornetto, 1, new BigDecimal("2.00")))
        );

        Response response = helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("sale")).post(Entity.json(sale));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        BillBean bill = response.readEntity(BillBean.class);

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
    }
}

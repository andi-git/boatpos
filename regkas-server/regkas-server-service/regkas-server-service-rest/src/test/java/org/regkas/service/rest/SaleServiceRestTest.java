package org.regkas.service.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;

import com.google.common.collect.Lists;

@RunWith(Arquillian.class)
public class SaleServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Inject
    private DateTimeHelperMock dateTimeHelperMock;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Override
    @Before
    public void before() throws Exception {
        super.before();
        rkOnlineContext.setEnvironment(Environment.TEST);
    }

    @Test
    public void testSale() throws Exception {
        ProductBean snack = helper
            .createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("product/Snack"))
            .get()
            .readEntity(ProductBean.class);
        ProductBean cola = helper
            .createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("product/Cola"))
            .get()
            .readEntity(ProductBean.class);
        ProductBean cornetto1 = helper
            .createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("product/Cornetto"))
            .get()
            .readEntity(ProductBean.class);
        ProductBean cornetto2 = helper
            .createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("product/Cornetto"))
            .get()
            .readEntity(ProductBean.class);

        SaleBean sale = new SaleBean();
        sale.setPaymentMethod("cash");
        sale.setReceiptType("Standard-Beleg");
        sale.setSaleElements(
            Lists.newArrayList(
                new ReceiptElementBean(snack, 2, new BigDecimal("2.50")),
                new ReceiptElementBean(cola, 3, new BigDecimal("7.50")),
                new ReceiptElementBean(cornetto1, 1, new BigDecimal("2.00")),
                new ReceiptElementBean(cornetto2, 1, new BigDecimal("2.50"))));

        Response response = helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("sale")).post(Entity.json(sale));
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
        assertEquals(
            "_R1-AT0_RegKas1_2015-0000003_2015-07-01T15:00:00_7,50_7,00_0,00_0,00_0,00_GFcSnbVWfIw=_123_dpzooBjO1F4=_",
            bill.getJwsCompact().substring(0, bill.getJwsCompact().lastIndexOf('_') + 1));
        assertEquals(88, bill.getJwsCompact().substring(bill.getJwsCompact().lastIndexOf('_') + 1).length());
    }

    @Test
    public void testSignatureDeviceAvailable() throws Exception {
        Response response = helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("sale/signatureDeviceAvailable")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.readEntity(Boolean.class));
    }
}

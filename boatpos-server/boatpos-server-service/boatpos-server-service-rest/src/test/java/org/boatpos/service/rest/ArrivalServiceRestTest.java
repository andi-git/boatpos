package org.boatpos.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.boatpos.common.test.rest.RestTestHelper;
import org.boatpos.service.api.bean.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.BillBean;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class ArrivalServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testArrive() throws Exception {
        Response response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/arrive")).post(Entity.json(new ArrivalBean(3)));
        RentalBean rentalBean = response.readEntity(RentalBean.class);
        assertEquals(3, rentalBean.getDayId().intValue());
        assertFalse(rentalBean.isFinished());

        response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/arrive")).post(Entity.json(new ArrivalBean(999)));
        assertEquals(500, response.getStatus());
    }

    @Test
    public void testAddPromotionAndPay() throws Exception {
        Response response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/arrive")).post(Entity.json(new ArrivalBean(3)));
        RentalBean rentalBean = response.readEntity(RentalBean.class);
        assertEquals(new BigDecimal("44.80"), rentalBean.getPriceCalculatedAfter());

        Long promotionId = helper.createRestCall(url, (wt) -> wt.path("promotion/after/name/HolliKnolli")).get().readEntity(PromotionAfterBean.class).getId();
        response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/promotion")).post(Entity.json(new AddPromotionBean(3, promotionId)));
        rentalBean = response.readEntity(RentalBean.class);
        assertEquals(new BigDecimal("22.40"), rentalBean.getPriceCalculatedAfter());

        response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/pay")).post(Entity.json(new PaymentBean(3, new BigDecimal("22.40"), "cash")));
        BillBean billBean = response.readEntity(BillBean.class);
        assertEquals(new BigDecimal("22.40"), billBean.getSumTaxSetNormal());
        rentalBean = helper.createRestCall(url, (webTarget) -> webTarget.path("rental/3")).get().readEntity(RentalBean.class);
        assertTrue(rentalBean.isFinished());
        assertEquals("cash", rentalBean.getPaymentMethodAfter());
    }

    @Test
    public void testRemovePromotionsAfter() throws Exception {
        Response response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/arrive")).post(Entity.json(new ArrivalBean(3)));
        RentalBean rentalBean = response.readEntity(RentalBean.class);
        assertEquals(new BigDecimal("44.80"), rentalBean.getPriceCalculatedAfter());

        Long promotionId = helper.createRestCall(url, (wt) -> wt.path("promotion/after/name/HolliKnolli")).get().readEntity(PromotionAfterBean.class).getId();
        response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/promotion")).post(Entity.json(new AddPromotionBean(3, promotionId)));
        rentalBean = response.readEntity(RentalBean.class);
        assertEquals(new BigDecimal("22.40"), rentalBean.getPriceCalculatedAfter());

        response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/promotion")).put(Entity.json(new RemovePromotionsAfterBean(3)));
        rentalBean = response.readEntity(RentalBean.class);
        assertEquals(new BigDecimal("44.80"), rentalBean.getPriceCalculatedAfter());
    }

    @Test
    public void testSignatureDeviceAvailable() throws Exception {
        assertTrue(helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/signatureDeviceAvailable")).get().readEntity(Boolean.class));
    }
}

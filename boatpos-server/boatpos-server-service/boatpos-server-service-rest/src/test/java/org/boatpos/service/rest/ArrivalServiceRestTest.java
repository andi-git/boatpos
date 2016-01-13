package org.boatpos.service.rest;

import org.boatpos.service.api.bean.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class ArrivalServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testArrive() throws Exception {
        Response response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/arrive")).post(Entity.json(new ArrivalBean(2)));
        RentalBean rentalBean = response.readEntity(RentalBean.class);
        assertEquals(2, rentalBean.getDayId().intValue());
        assertTrue(rentalBean.isFinished());

        response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/arrive")).post(Entity.json(new ArrivalBean(999)));
        assertEquals(500, response.getStatus());
    }

    @Test
    public void testAddPromotionAndPay() throws Exception {
        Response response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/arrive")).post(Entity.json(new ArrivalBean(2)));
        RentalBean rentalBean = response.readEntity(RentalBean.class);
        assertEquals(new BigDecimal("34.10"), rentalBean.getPriceCalculatedAfter());

        Long promotionId = helper.createRestCall(url, (wt) -> wt.path("promotion/after/name/HolliKnolli")).get().readEntity(PromotionAfterBean.class).getId();
        response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/promotion")).post(Entity.json(new AddPromotionBean(2, promotionId)));
        rentalBean = response.readEntity(RentalBean.class);
        assertEquals(new BigDecimal("17.10"), rentalBean.getPriceCalculatedAfter());

        response = helper.createRestCall(url, (webTarget) -> webTarget.path("arrival/pay")).post(Entity.json(new PaymentBean(2, new BigDecimal("17.10"))));
        BillBean billBean = response.readEntity(BillBean.class);
        assertEquals(new BigDecimal("17.10"), billBean.getSumTaxSetNormal());
    }
}

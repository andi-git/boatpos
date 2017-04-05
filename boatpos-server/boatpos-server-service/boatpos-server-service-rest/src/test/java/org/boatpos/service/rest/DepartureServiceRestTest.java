package org.boatpos.service.rest;

import com.google.common.collect.Sets;
import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.boatpos.common.test.rest.RestTestHelper;
import org.boatpos.service.api.bean.*;
import org.boatpos.common.util.datetime.DateTimeHelper;
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
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class DepartureServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    public void testDepart() throws Exception {
        Long boatId = helper.createRestCall(url, (wt) -> wt.path("boat/name/E")).get().readEntity(BoatBean.class).getId();
        Long commitmentId = helper.createRestCall(url, (wt) -> wt.path("commitment/name/Ausweis")).get().readEntity(CommitmentBean.class).getId();
        Response response = helper.createRestCall(url, (webTarget) -> webTarget.path("departure/depart")).post(Entity.json(new DepartureBean(boatId, Sets.newHashSet(commitmentId), null)));
        RentalBean rentalBean = response.readEntity(RentalBean.class);
        assertEquals(dateTimeHelper.currentTime(), rentalBean.getDeparture());
        assertNull(rentalBean.getPromotionBeforeBean());
        assertNull(rentalBean.getPromotionAfterBean());
        assertNull(rentalBean.getPriceCalculatedBefore());
    }

    @Test
    public void testPay() throws Exception {
        Long boatId = helper.createRestCall(url, (wt) -> wt.path("boat/name/E")).get().readEntity(BoatBean.class).getId();
        Long commitmentId = helper.createRestCall(url, (wt) -> wt.path("commitment/name/Ausweis")).get().readEntity(CommitmentBean.class).getId();
        Long promotionId = helper.createRestCall(url, (wt) -> wt.path("promotion/before/name/Fahr 3 zahl 2")).get().readEntity(PromotionBeforeBean.class).getId();
        Response response = helper.createRestCall(url, (webTarget) -> webTarget.path("departure/depart")).post(Entity.json(new DepartureBean(boatId, Sets.newHashSet(commitmentId), promotionId)));
        RentalBean rentalBean = response.readEntity(RentalBean.class);
        assertEquals("Fahr 3 zahl 2", rentalBean.getPromotionBeforeBean().getName());
        assertEquals(new BigDecimal("33.60"), rentalBean.getPriceCalculatedBefore());
        assertNull(rentalBean.getPricePaidBefore());

        response = helper.createRestCall(url, (webTarget) -> webTarget.path("departure/pay")).post(Entity.json(new PaymentBean(rentalBean.getDayId(), rentalBean.getPriceCalculatedBefore(), "card", "Standard-Beleg")));
        rentalBean = response.readEntity(RentalBean.class);
        assertEquals(new BigDecimal("33.60"), rentalBean.getPricePaidBefore());
        assertEquals("card", rentalBean.getPaymentMethodBefore());
    }
}

package org.boatpos.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.boatpos.common.test.rest.RestTestHelper;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class RentalServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    public void testGet() throws Exception {
        RentalBean rentalBean = helper.createRestCall(url, (webTarget) -> webTarget.path("rental/1")).get().readEntity(RentalBean.class);
        assertEquals("E-Boot", rentalBean.getBoatBean().getName());
        assertEquals("Fahr 3 zahl 2", rentalBean.getPromotionBeforeBean().getName());
        assertEquals(new BigDecimal("1.60"), rentalBean.getPriceCalculatedAfter());
        assertEquals(new BigDecimal("32.00"), rentalBean.getPricePaidBefore());
        assertEquals(2, rentalBean.getCommitmentBeans().size());
    }

    @Test
    public void testDelete() throws Exception {
        RentalBean rentalBean = helper.createRestCall(url, (webTarget) -> webTarget.path("rental/1")).get().readEntity(RentalBean.class);
        assertFalse(rentalBean.isDeleted());
        rentalBean = helper.createRestCall(url, (webTarget) -> webTarget.path("rental/1")).delete().readEntity(RentalBean.class);
        assertTrue(rentalBean.isDeleted());
    }

    @Test
    public void testUndoDelete() throws Exception {
        RentalBean rentalBean = helper.createRestCall(url, (webTarget) -> webTarget.path("rental/2")).get().readEntity(RentalBean.class);
        assertTrue(rentalBean.isDeleted());
        rentalBean = helper.createRestCall(url, (webTarget) -> webTarget.path("rental/undoDelete/2")).get().readEntity(RentalBean.class);
        assertFalse(rentalBean.isDeleted());
    }

    @Test
    public void testNextDayId() throws Exception {
        assertEquals(6, helper.createRestCall(url, (webTarget) -> webTarget.path("rental/nextId")).get().readEntity(RentalDayNumberWrapper.class).getDayNumber().intValue());
    }

    @Test
    public void testGetAllForCurrentDay() throws Exception {
        List<RentalBean> rentalBeans = helper.createRestCall(url, (webTarget) -> webTarget.path("rental/currentDay")).get().readEntity(new GenericType<List<RentalBean>>() {
        });
        assertEquals(5, rentalBeans.size());
    }
}

package org.boatpos.service.core;

import org.boatpos.repository.api.repository.PromotionAfterRepository;
import org.boatpos.repository.api.values.Name;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.bean.AddPromotionBean;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.PaymentBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.boatpos.util.datetime.DateTimeHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@RunWith(Arquillian.class)
public class ArrivalServiceCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private ArrivalService arrivalService;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private PromotionAfterRepository promotionAfterRepository;

    @Test
    @Transactional
    public void testArrive() {
        assertEquals(new BigInteger("11"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM rental").getSingleResult());
        RentalBean rental = arrivalService.arrive(new ArrivalBean(2));
        assertEquals(dateTimeHelper.currentTime(), rental.getArrival());
        assertTrue(rental.isFinished());
        assertEquals(new BigDecimal("34.10"), rental.getPriceCalculatedAfter());
        assertEquals(new BigInteger("11"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM rental").getSingleResult());
    }

    @Test
    @Transactional
    public void testPay() {
        RentalBean rental = arrivalService.arrive(new ArrivalBean(2));
        assertEquals(dateTimeHelper.currentTime(), rental.getArrival());
        assertTrue(rental.isFinished());
        assertEquals(new BigDecimal("34.10"), rental.getPriceCalculatedAfter());
        assertNull(rental.getPricePaidAfter());

        rental = arrivalService.pay(new PaymentBean(2, new BigDecimal("34.10")));
        assertEquals(new BigDecimal("34.10"), rental.getPricePaidAfter());
    }

    @Test
    @Transactional
    public void testAddPromotionAndPay() {
        RentalBean rental = arrivalService.arrive(new ArrivalBean(2));
        assertEquals(new BigDecimal("34.10"), rental.getPriceCalculatedAfter());
        assertNull(rental.getPromotionBean());
        assertNull(rental.getPricePaidAfter());

        rental = arrivalService.addPromotion(new AddPromotionBean(2, promotionAfterRepository.loadBy(new Name("HolliKnolli")).get().getId().get()));
        assertEquals(new BigDecimal("17.10"), rental.getPriceCalculatedAfter());
        assertEquals("HolliKnolli", rental.getPromotionBean().getName());
        assertNull(rental.getPricePaidAfter());

        rental = arrivalService.pay(new PaymentBean(2, new BigDecimal("17.10")));
        assertEquals(new BigDecimal("17.10"), rental.getPriceCalculatedAfter());
        assertEquals("HolliKnolli", rental.getPromotionBean().getName());
        assertEquals(new BigDecimal("17.10"), rental.getPricePaidAfter());
    }
}

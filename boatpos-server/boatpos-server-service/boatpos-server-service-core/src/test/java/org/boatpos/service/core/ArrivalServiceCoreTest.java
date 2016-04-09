package org.boatpos.service.core;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.repository.api.repository.PromotionAfterRepository;
import org.boatpos.repository.api.values.Name;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.*;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.BillBean;

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
    private RentalService rentalService;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private PromotionAfterRepository promotionAfterRepository;

    @Test
    @Transactional
    public void testArrive() {
        assertEquals(new BigInteger("11"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM rental").getSingleResult());
        RentalBean rental = arrivalService.arrive(new ArrivalBean(3));
        assertEquals(dateTimeHelper.currentTime(), rental.getArrival());
        assertFalse(rental.isFinished());
        assertEquals(new BigDecimal("44.80"), rental.getPriceCalculatedAfter());
        assertEquals(new BigInteger("11"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM rental").getSingleResult());
    }

    @Test
    @Transactional
    public void testPay() {
        RentalBean rental = arrivalService.arrive(new ArrivalBean(3));
        assertEquals(dateTimeHelper.currentTime(), rental.getArrival());
        assertFalse(rental.isFinished());
        assertEquals(new BigDecimal("44.80"), rental.getPriceCalculatedAfter());
        assertNull(rental.getPricePaidAfter());

        BillBean bill = arrivalService.pay(new PaymentBean(3, new BigDecimal("44.80"), "card"));
        assertEquals(new BigDecimal("44.80"), bill.getSumTaxSetNormal());
        rental = rentalService.get(new RentalDayNumberWrapper(3));
        assertTrue(rental.isFinished());
        assertEquals(PaymentMethod.CARD.toString(), rental.getPaymentMethodAfter());

        try {
            arrivalService.pay(new PaymentBean(3, new BigDecimal("44.80"), "cash"));
            fail("rental 3 is already finished");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    @Transactional
    public void testAddPromotionAndPay() {
        RentalBean rental = arrivalService.arrive(new ArrivalBean(3));
        assertEquals(new BigDecimal("44.80"), rental.getPriceCalculatedAfter());
        assertNull(rental.getPromotionBeforeBean());
        assertNull(rental.getPromotionAfterBean());
        assertNull(rental.getPricePaidAfter());

        rental = arrivalService.addPromotion(new AddPromotionBean(3, promotionAfterRepository.loadBy(new Name("HolliKnolli")).get().getId().get()));
        assertEquals(new BigDecimal("22.40"), rental.getPriceCalculatedAfter());
        assertEquals("HolliKnolli", rental.getPromotionAfterBean().getName());
        assertNull(rental.getPricePaidAfter());

        BillBean bill = arrivalService.pay(new PaymentBean(3, new BigDecimal("22.40"), "card"));
        assertEquals(new BigDecimal("22.40"), bill.getSumTaxSetNormal());
    }
}

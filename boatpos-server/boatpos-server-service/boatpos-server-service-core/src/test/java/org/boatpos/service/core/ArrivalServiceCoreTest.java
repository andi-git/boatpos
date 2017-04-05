package org.boatpos.service.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.inject.Inject;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.domain.api.repository.PromotionAfterRepository;
import org.boatpos.domain.api.values.Name;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.AddPromotionBean;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.PaymentBean;
import org.boatpos.service.api.bean.RemovePromotionsAfterBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.BillBean;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection", "ConstantConditions"})
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

        BillBean bill = arrivalService.pay(new PaymentBean(3, new BigDecimal("44.80"), "card", "Standard-Beleg"));
        assertEquals(new BigDecimal("44.80"), bill.getSumTaxSetNormal());
        rental = rentalService.get(new RentalDayNumberWrapper(3));
        assertTrue(rental.isFinished());
        assertEquals(PaymentMethod.CARD.toString(), rental.getPaymentMethodAfter());

        try {
            arrivalService.pay(new PaymentBean(3, new BigDecimal("44.80"), "cash", "Standard-Beleg"));
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

        rental = arrivalService
            .addPromotion(new AddPromotionBean(rental.getDayId(), promotionAfterRepository.loadBy(new Name("HolliKnolli")).get().getId().get()));
        assertEquals(new BigDecimal("22.40"), rental.getPriceCalculatedAfter());
        assertEquals("HolliKnolli", rental.getPromotionAfterBean().getName());
        assertNull(rental.getPricePaidAfter());

        BillBean bill = arrivalService.pay(new PaymentBean(3, new BigDecimal("22.40"), "card", "Standard-Beleg"));
        assertEquals(new BigDecimal("22.40"), bill.getSumTaxSetNormal());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testAddPromotionWhereRentalIsNotAvailable() {
        arrivalService.addPromotion(new AddPromotionBean(999, promotionAfterRepository.loadBy(new Name("HolliKnolli")).get().getId().get()));
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testAddPromotionWherePromotionIsNotAvailable() {
        arrivalService.addPromotion(new AddPromotionBean(3, 999L));
    }

    @Test
    @Transactional
    public void testRemovePromotionAndPay() {
        RentalBean rental = arrivalService.arrive(new ArrivalBean(3));

        rental = arrivalService
            .addPromotion(new AddPromotionBean(rental.getDayId(), promotionAfterRepository.loadBy(new Name("HolliKnolli")).get().getId().get()));
        assertEquals(new BigDecimal("22.40"), rental.getPriceCalculatedAfter());
        assertEquals("HolliKnolli", rental.getPromotionAfterBean().getName());
        assertNull(rental.getPricePaidAfter());

        rental = arrivalService.removePromotionsAfter(new RemovePromotionsAfterBean(rental.getDayId()));
        assertEquals(new BigDecimal("44.80"), rental.getPriceCalculatedAfter());
        assertNull(rental.getPromotionAfterBean());
        assertNull(rental.getPricePaidAfter());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testRemovePromotionWhereRentalIsNotAvailable() {
        arrivalService.removePromotionsAfter(new RemovePromotionsAfterBean(999));
    }

    @Test
    @Transactional
    public void testSignatureDeviceAvailable() {
        assertTrue(arrivalService.isSignatureDeviceAvailable());
    }
}

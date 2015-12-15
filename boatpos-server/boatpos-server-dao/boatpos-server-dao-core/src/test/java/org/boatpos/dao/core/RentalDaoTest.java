package org.boatpos.dao.core;

import com.google.common.collect.Sets;
import org.boatpos.dao.api.*;
import org.boatpos.model.*;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.boatpos.util.datetime.DateTimeHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class RentalDaoTest extends EntityManagerProviderForBoatpos {

    @Inject
    private RentalDao rentalDao;

    @Inject
    private BoatDao boatDao;

    @Inject
    private CommitmentDao commitmentDao;

    @Inject
    private PromotionDao promotionDao;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    @Transactional
    public void testGetById() {
        Rental rental = rentalDao.getById(2L).get();
        assertEquals("E-Boot", rental.getBoat().getName());
    }

    @Test
    @Transactional
    public void testGetNextDayId() {
        assertEquals(1, rentalDao.nextDayId(LocalDate.now()).intValue());
        assertEquals(6, rentalDao.nextDayId(dateTimeHelper.currentDate()).intValue());
    }

    @Test
    @Transactional
    public void testGetAllForCurrentDay() {
        assertEquals(5, rentalDao.getAllForCurrentDay().size());
    }

    @Test
    @Transactional
    public void testGetAllFor() {
        assertEquals(5, rentalDao.getAllFor(dateTimeHelper.currentDate(), Period.DAY).size());
        assertEquals(7, rentalDao.getAllFor(dateTimeHelper.currentDate(), Period.MONTH).size());
        assertEquals(9, rentalDao.getAllFor(dateTimeHelper.currentDate(), Period.YEAR).size());
    }

    @Test
    @Transactional
    public void testDepart() {
        assertEquals(5, rentalDao.getAllForCurrentDay().size());
        Boat boat = boatDao.getByShortName("E").get();
        Commitment commitment = commitmentDao.getByName("Ausweis").get();
        Rental rental = rentalDao.depart(boat, Sets.newHashSet(commitment), Optional.empty(), LocalDateTime.of(2015, 7, 1, 14, 15));
        assertNotNull(rental.getId());
        assertEquals(dateTimeHelper.currentDate(), rental.getDate());
        assertEquals(boat, rental.getBoat());
        assertEquals(6, rentalDao.getAllForCurrentDay().size());
    }

    @Test
    @Transactional
    public void testArrive() {
        Optional<Promotion> holliKnolli = promotionDao.getByName("HolliKnolli");
        Rental rental = rentalDao.arrive(3, LocalDateTime.of(2015, 7, 1, 15, 30), holliKnolli);
        assertNotNull(rental.getArrival());
        assertNotNull(rental.getPromotion());
        assertEquals(LocalDateTime.of(2015, 7, 1, 15, 30), rentalDao.getByDayId(3, dateTimeHelper.currentDate()).get().getArrival());
        assertEquals(holliKnolli.get(), rentalDao.getByDayId(3, dateTimeHelper.currentDate()).get().getPromotion());
    }

    @Test
    @Transactional
    public void testPay() {
        Rental rental = rentalDao.pay(3, dateTimeHelper.currentDate(), new BigDecimal("15.6"), PaymentMethod.CASH, true);
        assertNotNull(rental.getPrice());
        assertNotNull(rental.getPaymentMethod());
        assertTrue(rental.isCoupon());
        assertTrue(rental.isFinished());
        assertEquals(new BigDecimal("15.6"), rentalDao.getByDayId(3, dateTimeHelper.currentDate()).get().getPrice());
    }

    @Test
    @Transactional
    public void testDelete() {
        assertFalse(rentalDao.getByDayId(3, dateTimeHelper.currentDate()).get().isDeleted());
        assertEquals(5, rentalDao.getAllForCurrentDay().size());
        rentalDao.delete(3, dateTimeHelper.currentDate());
        assertTrue(rentalDao.getByDayId(3, dateTimeHelper.currentDate()).get().isDeleted());
        assertEquals(5, rentalDao.getAllForCurrentDay().size());
        rentalDao.undoDelete(3, dateTimeHelper.currentDate());
        assertFalse(rentalDao.getByDayId(3, dateTimeHelper.currentDate()).get().isDeleted());
        assertEquals(5, rentalDao.getAllForCurrentDay().size());
    }
}

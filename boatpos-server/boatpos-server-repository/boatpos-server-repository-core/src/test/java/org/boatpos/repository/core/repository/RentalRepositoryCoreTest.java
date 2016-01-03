package org.boatpos.repository.core.repository;

import com.google.common.collect.Sets;
import org.boatpos.model.PaymentMethod;
import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.*;
import org.boatpos.repository.api.values.*;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.boatpos.util.datetime.DateTimeHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RentalRepositoryCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private RentalRepository rentalRepository;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private CommitmentRepository commitmentRepository;

    @Inject
    private PromotionBeforeRepository promotionBeforeRepository;

    @Inject
    private PromotionAfterRepository promotionAfterRepository;

    @Test
    @Transactional
    public void testLoadByDayAndDayId() {
        assertEquals("E-Boot", rentalRepository.loadBy(new Day(dateTimeHelper.currentDate()), new DayId(1)).get().getBoat().getName().get());
    }

    @Test
    @Transactional
    public void testNextDayId() {
        assertEquals(6, rentalRepository.nextDayId(new Day(dateTimeHelper.currentDate())).get().intValue());
        assertEquals(1, rentalRepository.nextDayId(new Day(LocalDate.now())).get().intValue());
    }

    @Test
    @Transactional
    public void testGetAllForCurrentDay() {
        assertEquals(5, rentalRepository.loadAllForCurrentDay().size());
    }

    @Test
    @Transactional
    public void testGetAllFor() {
        assertEquals(5, rentalRepository.loadAll(Period.day(dateTimeHelper.currentDate())).size());
        assertEquals(7, rentalRepository.loadAll(Period.month(dateTimeHelper.currentDate())).size());
        assertEquals(9, rentalRepository.loadAll(Period.year(dateTimeHelper.currentDate())).size());
    }

    @Test
    @Transactional
    public void testDepart() {
        assertEquals(5, rentalRepository.loadAllForCurrentDay().size());
        Rental rental = rentalRepository.depart(
                new Day(dateTimeHelper.currentDate()),
                new DepartureTime(dateTimeHelper.currentTime()),
                boatRepository.loadBy(new ShortName("E")).get(),
                Sets.newHashSet(commitmentRepository.loadBy(new Name("Ausweis")).get()),
                promotionBeforeRepository.loadBy(new Name("Fahr 3 zahl 2")));
        assertNotNull(rental.getId());
        assertEquals(dateTimeHelper.currentDate(), rental.getDay().get());
        assertEquals("E-Boot", rental.getBoat().getName().get());
        assertEquals("Fahr 3 zahl 2", rental.getPromotion().get().getName().get());
        assertEquals(1, rental.getCommitments().size());
        assertEquals(6, rental.getDayId().get().intValue());
        assertEquals(dateTimeHelper.currentTime(), rental.getDepartureTime().get());
        assertEquals(6, rentalRepository.loadAllForCurrentDay().size());
    }

    @Test
    @Transactional
    public void testArrive() {
        Rental rental = rentalRepository.arrive(new Day(dateTimeHelper.currentDate()), new DayId(3), new ArrivalTime(LocalDateTime.of(2015, 7, 1, 15, 30)));
        assertNotNull(rental.getArrivalTime().get());
        assertEquals(LocalDateTime.of(2015, 7, 1, 15, 30), rentalRepository.loadBy(new Day(dateTimeHelper.currentDate()), new DayId(3)).get().getArrivalTime().get());
    }

    @Test
    @Transactional
    public void testPay() {
        Optional<PromotionAfter> holliKnolli = promotionAfterRepository.loadBy(new Name("HolliKnolli"));
        Rental rental = rentalRepository.pay(new Day(dateTimeHelper.currentDate()), new DayId(3), new PricePaidAfter("15.6"), holliKnolli, PaymentMethod.CASH);
        assertNotNull(rental.getPricePaidAfter());
        assertNotNull(rental.getPaymentMethod());
        assertTrue(rental.isFinished().get());
        assertEquals(new PricePaidAfter("15.6"), rentalRepository.loadBy(new Day(dateTimeHelper.currentDate()), new DayId(3)).get().getPricePaidAfter());
    }
}

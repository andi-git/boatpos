package org.boatpos.repository.core.model;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.repository.CommitmentRepository;
import org.boatpos.repository.api.repository.PromotionBeforeRepository;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.TestUtil;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RentalCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private RentalRepository rentalRepository;

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private PromotionBeforeRepository promotionBeforeRepository;

    @Inject
    private CommitmentRepository commitmentRepository;

    @Inject
    private TestUtil.RentalUtil rentalUtil;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    @Transactional
    public void testEntity() {
        assertEquals(1, new RentalCore(rentalUtil.createDummyRental().asEntity()).getDayId().get().intValue());
    }

    @Test
    @Transactional
    public void testDto() {
        assertEquals(1, new RentalCore(rentalUtil.createDummyRental().asDto()).getDayId().get().intValue());
    }

    @Test
    @Transactional
    public void testPersist() {
        rentalUtil.assertDatabaseRentalCount(11);
        Commitment commitment = commitmentRepository.loadBy(new Name("Ausweis")).get();
        Rental rental = rentalUtil.createDummyRentalBuilder()
                .add(new DomainId(null))
                .add(new Version(null))
                .add(boatRepository.loadBy(new ShortName("E")).get())
                .add(promotionBeforeRepository.loadBy(new Name("Fahr 3 zahl 2")).get())
                .add(commitment)
                .build();
        rental.clearCommitments();
        assertTrue(rental.getCommitments().isEmpty());
        rental.addCommitment(commitment);
        rental.persist();
        rentalUtil.assertDatabaseRentalCount(12);
    }

    @Test
    @Transactional
    public void testDelete() {
        rentalUtil.assertDatabaseRentalCount(11);
        assertFalse(rentalRepository.loadBy(new Day(dateTimeHelper.currentDate()), new DayId(1)).get().isDeleted().get());
        Rental rental = rentalRepository.loadBy(new Day(dateTimeHelper.currentDate()), new DayId(1)).get();
        rental.delete();
        assertTrue(rentalRepository.loadBy(new Day(dateTimeHelper.currentDate()), new DayId(1)).get().isDeleted().get());
        rentalUtil.assertDatabaseRentalCount(11);
        rental.undoDelete();
        assertFalse(rentalRepository.loadBy(new Day(dateTimeHelper.currentDate()), new DayId(1)).get().isDeleted().get());
        rentalUtil.assertDatabaseRentalCount(11);
    }

    @Test
    @Transactional
    public void testSomeGetterAndSetterForCodeCoverage() {
        Rental rental = new RentalCore(rentalUtil.createDummyRental().asEntity());
        rental.setPriceCalculatedBefore(new PriceCalculatedBefore(new BigDecimal("20.0")));
        rental.setPriceCalculatedAfter(new PriceCalculatedAfter(new BigDecimal("10.0")));
        rental.setPricePaidBefore(new PricePaidBefore(new BigDecimal("20.0")));
        rental.setPricePaidAfter(new PricePaidAfter(new BigDecimal("10.0")));
        assertEquals(new BigDecimal("20.0"), rental.getPriceCalculatedBefore().get());
        assertEquals(new BigDecimal("10.0"), rental.getPriceCalculatedAfter().get());
        assertEquals(new BigDecimal("20.0"), rental.getPricePaidBefore().get());
        assertEquals(new BigDecimal("10.0"), rental.getPricePaidAfter().get());
        rental.isFinished();
        rental.getPaymentMethodAfter();
        rental.getPaymentMethodBefore();
        rental.getPricePaidComplete();
    }
}

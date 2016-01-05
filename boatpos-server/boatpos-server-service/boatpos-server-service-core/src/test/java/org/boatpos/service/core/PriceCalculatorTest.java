package org.boatpos.service.core;

import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Promotion;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.repository.PromotionAfterRepository;
import org.boatpos.repository.api.repository.PromotionBeforeRepository;
import org.boatpos.repository.api.values.*;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("OctalInteger")
@RunWith(Arquillian.class)
public class PriceCalculatorTest {

    @Inject
    private PriceCalculator priceCalculator;

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private PromotionBeforeRepository promotionBeforeRepository;

    @Inject
    private PromotionAfterRepository promotionAfterRepository;

    @Test
    public void testCalculateDefault() throws Exception {
        Boat boat = createBoat();
        DepartureTime departureTime = new DepartureTime(LocalDateTime.of(2015, 7, 1, 12, 0));
        PriceCalculatedAfter priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 13, 0)), boat, Optional.empty());
        assertEquals(new BigDecimal("20.00"), priceCalculatedAfter.get());

        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 0, 0)), boat, Optional.empty());
        assertEquals(new BigDecimal("0.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 0)), boat, Optional.empty());
        assertEquals(new BigDecimal("0.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 5)), boat, Optional.empty());
        assertEquals(new BigDecimal("0.00"), priceCalculatedAfter.get());

        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 6)), boat, Optional.empty());
        assertEquals(new BigDecimal("10.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 30)), boat, Optional.empty());
        assertEquals(new BigDecimal("10.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 35)), boat, Optional.empty());
        assertEquals(new BigDecimal("10.00"), priceCalculatedAfter.get());

        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 36)), boat, Optional.empty());
        assertEquals(new BigDecimal("15.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 45)), boat, Optional.empty());
        assertEquals(new BigDecimal("15.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 50)), boat, Optional.empty());
        assertEquals(new BigDecimal("15.00"), priceCalculatedAfter.get());

        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 12, 51)), boat, Optional.empty());
        assertEquals(new BigDecimal("20.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 13, 00)), boat, Optional.empty());
        assertEquals(new BigDecimal("20.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 13, 05)), boat, Optional.empty());
        assertEquals(new BigDecimal("20.00"), priceCalculatedAfter.get());

        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 13, 06)), boat, Optional.empty());
        assertEquals(new BigDecimal("20.30"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 13, 27)), boat, Optional.empty());
        assertEquals(new BigDecimal("27.30"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 14, 05)), boat, Optional.empty());
        assertEquals(new BigDecimal("40.00"), priceCalculatedAfter.get());
    }

    private Boat createBoat() {
        return boatRepository.builder()
                .add(new DomainId(1L))
                .add(new Version(1))
                .add(Enabled.TRUE)
                .add(new Priority(1))
                .add(new Name("Boat"))
                .add(new ShortName("B"))
                .add(new PriceOneHour("20"))
                .add(new PriceThirtyMinutes("10"))
                .add(new PriceFortyFiveMinutes("15"))
                .add(new Count(20))
                .add(new PictureUrlSmall("small_____"))
                .add(new PictureUrlMedium("medium____"))
                .add(new PictureUrlLarge("large_____"))
                .build();
    }

    @Test
    public void testCalculateWithPromotionBefore() {
        Boat boat = createBoat();
        Optional<Promotion> promotionBefore = Optional.of(promotionBeforeRepository.builder()
                .add(new DomainId(2L))
                .add(new Version(1))
                .add(Enabled.TRUE)
                .add(new Priority(1))
                .add(new Name("Fahr 3 zahl 2"))
                .add(new TimeCredit(180))
                .add(new FormulaPrice("priceOneHour * 2"))
                .build());
        DepartureTime departureTime = new DepartureTime(LocalDateTime.of(2015, 7, 1, 12, 0));
        PriceCalculatedAfter priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 13, 00)), boat, promotionBefore);
        assertEquals(new BigDecimal("0.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 14, 00)), boat, promotionBefore);
        assertEquals(new BigDecimal("0.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 15, 05)), boat, promotionBefore);
        assertEquals(new BigDecimal("0.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 15, 06)), boat, promotionBefore);
        assertEquals(new BigDecimal("0.30"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 16, 05)), boat, promotionBefore);
        assertEquals(new BigDecimal("20.00"), priceCalculatedAfter.get());
    }

    @Test
    public void testCalculateWithPromotionAfter() {
        Boat boat = createBoat();
        Optional<Promotion> promotionAfter = Optional.of(promotionAfterRepository.builder()
                .add(new DomainId(2L))
                .add(new Version(1))
                .add(Enabled.TRUE)
                .add(new Priority(1))
                .add(new Name("HolliKnolli"))
                .add(new FormulaPrice("price / 2"))
                .build());
        DepartureTime departureTime = new DepartureTime(LocalDateTime.of(2015, 7, 1, 12, 0));
        PriceCalculatedAfter priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 13, 05)), boat, promotionAfter);
        assertEquals(new BigDecimal("10.00"), priceCalculatedAfter.get());
        priceCalculatedAfter = priceCalculator.calculate(departureTime, new ArrivalTime(LocalDateTime.of(2015, 7, 1, 13, 28)), boat, promotionAfter);
        assertEquals(new BigDecimal("13.80"), priceCalculatedAfter.get());
    }

    @Test
    public void testCalculatePricePromotionBefore() {
        PromotionBefore promotionBefore = promotionBeforeRepository.builder()
                .add(new DomainId(2L))
                .add(new Version(1))
                .add(Enabled.TRUE)
                .add(new Priority(1))
                .add(new Name("Fahr 3 zahl 2"))
                .add(new TimeCredit(180))
                .add(new FormulaPrice("priceOneHour * 2"))
                .build();
        assertEquals(new BigDecimal("30.60"), priceCalculator.calculate(new PriceOneHour("15.3"), promotionBefore).get());
    }

    @Test
    public void testRoundCent() {
        assertCentRound(new PriceCalculatedAfter("3.00"), new PriceCalculatedAfter("3.00"));
        assertCentRound(new PriceCalculatedAfter("3.11"), new PriceCalculatedAfter("3.10"));
        assertCentRound(new PriceCalculatedAfter("3.22"), new PriceCalculatedAfter("3.20"));
        assertCentRound(new PriceCalculatedAfter("3.33"), new PriceCalculatedAfter("3.30"));
        assertCentRound(new PriceCalculatedAfter("3.44"), new PriceCalculatedAfter("3.40"));
        assertCentRound(new PriceCalculatedAfter("3.55"), new PriceCalculatedAfter("3.60"));
        assertCentRound(new PriceCalculatedAfter("3.66"), new PriceCalculatedAfter("3.70"));
        assertCentRound(new PriceCalculatedAfter("3.77"), new PriceCalculatedAfter("3.80"));
        assertCentRound(new PriceCalculatedAfter("3.88"), new PriceCalculatedAfter("3.90"));
        assertCentRound(new PriceCalculatedAfter("3.99"), new PriceCalculatedAfter("4.00"));
    }

    private void assertCentRound(PriceCalculatedAfter priceCalculatedAfter, PriceCalculatedAfter expected) {
        assertEquals(expected, priceCalculator.round(priceCalculatedAfter));
    }
}

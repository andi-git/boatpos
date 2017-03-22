package org.boatpos.service.core;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.model.Promotion;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.repository.BoatRepository;
import org.boatpos.domain.api.repository.PromotionAfterRepository;
import org.boatpos.domain.api.repository.PromotionBeforeRepository;
import org.boatpos.domain.api.values.ArrivalTime;
import org.boatpos.domain.api.values.Count;
import org.boatpos.domain.api.values.DepartureTime;
import org.boatpos.domain.api.values.FormulaPrice;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.PriceCalculatedAfter;
import org.boatpos.domain.api.values.PriceFortyFiveMinutes;
import org.boatpos.domain.api.values.PriceOneHour;
import org.boatpos.domain.api.values.PriceThirtyMinutes;
import org.boatpos.domain.api.values.ShortName;
import org.boatpos.domain.api.values.TimeCredit;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
                .add(new PictureUrlThumb("small_____"))
                .add(new PictureUrl("large_____"))
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

    @Test
    public void testProductionOg20160416() {
        Boat boat = mock(Boat.class);
        when(boat.getPriceOneHour()).thenReturn(new PriceOneHour("14.00"));
        when(boat.getPriceFortyFiveMinutes()).thenReturn(new PriceFortyFiveMinutes("12.00"));
        when(boat.getPriceThirtyMinutes()).thenReturn(new PriceThirtyMinutes("8.00"));
        assertCalculation("00.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 12, 0), boat);
        assertCalculation("00.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 12, 4), boat);
        assertCalculation("00.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 12, 5), boat);
        assertCalculation("08.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 12, 6), boat);
        assertCalculation("08.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 12, 35), boat);
        assertCalculation("12.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 12, 36), boat);
        assertCalculation("12.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 12, 50), boat);
        assertCalculation("14.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 12, 51), boat);
        assertCalculation("14.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 5), boat);
        assertCalculation("15.20", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 10), boat);
        assertCalculation("16.30", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 15), boat);
        assertCalculation("17.50", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 20), boat);
        assertCalculation("18.70", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 25), boat);
        assertCalculation("19.80", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 30), boat);
        assertCalculation("21.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 35), boat);
        assertCalculation("22.20", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 40), boat);
        assertCalculation("23.30", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 45), boat);
        assertCalculation("24.50", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 50), boat);
        assertCalculation("25.70", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 13, 55), boat);
        assertCalculation("26.80", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 14, 00), boat);
        assertCalculation("28.00", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 14, 05), boat);
        assertCalculation("29.20", new DepartureTime(2016, 4, 16, 12, 0), new ArrivalTime(2016, 4, 16, 14, 10), boat);
    }

    private void assertCalculation(String expected, DepartureTime departureTime, ArrivalTime arrivalTime, Boat boat) {
        PriceCalculatedAfter calculate = priceCalculator.calculate(departureTime, arrivalTime, boat, Optional.empty());
        assertEquals(new BigDecimal(expected), calculate.get());
    }
}

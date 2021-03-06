package org.boatpos.domain.core;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.qualifiers.Current;
import org.boatpos.domain.api.builder.BoatBuilder;
import org.boatpos.domain.api.builder.CommitmentBuilder;
import org.boatpos.domain.api.builder.HolidayBuilder;
import org.boatpos.domain.api.builder.PrinterBuilder;
import org.boatpos.domain.api.builder.PromotionAfterBuilder;
import org.boatpos.domain.api.builder.PromotionBeforeBuilder;
import org.boatpos.domain.api.builder.RentalBuilder;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.model.Commitment;
import org.boatpos.domain.api.model.Holiday;
import org.boatpos.domain.api.model.Printer;
import org.boatpos.domain.api.model.PromotionAfter;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.repository.BoatRepository;
import org.boatpos.domain.api.repository.CommitmentRepository;
import org.boatpos.domain.api.repository.HolidayRepository;
import org.boatpos.domain.api.repository.PrinterRepository;
import org.boatpos.domain.api.repository.PromotionAfterRepository;
import org.boatpos.domain.api.repository.PromotionBeforeRepository;
import org.boatpos.domain.api.repository.RentalRepository;
import org.boatpos.domain.api.values.Count;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.DayId;
import org.boatpos.domain.api.values.DepartureTime;
import org.boatpos.domain.api.values.FormulaPrice;
import org.boatpos.domain.api.values.IpAddress;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.Paper;
import org.boatpos.domain.api.values.PriceFortyFiveMinutes;
import org.boatpos.domain.api.values.PriceOneHour;
import org.boatpos.domain.api.values.PriceThirtyMinutes;
import org.boatpos.domain.api.values.ShortName;
import org.boatpos.domain.api.values.TimeCredit;
import org.boatpos.model.BoatEntity;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.api.bean.HolidayBean;
import org.boatpos.service.api.bean.PrinterBean;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@Dependent
public class TestUtil {

    @Dependent
    public static class BoatUtil {

        @Inject
        @Current
        private EntityManager entityManager;

        @Inject
        private BoatRepository boatRepository;

        public BoatEntity getBoatByShortName(String shortName) {
            return entityManager.createNamedQuery("boat.getByShortName", BoatEntity.class).setParameter("shortName", shortName).getSingleResult();
        }

        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        public void assertDatabaseBoatCount(int count) {
            assertEquals(count, ((BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM Boat").getSingleResult()).intValue());
        }

        public Boat createDummyBoat() {
            return createDummyBoatBuilder().build();
        }

        public Boat createDummyBoatIdAndVersionNull() {
            return createDummyBoatBuilder(new DomainId(null), new Version(null)).build();
        }

        public BoatBuilder createDummyBoatBuilder(DomainId id, Version version) {
            return boatRepository.builder()
                    .add(id)
                    .add(version)
                    .add(Enabled.TRUE)
                    .add(new Priority(1))
                    .add(new Name("E-Boot"))
                    .add(new ShortName("E"))
                    .add(new PriceOneHour("16.8"))
                    .add(new PriceThirtyMinutes("9.5"))
                    .add(new PriceFortyFiveMinutes("14.3"))
                    .add(new Count(22))
                    .add(new KeyBinding('x'))
                    .add(new PictureUrlThumb("small_____"))
                    .add(new PictureUrl("large_____"));
        }

        public BoatBuilder createDummyBoatBuilder() {
            return createDummyBoatBuilder(new DomainId(1L), new Version(1));
        }

        public BoatBean createDummyBoatBean() {
            BoatBean boatBean = new BoatBean();
            boatBean.setId(1L);
            boatBean.setVersion(1);
            boatBean.setName("E-Boot");
            boatBean.setShortName("E");
            boatBean.setPriceOneHour(new BigDecimal("16.8"));
            boatBean.setPriceThirtyMinutes(new BigDecimal("9.5"));
            boatBean.setPriceFortyFiveMinutes(new BigDecimal("14.3"));
            boatBean.setCount(22);
            boatBean.setEnabled(true);
            boatBean.setPriority(1);
            boatBean.setKeyBinding('x');
            boatBean.setPictureUrlThumb("small_____");
            boatBean.setPictureUrl("large_____");
            return boatBean;
        }
    }

    @Dependent
    public static class CommitmentUtil {

        @Inject
        @Current
        private EntityManager entityManager;

        @Inject
        private CommitmentRepository commitmentRepository;

        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        public void assertDatabaseCommitmentCount(int count) {
            assertEquals(count, ((BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM Commitment").getSingleResult()).intValue());
        }

        public Commitment createDummyCommitment() {
            return createDummyCommitmentBuilder().build();
        }

        public CommitmentBuilder createDummyCommitmentBuilder() {
            return commitmentRepository.builder()
                    .add(new DomainId(1L))
                    .add(new Version(1))
                    .add(Enabled.TRUE)
                    .add(new Priority(1))
                    .add(new Name("Pass"))
                    .add(Paper.TRUE)
                    .add(new KeyBinding('x'))
                    .add(new PictureUrl("p"))
                    .add(new PictureUrlThumb("t"));
        }
    }

    @Dependent
    public static class PromotionBeforeUtil {

        @Inject
        @Current
        private EntityManager entityManager;

        @Inject
        private PromotionBeforeRepository promotionBeforeRepository;

        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        public void assertDatabasePromotionCount(int count) {
            assertEquals(count, ((BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM Promotion").getSingleResult()).intValue());
        }

        public PromotionBefore createDummyPromotionBefore() {
            return createDummyPromotionBeforeBuilder().build();
        }

        public PromotionBeforeBuilder createDummyPromotionBeforeBuilder() {
            return promotionBeforeRepository.builder()
                    .add(new DomainId(1L))
                    .add(new Version(1))
                    .add(Enabled.TRUE)
                    .add(new Priority(1))
                    .add(new Name("Tageskarte"))
                    .add(new FormulaPrice("some formula * 2"))
                    .add(new TimeCredit(1200))
                    .add(new KeyBinding('x'))
                    .add(new PictureUrl("p"))
                    .add(new PictureUrlThumb("t"));
        }
    }

    @Dependent
    public static class PromotionAfterUtil {

        @Inject
        @Current
        private EntityManager entityManager;

        @Inject
        private PromotionAfterRepository promotionAfterRepository;

        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        public void assertDatabasePromotionCount(int count) {
            assertEquals(count, ((BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM Promotion").getSingleResult()).intValue());
        }

        public PromotionAfter createDummyPromotionAfter() {
            return createDummyPromotionAfterBuilder().build();
        }

        public PromotionAfterBuilder createDummyPromotionAfterBuilder() {
            return promotionAfterRepository.builder()
                    .add(new DomainId(1L))
                    .add(new Version(1))
                    .add(Enabled.TRUE)
                    .add(new Priority(1))
                    .add(new Name("Alles Gratis"))
                    .add(new FormulaPrice("some formula * 2"))
                    .add(new KeyBinding('x'))
                    .add(new PictureUrl("p"))
                    .add(new PictureUrlThumb("t"));
        }
    }

    @Dependent
    public static class RentalUtil {

        @Inject
        @Current
        private EntityManager entityManager;

        @Inject
        private DateTimeHelper dateTimeHelper;

        @Inject
        private RentalRepository rentalRepository;

        @Inject
        private BoatRepository boatRepository;

        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        public void assertDatabaseRentalCount(int count) {
            assertEquals(count, ((BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM Rental").getSingleResult()).intValue());
        }

        public Rental createDummyRental() {
            return createDummyRentalBuilder().build();
        }

        public RentalBuilder createDummyRentalBuilder() {
            return rentalRepository.builder()
                    .add(new DomainId(1L))
                    .add(new Version(1))
                    .add(new Day(dateTimeHelper.currentDate()))
                    .add(new DayId(1))
                    .add(new DepartureTime(dateTimeHelper.currentTime()))
                    .add(boatRepository.loadBy(new ShortName("E")).get());
        }
    }

    @Dependent
    public static class HolidayUtil {

        @Inject
        @Current
        private EntityManager entityManager;

        @Inject
        private DateTimeHelper dateTimeHelper;

        @Inject
        private HolidayRepository holidayRepository;

        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        public void assertDatabaseHolidayCount(int count) {
            assertEquals(count, ((BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM holiday").getSingleResult()).intValue());
        }

        public Holiday createDummyHoliday() {
            return createDummyHolidayBuilder().build();
        }

        public HolidayBuilder createDummyHolidayBuilder() {
            return holidayRepository.builder()
                    .add(new Version(1))
                    .add(new Day(LocalDate.of(2015, 5, 1)))
                    .add(new Name("Staatsfeiertag"));
        }

        public HolidayBean createDummyHolidayBean() {
            return new HolidayBean(1L, 1, LocalDate.of(2015, 5, 1), "Staatsfeiertag");
        }
    }


    @Dependent
    public static class PrinterUtil {

        @Inject
        @Current
        private EntityManager entityManager;

        @Inject
        private DateTimeHelper dateTimeHelper;

        @Inject
        private PrinterRepository printerRepository;

        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        public void assertDatabasePrinterCount(int count) {
            assertEquals(count, ((BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM printer").getSingleResult()).intValue());
        }

        public Printer createDummyPrinter() {
            return createDummyPrinterBuilder().build();
        }

        public PrinterBuilder createDummyPrinterBuilder() {
            return printerRepository.builder()
                    .add(new Version(1))
                    .add(new IpAddress("192.168.0.11"))
                    .add(new Priority(1));
        }

        public PrinterBean createDummyPrinterBean() {
            return new PrinterBean(1L, 1, "192.168.0.11");
        }
    }
}

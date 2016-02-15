package org.boatpos.test.model;

import com.google.common.collect.Sets;
import org.boatpos.model.*;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Create a sample-database for tests.
 */
@SuppressWarnings("SqlNoDataSourceInspection")
@Dependent
public class SampleDatabaseCreator {

    @Resource
    private UserTransaction userTransaction;

    @SuppressWarnings("OctalInteger")
    public void fillDatabase(EntityManager em) {
        // clear cache and reset sequence for ids
        em.flush();
        em.clear();
        em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();

        BoatEntity boat1 = new BoatEntity(null, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, new HashSet<>(), true, "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/thumbs/eboot_2.jpg", "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/thumbs/eboot_2.jpg", 'a');
        BoatEntity boat2 = new BoatEntity(null, 1, "Tretboot klein", "T2", new BigDecimal("12.8"), new BigDecimal("7.5"), new BigDecimal("11.3"), 6, 2, new HashSet<>(), true, "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/tretboot_2.jpg", "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/thumbs/tretboot_2.jpg", 'b');
        BoatEntity boat3 = new BoatEntity(null, 1, "Tretboot groß", "T4", new BigDecimal("12.8"), new BigDecimal("7.5"), new BigDecimal("11.3"), 9, 3, new HashSet<>(), true, "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/tretboot_4.jpg", "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/thumbs/tretboot_4.jpg", 'c');
        BoatEntity boat4 = new BoatEntity(null, 1, "Tretboot Rutsche", "TR", new BigDecimal("13.8"), new BigDecimal("7.8"), new BigDecimal("11.7"), 3, 4, new HashSet<>(), true, "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/tretboot_rutsche.jpg", "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/thumbs/tretboot_rutsche.jpg", 'd');
        BoatEntity boat5 = new BoatEntity(null, 1, "Liegeboot", "L", new BigDecimal("9.8"), new BigDecimal("9.8"), new BigDecimal("9.8"), 5, 5, new HashSet<>(), true, "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/ruderboot.jpg", "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/thumbs/ruderboot.jpg", 'e');
        BoatEntity boat6 = new BoatEntity(null, 1, "Pönt", "P", new BigDecimal("9.8"), new BigDecimal("9.8"), new BigDecimal("9.8"), 5, 6, new HashSet<>(), false, "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/ruderboot.jpg", "http://www.eppel-boote.at/cms/images/stories/pics_eppel/boote/thumbs/ruderboot.jpg", 'f');
        PromotionEntity promotion1 = new PromotionBeforeEntity(null, 1, "Fahr 3 zahl 2", 180, "priceOneHour * 2", new HashSet<>(), 1, true, 'k', "", "");
        PromotionEntity promotion2 = new PromotionBeforeEntity(null, 1, "Fahr 5 zahl 3", 300, "priceOneHour * 3", new HashSet<>(), 2, true, 'l', "", "");
        PromotionEntity promotion3 = new PromotionBeforeEntity(null, 1, "Tageskarte", 480, "priceOneHour * 5", new HashSet<>(), 3, false, 'm', "", "");
        PromotionEntity promotion4 = new PromotionAfterEntity(null, 1, "HolliKnolli", "price / 2", new HashSet<>(), 100, true, 'n', "", "");
        PromotionEntity promotion5 = new PromotionAfterEntity(null, 1, "Sommerferien", "price / 3", new HashSet<>(), 101, false, 'o', "", "");
        CommitmentEntity commitment1 = new CommitmentEntity(null, 1, "Ausweis", true, new HashSet<>(), 1, true, 'A', "", "");
        CommitmentEntity commitment2 = new CommitmentEntity(null, 1, "EUR 50,-", false, new HashSet<>(), 2, true, 'B', "", "");
        CommitmentEntity commitment3 = new CommitmentEntity(null, 1, "EUR 100,-", false, new HashSet<>(), 3, true, 'C', "", "");
        CommitmentEntity commitment4 = new CommitmentEntity(null, 1, "Schlüssel", true, new HashSet<>(), 4, true, 'D', "", "");
        CommitmentEntity commitment5 = new CommitmentEntity(null, 1, "Diverses", true, new HashSet<>(), 5, true, 'E', "", "");
        CommitmentEntity commitment6 = new CommitmentEntity(null, 1, "Kinderwagen", true, new HashSet<>(), 5, false, 'F', "", "");
        RentalEntity rental1 = new RentalEntity(null, 1, 1, LocalDate.of(2015, 7, 1), boat1, LocalDateTime.of(2015, 7, 1, 12, 00), LocalDateTime.of(2015, 7, 1, 14, 10), new BigDecimal("32.00"), new BigDecimal("1.60"), new BigDecimal("32.00"), new BigDecimal("1.60"), true, false, false, PaymentMethod.CARD, promotion1, Sets.newHashSet(commitment1, commitment2));
        RentalEntity rental2 = new RentalEntity(null, 1, 2, LocalDate.of(2015, 7, 1), boat2, LocalDateTime.of(2015, 7, 1, 12, 14), null, null, null, null, null, false, true, false, null, null, Sets.newHashSet(commitment1));
        RentalEntity rental3 = new RentalEntity(null, 1, 3, LocalDate.of(2015, 7, 1), boat1, LocalDateTime.of(2015, 7, 1, 12, 15), null, null, null, null, null, false, false, false, null, null, Sets.newHashSet(commitment1));
        RentalEntity rental4 = new RentalEntity(null, 1, 4, LocalDate.of(2015, 7, 1), boat2, LocalDateTime.of(2015, 7, 1, 12, 20), null, null, null, null, null, false, false, false, null, null, null);
        RentalEntity rental5 = new RentalEntity(null, 1, 5, LocalDate.of(2015, 7, 1), boat3, LocalDateTime.of(2015, 7, 1, 12, 30), LocalDateTime.of(2015, 7, 1, 13, 37), null, new BigDecimal("12.60"), null, new BigDecimal("12.60"), true, false, false, PaymentMethod.CASH, null, Sets.newHashSet(commitment2));
        RentalEntity rental6 = new RentalEntity(null, 1, 6, LocalDate.of(2014, 7, 1), boat1, LocalDateTime.of(2014, 7, 1, 12, 00), LocalDateTime.of(2014, 7, 1, 13, 00), null, new BigDecimal("20.00"), null, new BigDecimal("20.00"), true, false, false, PaymentMethod.CASH, null, new HashSet<>());
        RentalEntity rental7 = new RentalEntity(null, 1, 6, LocalDate.of(2014, 7, 2), boat1, LocalDateTime.of(2014, 7, 2, 12, 00), LocalDateTime.of(2014, 7, 2, 13, 00), null, new BigDecimal("20.00"), null, new BigDecimal("20.00"), true, false, false, PaymentMethod.CASH, null, new HashSet<>());
        RentalEntity rental8 = new RentalEntity(null, 1, 6, LocalDate.of(2015, 6, 1), boat1, LocalDateTime.of(2015, 6, 1, 12, 00), LocalDateTime.of(2015, 6, 1, 13, 00), null, new BigDecimal("20.00"), null, new BigDecimal("20.00"), true, false, false, PaymentMethod.CASH, null, new HashSet<>());
        RentalEntity rental9 = new RentalEntity(null, 1, 6, LocalDate.of(2015, 6, 1), boat1, LocalDateTime.of(2015, 6, 1, 13, 00), LocalDateTime.of(2015, 6, 1, 14, 00), null, new BigDecimal("20.00"), null, new BigDecimal("20.00"), true, false, false, PaymentMethod.CASH, null, new HashSet<>());
        RentalEntity rental10 = new RentalEntity(null, 1, 6, LocalDate.of(2015, 7, 31), boat1, LocalDateTime.of(2015, 7, 31, 12, 00), LocalDateTime.of(2015, 7, 31, 13, 00), null, new BigDecimal("20.00"), null, new BigDecimal("20.00"), true, false, false, PaymentMethod.CASH, null, new HashSet<>());
        RentalEntity rental11 = new RentalEntity(null, 1, 6, LocalDate.of(2015, 7, 31), boat1, LocalDateTime.of(2015, 7, 31, 13, 00), LocalDateTime.of(2015, 7, 31, 14, 00), null, new BigDecimal("20.00"), null, new BigDecimal("20.00"), true, false, false, PaymentMethod.CASH, null, new HashSet<>());


        boat1.getRentals().add(rental1);
        boat1.getRentals().add(rental3);
        boat1.getRentals().add(rental6);
        boat1.getRentals().add(rental7);
        boat1.getRentals().add(rental8);
        boat1.getRentals().add(rental9);
        boat1.getRentals().add(rental10);
        boat1.getRentals().add(rental11);
        promotion1.getRentals().add(rental1);
        commitment1.getRentals().add(rental1);
        commitment1.getRentals().add(rental2);
        commitment1.getRentals().add(rental3);
        commitment2.getRentals().add(rental1);
        commitment2.getRentals().add(rental5);

        em.persist(boat1);
        em.persist(boat2);
        em.persist(boat3);
        em.persist(boat4);
        em.persist(boat5);
        em.persist(boat6);
        em.persist(promotion1);
        em.persist(promotion2);
        em.persist(promotion3);
        em.persist(promotion4);
        em.persist(promotion5);
        em.persist(commitment1);
        em.persist(commitment2);
        em.persist(commitment3);
        em.persist(commitment4);
        em.persist(commitment5);
        em.persist(commitment6);
        em.persist(rental1);
        em.persist(rental2);
        em.persist(rental3);
        em.persist(rental4);
        em.persist(rental5);
        em.persist(rental6);
        em.persist(rental7);
        em.persist(rental8);
        em.persist(rental9);
        em.persist(rental10);
        em.persist(rental11);
        em.flush();
    }


    public void clearDatabase(EntityManager em) throws Exception {
        if (Status.STATUS_ACTIVE == userTransaction.getStatus()) {
            // clear all tables
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
            em.createNativeQuery("DELETE FROM boat").executeUpdate();
            em.createNativeQuery("DELETE FROM rental").executeUpdate();
            em.createNativeQuery("DELETE FROM promotion").executeUpdate();
            em.createNativeQuery("DELETE FROM commitment").executeUpdate();
            em.createNativeQuery("DELETE FROM boat_rental").executeUpdate();
            em.createNativeQuery("DELETE FROM rental_commitment").executeUpdate();
            em.createNativeQuery("DELETE FROM promotion_rental").executeUpdate();
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
            // reset sequence for ids and clear cache
            em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
            em.flush();
            em.clear();
        }
    }
}

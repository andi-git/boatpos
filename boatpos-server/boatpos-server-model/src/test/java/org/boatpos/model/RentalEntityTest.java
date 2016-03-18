package org.boatpos.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@RunWith(Arquillian.class)
public class RentalEntityTest extends JavaBeanTest<RentalEntity> {

    @Test
    public void testConstructor() {
        new RentalEntity(null, 1, 1, LocalDate.now(), null, LocalDateTime.now(), null, null, null, null, null, false, false, false, null, null, null, null);
        RentalEntity rentalEntity = RentalEntity.builder()
                .setDayId(1)
                .setBoat(new BoatEntity())
                .setCommitments(new HashSet<>())
                .setDepartTime(LocalDateTime.now())
                .setPromotion(Optional.empty())
                .build();
        rentalEntity.setPaymentMethodBefore(PaymentMethod.CARD);
        rentalEntity.setPaymentMethodAfter(PaymentMethod.CARD);
        rentalEntity.getPaymentMethodBefore();
        rentalEntity.getPaymentMethodAfter();
    }

    @Test
    public void testAdditional() {
        new RentalEntity();
    }
}
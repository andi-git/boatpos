package org.boatpos.api.dto;

import com.google.common.collect.Sets;
import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalTest extends JavaBeanTest<Rental> {

    @Override
    protected Class<Rental> getType() {
        return Rental.class;
    }

    @Test
    public void testConstructor() {
        new Rental(1L, 1, 1, LocalDate.now(), BoatTest.createBoatE(), LocalDateTime.now(), null, null, false, false, false, PromotionAfterTest.createPromotionAfter(), Sets.newHashSet(CommitmentTest.createCommitmentAusweis()));
    }
}
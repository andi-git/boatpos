package org.boatpos.service.api.bean;

import com.google.common.collect.Sets;
import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalBeanTest extends JavaBeanTest<RentalBean> {

    @Override
    protected Class<RentalBean> getType() {
        return RentalBean.class;
    }

    @Test
    public void testConstructor() {
        new RentalBean(1L, 1, 1, LocalDate.now(), BoatBeanTest.createBoatE(), LocalDateTime.now(), null, null, null, null, null, false, false, false, null, PromotionAfterBeanTest.createPromotionAfter(), Sets.newHashSet(CommitmentBeanTest.createCommitmentAusweis()), 0, 0, null, null);
    }
}
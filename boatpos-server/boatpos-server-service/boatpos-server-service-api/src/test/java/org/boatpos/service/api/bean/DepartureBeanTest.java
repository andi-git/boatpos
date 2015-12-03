package org.boatpos.service.api.bean;

import com.google.common.collect.Sets;
import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DepartureBeanTest extends JavaBeanTest<DepartureBean> {

    @Override
    protected Class<DepartureBean> getType() {
        return DepartureBean.class;
    }

    @Test
    public void testEqualsAndHashCode() {
        DepartureBean departureBean1 = new DepartureBean(BoatBeanTest.createBoatE(), Sets.newHashSet(CommitmentBeanTest.createCommitmentAusweis(), CommitmentBeanTest.createCommitment50Euro()), PromotionBeanAfterBeanTest.createPromotionAfter());
        DepartureBean departureBean2 = new DepartureBean(BoatBeanTest.createBoatE(), Sets.newHashSet(CommitmentBeanTest.createCommitmentAusweis(), CommitmentBeanTest.createCommitment50Euro()), PromotionBeanAfterBeanTest.createPromotionAfter());
        DepartureBean departureBean3 = new DepartureBean(BoatBeanTest.createBoatT(), Sets.newHashSet(CommitmentBeanTest.createCommitmentAusweis(), CommitmentBeanTest.createCommitment50Euro()), PromotionBeanAfterBeanTest.createPromotionAfter());
        DepartureBean departureBean4 = new DepartureBean(BoatBeanTest.createBoatE(), Sets.newHashSet(CommitmentBeanTest.createCommitmentAusweis()), PromotionBeanAfterBeanTest.createPromotionAfter());
        assertEquals(departureBean1, departureBean2);
        assertNotEquals(departureBean1, departureBean3);
        assertNotEquals(departureBean1, departureBean4);
        assertEquals(departureBean1.hashCode(), departureBean2.hashCode());
        assertNotEquals(departureBean1.hashCode(), departureBean3.hashCode());
        assertNotEquals(departureBean1.hashCode(), departureBean4.hashCode());
    }
}
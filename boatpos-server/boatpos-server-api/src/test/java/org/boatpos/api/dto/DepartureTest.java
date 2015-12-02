package org.boatpos.api.dto;

import com.google.common.collect.Sets;
import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DepartureTest extends JavaBeanTest<Departure> {

    @Override
    protected Class<Departure> getType() {
        return Departure.class;
    }

    @Test
    public void testEqualsAndHashCode() {
        Departure departure1 = new Departure(BoatTest.createBoatE(), Sets.newHashSet(CommitmentTest.createCommitmentAusweis(), CommitmentTest.createCommitment50Euro()), PromotionAfterTest.createPromotionAfter());
        Departure departure2 = new Departure(BoatTest.createBoatE(), Sets.newHashSet(CommitmentTest.createCommitmentAusweis(), CommitmentTest.createCommitment50Euro()), PromotionAfterTest.createPromotionAfter());
        Departure departure3 = new Departure(BoatTest.createBoatT(), Sets.newHashSet(CommitmentTest.createCommitmentAusweis(), CommitmentTest.createCommitment50Euro()), PromotionAfterTest.createPromotionAfter());
        Departure departure4 = new Departure(BoatTest.createBoatE(), Sets.newHashSet(CommitmentTest.createCommitmentAusweis()), PromotionAfterTest.createPromotionAfter());
        assertEquals(departure1, departure2);
        assertNotEquals(departure1, departure3);
        assertNotEquals(departure1, departure4);
        assertEquals(departure1.hashCode(), departure2.hashCode());
        assertNotEquals(departure1.hashCode(), departure3.hashCode());
        assertNotEquals(departure1.hashCode(), departure4.hashCode());
    }
}
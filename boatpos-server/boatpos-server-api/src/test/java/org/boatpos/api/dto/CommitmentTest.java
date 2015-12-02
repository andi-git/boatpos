package org.boatpos.api.dto;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommitmentTest extends JavaBeanTest<Commitment> {

    @Override
    protected Class<Commitment> getType() {
        return Commitment.class;
    }

    @Test
    public void testConstructor() {
        createCommitmentAusweis();
        createCommitment50Euro();
    }

    public static Commitment createCommitmentAusweis() {
        return new Commitment(1L, 1, "Ausweis", true, 1);
    }

    public static Commitment createCommitment50Euro() {
        return new Commitment(2L, 1, "EUR 50,-", false, 2);
    }

    @Test
    public void testEqualsOfAbstractDtoBasedOnEntity() {
        Commitment commitment1 = createCommitmentAusweis();
        Commitment commitment2 = createCommitmentAusweis();
        Commitment commitment3 = createCommitment50Euro();
        assertTrue(commitment1.equals(commitment2));
        assertTrue(commitment2.equals(commitment1));
        assertFalse(commitment1.equals(commitment3));
        assertFalse(commitment2.equals(commitment3));
        assertFalse(commitment3.equals(commitment1));
        assertFalse(commitment3.equals(commitment2));
    }

    @Test
    public void testHashCodeOfAbstractDtoBasedOnEntity() {
        Commitment commitment1 = createCommitmentAusweis();
        Commitment commitment2 = createCommitmentAusweis();
        Commitment commitment3 = createCommitment50Euro();
        assertEquals(commitment1.hashCode(), commitment2.hashCode());
        assertNotEquals(commitment1.hashCode(), commitment3.hashCode());
        assertNotEquals(commitment2.hashCode(), commitment3.hashCode());
    }

    @Test
    public void testToStringOfAbstractDto() {
        assertEquals("{\"name\":\"Ausweis\",\"paper\":true,\"id\":1,\"version\":1}", createCommitmentAusweis().toString());
    }
}
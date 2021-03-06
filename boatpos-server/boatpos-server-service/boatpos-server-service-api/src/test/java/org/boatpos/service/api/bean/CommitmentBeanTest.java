package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommitmentBeanTest extends JavaBeanTest<CommitmentBean> {

    @Test
    public void testConstructor() {
        createCommitmentAusweis();
        createCommitment50Euro();
    }

    public static CommitmentBean createCommitmentAusweis() {
        return new CommitmentBean(1L, 1, "Ausweis", true, 1, true, 'a', "", "");
    }

    public static CommitmentBean createCommitment50Euro() {
        return new CommitmentBean(2L, 1, "EUR 50,-", false, 2, true, 'a', "", "");
    }

    @Test
    public void testEqualsOfAbstractDtoBasedOnEntity() {
        CommitmentBean commitmentBean1 = createCommitmentAusweis();
        CommitmentBean commitmentBean2 = createCommitmentAusweis();
        CommitmentBean commitmentBean3 = createCommitment50Euro();
        assertTrue(commitmentBean1.equals(commitmentBean2));
        assertTrue(commitmentBean2.equals(commitmentBean1));
        assertFalse(commitmentBean1.equals(commitmentBean3));
        assertFalse(commitmentBean2.equals(commitmentBean3));
        assertFalse(commitmentBean3.equals(commitmentBean1));
        assertFalse(commitmentBean3.equals(commitmentBean2));
    }

    @Test
    public void testHashCodeOfAbstractDtoBasedOnEntity() {
        CommitmentBean commitmentBean1 = createCommitmentAusweis();
        CommitmentBean commitmentBean2 = createCommitmentAusweis();
        CommitmentBean commitmentBean3 = createCommitment50Euro();
        assertEquals(commitmentBean1.hashCode(), commitmentBean2.hashCode());
        assertNotEquals(commitmentBean1.hashCode(), commitmentBean3.hashCode());
        assertNotEquals(commitmentBean2.hashCode(), commitmentBean3.hashCode());
    }

    @Test
    public void testToStringOfAbstractDto() {
        assertEquals("{\"name\":\"Ausweis\",\"paper\":true,\"enabled\":true,\"priority\":1,\"pictureUrlThumb\":\"\",\"pictureUrl\":\"\",\"id\":1,\"version\":1}", createCommitmentAusweis().toString());
    }
}
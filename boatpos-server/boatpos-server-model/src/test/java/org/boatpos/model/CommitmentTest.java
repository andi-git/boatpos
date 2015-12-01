package org.boatpos.model;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.HashSet;

@RunWith(Arquillian.class)
public class CommitmentTest extends JavaBeanTest<Commitment> {

    @Test
    public void testConstructor() {
        new Commitment(null, 1, "commitment", false, new HashSet<>());
    }

    @Override
    protected Class<Commitment> getType() {
        return Commitment.class;
    }
}
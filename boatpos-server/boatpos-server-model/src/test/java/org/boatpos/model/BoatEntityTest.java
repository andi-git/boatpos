package org.boatpos.model;

import org.boatpos.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.HashSet;

@RunWith(Arquillian.class)
public class BoatEntityTest extends JavaBeanTest<BoatEntity> {

    @Test
    public void testConstructor() {
        new BoatEntity(-1L, 1, "boat", "B", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 20, 0, new HashSet<>(), true, "p", "t", 'a');
    }

    @Override
    protected Class<BoatEntity> getType() {
        return BoatEntity.class;
    }
}
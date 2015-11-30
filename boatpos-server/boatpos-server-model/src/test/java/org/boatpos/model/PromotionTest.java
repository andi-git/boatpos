package org.boatpos.model;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class PromotionTest extends JavaBeanTest<Promotion> {

    @Test
    public void testConstructor() {
        new Promotion(null, 1, "Fahr 3 zahl 2", 180, "pricePerHour * 2", new HashSet<>());
    }

    @Override
    protected Class<Promotion> getType() {
        return Promotion.class;
    }
}
package org.boatpos.model;

import org.boatpos.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class PromotionAfterTest extends JavaBeanTest<PromotionAfter> {

    @Test
    public void testConstructor() {
        new PromotionAfter(null, 1, "Fahr 3 zahl 2", "pricePerHour * 2", new HashSet<>(), 100);
    }

    @Override
    protected Class<PromotionAfter> getType() {
        return PromotionAfter.class;
    }
}
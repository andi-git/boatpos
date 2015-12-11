package org.boatpos.model;

import org.boatpos.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class PromotionBeforeTest extends JavaBeanTest<PromotionBefore> {

    @Test
    public void testConstructor() {
        new PromotionBefore(null, 1, "Fahr 3 zahl 2", 180, "pricePerHour * 2", new HashSet<>(), 1, true);
    }

    @Override
    protected Class<PromotionBefore> getType() {
        return PromotionBefore.class;
    }
}
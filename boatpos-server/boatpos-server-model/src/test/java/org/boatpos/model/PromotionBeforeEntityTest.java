package org.boatpos.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class PromotionBeforeEntityTest extends JavaBeanTest<PromotionBeforeEntity> {

    @Test
    public void testConstructor() {
        new PromotionBeforeEntity(null, 1, "Fahr 3 zahl 2", 180, "pricePerHour * 2", new HashSet<>(), 1, true, 'a', "", "");
    }
}
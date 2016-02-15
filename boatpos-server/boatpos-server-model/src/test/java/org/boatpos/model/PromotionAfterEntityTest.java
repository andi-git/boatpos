package org.boatpos.model;

import org.boatpos.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class PromotionAfterEntityTest extends JavaBeanTest<PromotionAfterEntity> {

    @Test
    public void testConstructor() {
        new PromotionAfterEntity(null, 1, "Fahr 3 zahl 2", "pricePerHour * 2", new HashSet<>(), 100, true, 'a', "", "");
    }

    @Override
    protected Class<PromotionAfterEntity> getType() {
        return PromotionAfterEntity.class;
    }
}
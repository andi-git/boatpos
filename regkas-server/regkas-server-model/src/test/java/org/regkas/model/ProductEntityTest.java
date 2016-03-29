package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.HashSet;

@RunWith(Arquillian.class)
public class ProductEntityTest extends JavaBeanTest<ProductEntity> {

    @Test
    public void testConstructor() {
        new ProductEntity(1L, 1, true, 1, 'a', "", "", "product", new ProductGroupEntity(), new HashSet<>(), BigDecimal.ZERO, true);
    }
}
package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class ProductGroupEntityTest extends JavaBeanTest<ProductGroupEntity> {

    @Test
    public void testConstructor() {
        new ProductGroupEntity(1L, 1, true, 1, 'a', "", "", "name", new TaxSetEntity(), new CashBoxEntity(), new HashSet<>());
    }
}
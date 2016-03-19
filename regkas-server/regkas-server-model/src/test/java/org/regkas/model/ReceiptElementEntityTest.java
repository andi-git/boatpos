package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(Arquillian.class)
public class ReceiptElementEntityTest extends JavaBeanTest<ReceiptElementEntity> {

    @Test
    public void testConstructor() {
        new ReceiptElementEntity(1L, 1, new ProductGroupEntity(), BigDecimal.ZERO);
    }
}
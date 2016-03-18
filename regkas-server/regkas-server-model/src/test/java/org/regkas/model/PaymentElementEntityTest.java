package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.HashSet;

@RunWith(Arquillian.class)
public class PaymentElementEntityTest extends JavaBeanTest<PaymentElementEntity> {

    @Test
    public void testConstructor() {
        new PaymentElementEntity(1L, 1, new TaxSetEntity(), BigDecimal.ZERO);
    }
}
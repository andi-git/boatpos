package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.HashSet;

@RunWith(Arquillian.class)
public class ReceiptTypeEntityTest extends JavaBeanTest<ReceiptTypeEntity> {

    @Test
    public void testConstructor() {
        new ReceiptTypeEntity(1L, 1, true, 1, "", "", "name");
    }
}
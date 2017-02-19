package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class TaxSetEntityNullTest extends JavaBeanTest<TaxSetNullEntity> {

    @Test
    public void testConstructor() {
        new TaxSetNullEntity(1L, 1, true, 1, "", "", "name", 20, new HashSet<>());
    }
}
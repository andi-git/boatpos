package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class TaxSetEntityErmaessigt2Test extends JavaBeanTest<TaxSetErmaessigt2Entity> {

    @Test
    public void testConstructor() {
        new TaxSetErmaessigt2Entity(1L, 1, true, 1, "", "", "name", 20, new HashSet<>());
    }
}
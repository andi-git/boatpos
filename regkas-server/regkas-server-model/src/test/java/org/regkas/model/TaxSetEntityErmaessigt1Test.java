package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class TaxSetEntityErmaessigt1Test extends JavaBeanTest<TaxSetErmaessigt1Entity> {

    @Test
    public void testConstructor() {
        new TaxSetErmaessigt1Entity(1L, 1, true, 1, "", "", "name", 20, new HashSet<>());
    }
}
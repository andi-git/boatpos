package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AddressEntityTest extends JavaBeanTest<AddressEntity> {

    @Test
    public void testConstructor() {
        new AddressEntity("street", "zip", "country");
    }
}
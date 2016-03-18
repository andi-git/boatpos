package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

@RunWith(Arquillian.class)
public class CompanyEntityTest extends JavaBeanTest<CompanyEntity> {

    @Test
    public void testConstructor() {
        new CompanyEntity(1L, 1, true, 1, "", "", "name", new AddressEntity(), "phone", "mail", "atu", new HashSet<>(), new HashSet<>());
    }
}
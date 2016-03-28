package org.regkas.model;

import com.google.common.collect.Sets;
import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class CompanyEntityTest extends JavaBeanTest<CompanyEntity> {

    @Test
    public void testGetterSetter() {
        CompanyEntity companyEntity = new CompanyEntity(1L, 1, true, 1, "", "", "name", new AddressEntity(), "phone", "mail", "atu", new HashSet<>(), new HashSet<>(), new HashSet<>());
        companyEntity.setCashBoxes(Sets.newHashSet());
        companyEntity.getCashBoxes();
        companyEntity.setProductGroups(Sets.newHashSet());
        companyEntity.getProductGroups();
        companyEntity.setUsers(Sets.newHashSet());
        companyEntity.getUsers();
    }
}
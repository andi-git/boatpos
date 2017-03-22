package org.regkas.domain.core.model;

import com.google.common.collect.Sets;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.core.builder.CashBoxBuilderCoreTest;
import org.regkas.domain.core.builder.CompanyBuilderCoreTest;
import org.regkas.domain.core.builder.ProductGroupBuilderCoreTest;
import org.regkas.domain.core.builder.UserBuilderCoreTest;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CompanyTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductGroupBuilderCoreTest.ProductGroupProducer productGroupProducer;

    @Test
    @Transactional
    public void testGetter() {
        Company company = CompanyBuilderCoreTest.build();
        assertEquals("company-name", company.getName().get());
        assertEquals("atu", company.getATU().get());
        assertEquals("+12345678", company.getPhone().get());
        assertEquals("office@company.com", company.getEMail().get());
        assertEquals("street 123", company.getAddress().getStreet().get());
        assertEquals(1, company.getUsers().size());
        assertEquals(1, company.getCashBoxes().size());

        company.clearUsers();
        company.addUser(UserBuilderCoreTest.build());
        assertEquals(1, company.getUsers().size());
        company.clearUsers();
        company.addUsers(Sets.newHashSet(UserBuilderCoreTest.build()));
        assertEquals(1, company.getUsers().size());

        company.clearCashBoxes();
        company.addCashBox(CashBoxBuilderCoreTest.build());
        assertEquals(1, company.getCashBoxes().size());
        company.clearCashBoxes();
        company.addCashBoxes(Sets.newHashSet(CashBoxBuilderCoreTest.build()));
        assertEquals(1, company.getCashBoxes().size());

        company.clearProductGroups();
        company.addProductGroup(productGroupProducer.getProductGroup());
        assertEquals(1, company.getProductGroups().size());
        company.clearProductGroups();
        company.addProductGroups(Sets.newHashSet(productGroupProducer.getProductGroup()));
        assertEquals(1, company.getProductGroups().size());
    }
}
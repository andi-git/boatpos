package org.regkas.repository.core.model;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.core.builder.AddressBuilderCoreTest;
import org.regkas.repository.core.builder.CashBoxBuilderCoreTest;
import org.regkas.repository.core.builder.CompanyBuilderCoreTest;
import org.regkas.repository.core.builder.UserBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class CompanyTest {

    @Test
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
    }
}
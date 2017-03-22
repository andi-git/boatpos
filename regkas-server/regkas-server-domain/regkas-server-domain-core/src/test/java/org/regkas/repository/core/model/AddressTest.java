package org.regkas.repository.core.model;

import org.junit.Test;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.model.User;
import org.regkas.repository.core.builder.AddressBuilderCoreTest;
import org.regkas.repository.core.builder.UserBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class AddressTest {

    @Test
    public void testGetter() {
        Address address = AddressBuilderCoreTest.build();
        assertEquals("street 123", address.getStreet().get());
        assertEquals("12345", address.getZIP().get());
        assertEquals("city", address.getCity().get());
        assertEquals("country", address.getCountry().get());
    }
}
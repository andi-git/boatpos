package org.regkas.domain.core.model;

import org.junit.Test;
import org.regkas.domain.api.model.Address;
import org.regkas.domain.core.builder.AddressBuilderCoreTest;

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
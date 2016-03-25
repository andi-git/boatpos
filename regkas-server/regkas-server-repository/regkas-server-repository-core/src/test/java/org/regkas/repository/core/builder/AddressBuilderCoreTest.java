package org.regkas.repository.core.builder;

import org.junit.Test;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.values.City;
import org.regkas.repository.api.values.Country;
import org.regkas.repository.api.values.Street;
import org.regkas.repository.api.values.ZIP;

import static org.junit.Assert.assertEquals;

public class AddressBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("street 123", build().getStreet().get());
    }

    public static Address build() {
        return new AddressBuilderCore()
                .add(new Street("street 123"))
                .add(new ZIP("12345"))
                .add(new City("city"))
                .add(new Country("country"))
                .build();
    }
}
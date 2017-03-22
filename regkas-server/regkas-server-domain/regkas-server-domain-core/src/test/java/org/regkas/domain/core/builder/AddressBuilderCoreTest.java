package org.regkas.domain.core.builder;

import org.junit.Test;
import org.regkas.domain.api.model.Address;
import org.regkas.domain.api.values.City;
import org.regkas.domain.api.values.Country;
import org.regkas.domain.api.values.Street;
import org.regkas.domain.api.values.ZIP;

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
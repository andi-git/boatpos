package org.regkas.repository.core.model;

import org.junit.Test;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.model.User;
import org.regkas.repository.core.builder.TaxSetBuilderCoreTest;
import org.regkas.repository.core.builder.UserBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class TaxSetCoreTest {

    @Test
    public void testGetter() {
        TaxSet taxSet = TaxSetBuilderCoreTest.build();
        assertEquals("taxset-name", taxSet.getName().get());
        assertEquals(20, taxSet.getTaxPercent().get().intValue());
    }
}
package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.junit.Test;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Price;
import org.regkas.repository.api.values.TaxPercent;
import org.regkas.repository.core.model.ProductGroupCore;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxSetBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("taxset-name", build().getName().get());
    }

    public static TaxSet build() {
        return new TaxSetBuilderCore()
                .add(Enabled.TRUE)
                .add(new KeyBinding(' '))
                .add(new PictureUrl(""))
                .add(new PictureUrlThumb(""))
                .add(new Priority(1))
                .add(new Name("taxset-name"))
                .add(new TaxPercent(20))
                .build();
    }
}
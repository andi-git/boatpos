package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.junit.Test;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Price;
import org.regkas.repository.core.model.ProductGroupCore;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductGroupBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("productgroup-name", build().getName().get());
    }

    public static ProductGroup build() {
        return new ProductGroupBuilderCore()
                .add(Enabled.TRUE)
                .add(new KeyBinding(' '))
                .add(new PictureUrl(""))
                .add(new PictureUrlThumb(""))
                .add(new Priority(1))
                .add(new Name("productgroup-name"))
                .add(TaxSetBuilderCoreTest.build())
                .build();
    }
}
package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.junit.Test;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.values.Amount;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TotalPrice;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ReceiptElementBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("product-name", build().getProduct().getName().get());
    }

    public static ReceiptElement build() {
        return new ReceiptElementBuilderCore()
                .add(ProductBuilderCoreTest.build())
                .add(new Amount(2))
                .add(new TotalPrice(new BigDecimal("6.00")))
                .build();
    }
}
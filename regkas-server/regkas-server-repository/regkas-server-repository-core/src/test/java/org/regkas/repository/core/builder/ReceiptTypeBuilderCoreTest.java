package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.junit.Test;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TaxPercent;

import static org.junit.Assert.assertEquals;

public class ReceiptTypeBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("receipttype-name", build().getName().get());
    }

    public static ReceiptType build() {
        return new ReceiptTypeBuilderCore()
                .add(Enabled.TRUE)
                .add(new KeyBinding(' '))
                .add(new PictureUrl(""))
                .add(new PictureUrlThumb(""))
                .add(new Priority(1))
                .add(new Name("receipttype-name"))
                .build();
    }
}
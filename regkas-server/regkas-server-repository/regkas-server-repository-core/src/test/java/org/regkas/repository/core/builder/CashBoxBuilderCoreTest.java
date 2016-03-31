package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.junit.Test;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.SignatureCertificateSerialNumber;

import static org.junit.Assert.assertEquals;

public class CashBoxBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("cashbox-id", build().getName().get());
    }

    public static CashBox build() {
        return new CashBoxBuilderCore()
                .add(Enabled.TRUE)
                .add(new KeyBinding(' '))
                .add(new PictureUrl(""))
                .add(new PictureUrlThumb(""))
                .add(new Priority(1))
                .add(new Name("cashbox-id"))
                .add(new SignatureCertificateSerialNumber("xyz"))
                .build();
    }
}
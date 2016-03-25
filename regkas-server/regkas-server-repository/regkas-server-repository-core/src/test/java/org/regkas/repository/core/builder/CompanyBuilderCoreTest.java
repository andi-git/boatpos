package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.junit.Test;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.*;

import static org.junit.Assert.assertEquals;

public class CompanyBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("company-name", build().getName().get());
    }

    public static Company build() {
        return new CompanyBuilderCore()
                .add(Enabled.TRUE)
                .add(new KeyBinding(' '))
                .add(new PictureUrl(""))
                .add(new PictureUrlThumb(""))
                .add(new Priority(1))
                .add(new Name("company-name"))
                .add(new ATU("atu"))
                .add(new Phone("+12345678"))
                .add(new EMail("office@company.com"))
                .add(UserBuilderCoreTest.build())
                .add(AddressBuilderCoreTest.build())
                .add(CashBoxBuilderCoreTest.build())
                .build();
    }
}
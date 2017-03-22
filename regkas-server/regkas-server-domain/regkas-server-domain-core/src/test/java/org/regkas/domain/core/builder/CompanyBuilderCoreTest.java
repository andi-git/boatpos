package org.regkas.domain.core.builder;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.junit.Test;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.values.ATU;
import org.regkas.domain.api.values.EMail;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.Phone;

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
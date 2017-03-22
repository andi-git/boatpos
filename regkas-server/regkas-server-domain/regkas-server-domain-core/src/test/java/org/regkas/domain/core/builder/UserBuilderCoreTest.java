package org.regkas.domain.core.builder;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.junit.Test;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.PasswordPlain;

import static org.junit.Assert.assertEquals;

public class UserBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("user-name", build().getName().get());
    }

    public static User build() {
        return new UserBuilderCore()
                .add(Enabled.TRUE)
                .add(new KeyBinding(' '))
                .add(new PictureUrl(""))
                .add(new PictureUrlThumb(""))
                .add(new Priority(1))
                .add(new Name("user-name"))
                .add(new PasswordPlain("12345"))
                .build();
    }
}
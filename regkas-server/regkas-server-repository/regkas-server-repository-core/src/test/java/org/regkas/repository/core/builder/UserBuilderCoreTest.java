package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.junit.Test;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.*;

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
                .add(new Password("12345"))
                .build();
    }
}
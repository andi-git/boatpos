package org.regkas.domain.core.model;

import org.junit.Test;
import org.regkas.domain.api.model.User;
import org.regkas.domain.core.builder.UserBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class UserCoreTest {

    @Test
    public void testGetter() {
        User user = UserBuilderCoreTest.build();
        assertEquals("user-name", user.getName().get());
        assertEquals("12345", user.getPassword().get());
    }
}
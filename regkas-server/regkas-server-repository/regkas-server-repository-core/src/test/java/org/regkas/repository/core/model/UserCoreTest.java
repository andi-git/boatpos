package org.regkas.repository.core.model;

import org.junit.Test;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.User;
import org.regkas.repository.core.builder.CashBoxBuilderCoreTest;
import org.regkas.repository.core.builder.UserBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class UserCoreTest {

    @Test
    public void testGetter() {
        User user = UserBuilderCoreTest.build();
        assertEquals("user-name", user.getName().get());
        assertEquals("12345", user.getPassword().get());
    }
}
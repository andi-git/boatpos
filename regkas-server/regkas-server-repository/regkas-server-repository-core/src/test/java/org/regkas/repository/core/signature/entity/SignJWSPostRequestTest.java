package org.regkas.repository.core.signature.entity;

import static org.junit.Assert.assertEquals;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

public class SignJWSPostRequestTest extends JavaBeanTest<SignJWSPostRequest> {

    @Test
    public void testConstructor() {
        assertEquals("key", new SignJWSPostRequest("key", "payload").getSessionkey());
    }
}

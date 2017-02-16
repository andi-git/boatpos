package org.regkas.repository.core.crypto;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class EncodingTest {

    @Inject
    private Encoding encoding;

    @Test
    public void testBase64EncodeDecode() throws Exception {
        String string = "test";
        byte[] decodedString = encoding.base64Decode(string, false);
        assertEquals(3, decodedString.length);
        assertEquals(string, encoding.base64Encode(decodedString, false));
    }
}
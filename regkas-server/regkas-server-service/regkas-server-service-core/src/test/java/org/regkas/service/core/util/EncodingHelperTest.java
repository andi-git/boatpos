package org.regkas.service.core.util;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class EncodingHelperTest {

    @Inject
    private EncodingHelper encodingHelper;

    @Test
    public void testBase64EncodeDecode() throws Exception {
        String string = "test";
        byte[] decodedString = encodingHelper.base64Decode(string, false);
        assertEquals(3, decodedString.length);
        assertEquals(string, encodingHelper.base64Encode(decodedString, false));
    }
}
package org.regkas.domain.core.crypto;

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

        assertEquals("eyJhbGciOiJFUzI1NiJ9", encoding.base64Encode("{\"alg\":\"ES256\"}".getBytes(), false));
        assertEquals("_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto=",
                encoding.base64DecodeAsString("X1IxLUFUMTAwX0NBU0hCT1gtREVNTy0xX0NBU0hCT1gtREVNTy0xLVJlY2VpcHQtSUQtODJfMjAxNi0wMy0xMVQwNDoyNDo0Nl8wLDAwXzAsMDBfMCwwMF8wLDAwXzAsMDBfTkxvaVNITDNic009X2VlZTI1NzU3OWIwMzMwMmZfY2c4aE5VNWlodG89", false));
    }
}
package org.regkas.repository.core.signature;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.signature.RkOnlineSignature;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.api.values.RkOnlinePassword;
import org.regkas.repository.api.values.RkOnlineUsername;
import org.regkas.repository.core.crypto.Encoding;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class RkOnlineSignatureCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private RkOnlineSignature rkOnlineSignature;

    @Inject
    private Encoding encoding;

    private RkOnlineUsername username = new RkOnlineUsername("u123456789");

    private RkOnlinePassword password = new RkOnlinePassword("123456789");

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Before
    public void before() {
        rkOnlineContext.setEnvironment(Environment.TEST);
        rkOnlineContext.setRkOnlineUsername(username);
        rkOnlineContext.setRkOnlinePassword(password);
    }

    @Test
    @Transactional
    @Ignore
    public void testSign() throws Exception {
        CompactJWSRepresentation compactJWSRepresentation = rkOnlineSignature.sign(new JWSPayload("_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto="));
        assertEquals("eyJhbGciOiJFUzI1NiJ9", compactJWSRepresentation.getProtectedHeader());
        assertEquals("X1IxLUFUMTAwX0NBU0hCT1gtREVNTy0xX0NBU0hCT1gtREVNTy0xLVJlY2VpcHQtSUQtODJfMjAxNi0wMy0xMVQwNDoyNDo0Nl8wLDAwXzAsMDBfMCwwMF8wLDAwXzAsMDBfTkxvaVNITDNic009X2VlZTI1NzU3OWIwMzMwMmZfY2c4aE5VNWlodG89", compactJWSRepresentation.getPayload());
        assertEquals(86, compactJWSRepresentation.getSignature().length());

        System.out.println(encoding.base64Encode("{\"alg\":\"ES256\"}".getBytes(), false));
        System.out.println(encoding.base64Encode("_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto=".getBytes(), false));

        // decode payload
        assertEquals("_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto=",  encoding.base64DecodeAsString(compactJWSRepresentation.getPayload(), true));
        // decode and encode signature
        assertEquals(88, encoding.base64Encode(encoding.base64Decode(compactJWSRepresentation.getSignature(), true), false).length());

        assertEquals("J7YC28zquHfHzMpx02TqElbXOTSgXQu5JAA9Xu1Xzzu5p8eUYT+sgmyhzRps5nYyEp5Yh8ATIa9130zmuiACHw==", encoding.base64Encode(encoding.base64Decode("J7YC28zquHfHzMpx02TqElbXOTSgXQu5JAA9Xu1Xzzu5p8eUYT-sgmyhzRps5nYyEp5Yh8ATIa9130zmuiACHw", true), false));
    }
}

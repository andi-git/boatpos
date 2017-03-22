package org.regkas.repository.core.signature;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.core.crypto.Encoding;

@RunWith(Arquillian.class)
public class CompactJWSRepresentationCoreTest {

    @Inject
    private Encoding encoding;

    private CompactJWSRepresentation compactJWSRepresentation;

    private String protectedHeader = "eyJhbGciOiJFUzI1NiJ9";

    private String payloadDecoded = "X1IxLUFUMTAwX0NBU0hCT1gtREVNTy0xX0NBU0hCT1gtREVNTy0xLVJlY2VpcHQtSUQtODJfMjAxNi0wMy0xMVQwNDoyNDo0Nl8wLDAwXzAsMDBfMCwwMF8wLDAwXzAsMDBfTkxvaVNITDNic009X2VlZTI1NzU3OWIwMzMwMmZfY2c4aE5VNWlodG89";

    private String payloadEncoded = "_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto=";

    private String signatureOriginal = "J7YC28zquHfHzMpx02TqElbXOTSgXQu5JAA9Xu1Xzzu5p8eUYT-sgmyhzRps5nYyEp5Yh8ATIa9130zmuiACHw";

    @SuppressWarnings("FieldCanBeLocal")
    private String signatureDecodedAndEncoded = "J7YC28zquHfHzMpx02TqElbXOTSgXQu5JAA9Xu1Xzzu5p8eUYT+sgmyhzRps5nYyEp5Yh8ATIa9130zmuiACHw==";

    @SuppressWarnings("FieldCanBeLocal")
    private String signatureWhenDeviceIsNotAvailable = "U2ljaGVyaGVpdHNlaW5yaWNodHVuZyBhdXNnZWZhbGxlbg";

    @Before
    public void before() {
        compactJWSRepresentation = CompactJWSRepresentationCore
            .fromRealCompactJwsRepresentation(protectedHeader + "." + payloadDecoded + "." + signatureOriginal, encoding);
    }

    @Test
    public void testGetCompactJwsRepresentation() throws Exception {
        assertEquals(protectedHeader + "." + payloadDecoded + "." + signatureOriginal, compactJWSRepresentation.getCompactJwsRepresentation());
    }

    @Test
    public void testGetProtectedHeader() throws Exception {
        assertEquals(protectedHeader, compactJWSRepresentation.getProtectedHeader());
    }

    @Test
    public void testGetPayloadOriginal() throws Exception {
        assertEquals(payloadDecoded, compactJWSRepresentation.getPayloadOriginal());
    }

    @Test
    public void testGetPayload() throws Exception {
        assertEquals(payloadEncoded, compactJWSRepresentation.getPayload());
    }

    @Test
    public void testGetSignatureOriginal() throws Exception {
        assertEquals(signatureOriginal, compactJWSRepresentation.getSignatureOriginal());
    }

    @Test
    public void testGetSignature() throws Exception {
        assertEquals(signatureDecodedAndEncoded, compactJWSRepresentation.getSignature());
    }

    @Test
    public void testWhenSignatureDeviceIsNotAvailable() {
        assertEquals(
            protectedHeader + "." + payloadDecoded + "." + signatureWhenDeviceIsNotAvailable,
            CompactJWSRepresentationCore.whenSignatureDeviceIsNotAvailable(new JWSPayload(payloadEncoded), encoding).getCompactJwsRepresentation());
    }
}

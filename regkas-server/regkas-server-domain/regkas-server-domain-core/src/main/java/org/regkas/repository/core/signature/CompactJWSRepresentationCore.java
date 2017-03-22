package org.regkas.repository.core.signature;

import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.core.crypto.Encoding;

public class CompactJWSRepresentationCore implements CompactJWSRepresentation {

    @SuppressWarnings("FieldCanBeLocal")
    private static String DEFAULT_PROTECTED_HEADER = "{\"alg\":\"ES256\"}";

    @SuppressWarnings("FieldCanBeLocal")
    private static String DEFAULT_SIGNATURE_WHEN_DEVICE_IS_NOT_AVAILABLE = "Sicherheitseinrichtung ausgefallen";

    private final String compactJwsRepresentation;

    private final Encoding encoding;

    private final boolean signatureDeviceAvailable;

    private CompactJWSRepresentationCore(String compactJwsRepresentation, Encoding encoding, boolean signatureDeviceAvailable) {
        this.compactJwsRepresentation = compactJwsRepresentation;
        this.encoding = encoding;
        this.signatureDeviceAvailable = signatureDeviceAvailable;
    }

    public static CompactJWSRepresentation fromRealCompactJwsRepresentation(String compactJwsRepresentation, Encoding encoding) {
        return new CompactJWSRepresentationCore(compactJwsRepresentation, encoding, true);
    }

    @SuppressWarnings("WeakerAccess")
    public static CompactJWSRepresentation whenSignatureDeviceIsNotAvailable(JWSPayload jwsPayload, Encoding encoding) {
        String compactJwsRepresentation = encoding.base64Encode(DEFAULT_PROTECTED_HEADER.getBytes(), true) +
            "." +
            encoding.base64Encode(jwsPayload.get().getBytes(), true) +
            "." +
            encoding.base64Encode(DEFAULT_SIGNATURE_WHEN_DEVICE_IS_NOT_AVAILABLE.getBytes(), true);
        return new CompactJWSRepresentationCore(compactJwsRepresentation, encoding, false);
    }

    @Override
    public String getCompactJwsRepresentation() {
        return compactJwsRepresentation;
    }

    @Override
    public String getProtectedHeader() {
        return compactJwsRepresentation.split("\\.")[0];
    }

    @Override
    public String getPayloadOriginal() {
        return compactJwsRepresentation.split("\\.")[1];
    }

    @Override
    public String getPayload() {
        return encoding.base64DecodeAsString(getPayloadOriginal(), true);
    }

    @Override
    public String getSignatureOriginal() {
        return compactJwsRepresentation.split("\\.")[2];
    }

    @Override
    public String getMachineReadableRepresentation() {
        return getPayload() + "_" + getSignature();
    }

    @Override
    public boolean isSignatureDeviceAvailable() {
        return signatureDeviceAvailable;
    }

    @Override
    public String getSignature() {
        return encoding.base64Encode(encoding.base64Decode(getSignatureOriginal(), true), false);
    }
}

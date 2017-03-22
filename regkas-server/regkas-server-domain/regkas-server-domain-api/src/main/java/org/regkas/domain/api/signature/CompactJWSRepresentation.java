package org.regkas.domain.api.signature;

public interface CompactJWSRepresentation {

    String getCompactJwsRepresentation();

    String getProtectedHeader();

    String getPayloadOriginal();

    String getPayload();

    String getSignature();

    String getSignatureOriginal();

    String getMachineReadableRepresentation();

    boolean isSignatureDeviceAvailable();
}

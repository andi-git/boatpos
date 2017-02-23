package org.regkas.repository.core.signature;

import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.core.crypto.Encoding;

public class CompactJWSRepresentationCore implements CompactJWSRepresentation {

    private final String compactJwsRepresentation;

    private final Encoding encoding;

    public CompactJWSRepresentationCore(String compactJwsRepresentation, Encoding encoding) {
        this.compactJwsRepresentation = compactJwsRepresentation;
        this.encoding = encoding;
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
    public String getSignature() {
        return encoding.base64Encode(encoding.base64Decode(getSignatureOriginal(), true), false);
    }
}

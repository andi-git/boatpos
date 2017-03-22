package org.regkas.domain.core.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Encoding {

    public String base64Encode(byte[] data, boolean isUrlSafe) {
        Base64 encoder = new Base64(isUrlSafe);
        return new String(encoder.encode(data)).replace("\r\n", "");
    }

    public byte[] base64Decode(String base64Data, boolean isUrlSafe) {
        Base64 decoder = new Base64(isUrlSafe);
        return decoder.decode(base64Data);
    }

    public String base64DecodeAsString(String base64Data, boolean isUrlSafe) {
        return new String(base64Decode(base64Data, isUrlSafe));
    }
}

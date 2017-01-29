package org.regkas.service.core.util;

import org.apache.commons.codec.binary.Base64;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EncodingHelper {

    public String base64Encode(byte[] data, boolean isUrlSafe) {
        Base64 encoder = new Base64(isUrlSafe);
        return new String(encoder.encode(data)).replace("\r\n", "");
    }

    public byte[] base64Decode(String base64Data, boolean isUrlSafe) {
        Base64 decoder = new Base64(isUrlSafe);
        return decoder.decode(base64Data);
    }
}

package org.regkas.repository.api.values;

import java.util.List;

/**
 * The representation of the certificate.
 */
public class Certificate {

    private final String signaturzertifikat;

    private final List<String> zertifizierungsstellen;

    public Certificate(String signaturzertifikat, List<String> zertifizierungsstellen) {
        this.signaturzertifikat = signaturzertifikat;
        this.zertifizierungsstellen = zertifizierungsstellen;
    }

    public String getSignaturzertifikat() {
        return signaturzertifikat;
    }

    public List<String> getZertifizierungsstellen() {
        return zertifizierungsstellen;
    }
}

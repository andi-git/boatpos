package org.regkas.repository.core.signature.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class CertificateGetResponse {

    @Expose
    @JsonProperty("Signaturzertifikat")
    private String signaturzertifikat;

    @Expose
    @JsonProperty("Zertifizierungsstellen")
    private List<String> zertifizierungsstellen;

    @Expose
    @JsonProperty("Zertifikatsseriennummer")
    private String zertifikatsseriennummer;

    @Expose
    @JsonProperty("ZertifikatsseriennummerHex")
    private String zertifikatsseriennummerHex;

    @Expose
    @JsonProperty("alg")
    private String alg;

    public CertificateGetResponse() {}

    public String getSignaturzertifikat() {
        return signaturzertifikat;
    }

    @JsonIgnore
    public void setSignaturzertifikat(String signaturzertifikat) {
        this.signaturzertifikat = signaturzertifikat;
    }

    public List<String> getZertifizierungsstellen() {
        return zertifizierungsstellen;
    }

    @JsonIgnore
    public void setZertifizierungsstellen(List<String> zertifizierungsstellen) {
        this.zertifizierungsstellen = zertifizierungsstellen;
    }

    public String getZertifikatsseriennummer() {
        return zertifikatsseriennummer;
    }

    @JsonIgnore
    public void setZertifikatsseriennummer(String zertifikatsseriennummer) {
        this.zertifikatsseriennummer = zertifikatsseriennummer;
    }

    public String getZertifikatsseriennummerHex() {
        return zertifikatsseriennummerHex;
    }

    @JsonIgnore
    public void setZertifikatsseriennummerHex(String zertifikatsseriennummerHex) {
        this.zertifikatsseriennummerHex = zertifikatsseriennummerHex;
    }

    public String getAlg() {
        return alg;
    }

    @JsonIgnore
    public void setAlg(String alg) {
        this.alg = alg;
    }
}

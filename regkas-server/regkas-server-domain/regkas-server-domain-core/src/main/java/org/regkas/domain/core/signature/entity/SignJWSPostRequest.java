package org.regkas.domain.core.signature.entity;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class SignJWSPostRequest {

    @Expose
    private String sessionkey;

    @Expose
    private String jws_payload;

    public SignJWSPostRequest() {
    }

    public SignJWSPostRequest(String sessionkey, String jws_payload) {
        this.sessionkey = sessionkey;
        this.jws_payload = jws_payload;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }

    public String getJws_payload() {
        return jws_payload;
    }

    public void setJws_payload(String jws_payload) {
        this.jws_payload = jws_payload;
    }
}

package org.regkas.repository.core.signature.entity;

import com.google.gson.annotations.Expose;

public class SignJWSPostRequest {

    @Expose
    private String password;

    @Expose
    private String jws_payload;

    public SignJWSPostRequest() {
    }

    public SignJWSPostRequest(String password, String jws_payload) {
        this.password = password;
        this.jws_payload = jws_payload;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJws_payload() {
        return jws_payload;
    }

    public void setJws_payload(String jws_payload) {
        this.jws_payload = jws_payload;
    }
}

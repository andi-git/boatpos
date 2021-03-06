package org.regkas.domain.core.signature.entity;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class SessionPutRequest {

    @Expose
    private String password;

    public SessionPutRequest() {
    }

    public SessionPutRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

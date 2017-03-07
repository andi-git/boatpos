package org.regkas.repository.core.signature.entity;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class SessionPutResponse {

    @Expose
    private String sessionid;

    @Expose
    private String sessionkey;

    public SessionPutResponse() {
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }
}

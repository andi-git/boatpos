package org.regkas.repository.core.signature.entity;

import com.google.gson.annotations.Expose;

public class SessionPutResponse {

    @Expose
    private String sessionId;

    @Expose
    private String sessionKey;

    public SessionPutResponse() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}

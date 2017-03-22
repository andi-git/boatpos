package org.regkas.domain.core.signature.entity;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class SignJWSPostResponse {

    @Expose
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

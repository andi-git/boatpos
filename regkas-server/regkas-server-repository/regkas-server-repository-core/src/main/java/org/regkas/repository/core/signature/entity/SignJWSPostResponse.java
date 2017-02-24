package org.regkas.repository.core.signature.entity;

import com.google.gson.annotations.Expose;

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

package org.regkas.repository.core.signature.entity;

import com.google.gson.annotations.Expose;

public class SignJWSPostResponse {

    @Expose
    private String compactJwsRepresentation;

    public String getCompactJwsRepresentation() {
        return compactJwsRepresentation;
    }

    public void setCompactJwsRepresentation(String compactJwsRepresentation) {
        this.compactJwsRepresentation = compactJwsRepresentation;
    }
}

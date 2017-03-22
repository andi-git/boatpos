package org.regkas.repository.api.signature;

import javax.ws.rs.core.Response;

public class SignatureDeviceNotAvailableException extends Exception {

    public SignatureDeviceNotAvailableException(Response.StatusType status) {
        super("response-status: " + status.getStatusCode() + " -> " + status.getReasonPhrase());
    }

    public SignatureDeviceNotAvailableException(String message) {
        super(message);
    }
}

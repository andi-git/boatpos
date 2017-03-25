package org.regkas.domain.api.signature;

public class SignatureDeviceMandatoryException extends RuntimeException {

    public SignatureDeviceMandatoryException() {
        super();
    }

    public SignatureDeviceMandatoryException(String message) {
        super(message);
    }

    public SignatureDeviceMandatoryException(String message, Throwable cause) {
        super(message, cause);
    }
}

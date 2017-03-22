package org.regkas.service.core.receipt;

import org.regkas.domain.api.signature.RkOnlineResourceSession;
import org.regkas.domain.api.signature.SignatureDeviceNotAvailableException;

public class RkOnlineResourceSessionThrowingException implements RkOnlineResourceSession {

    @Override
    public void loginSession() throws SignatureDeviceNotAvailableException {
        throw new SignatureDeviceNotAvailableException("just to throw the exception");
    }
}

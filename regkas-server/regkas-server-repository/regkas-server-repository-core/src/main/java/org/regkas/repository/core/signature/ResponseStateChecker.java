package org.regkas.repository.core.signature;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.repository.api.signature.SignatureDeviceNotAvailableException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ResponseStateChecker {

    @Inject
    @SLF4J
    private LogWrapper log;

    public void checkResponseState(Response response) throws SignatureDeviceNotAvailableException {
        if (response.getStatusInfo() != Response.Status.OK) {
            SignatureDeviceNotAvailableException signatureDeviceNotAvailableException = new SignatureDeviceNotAvailableException(response.getStatusInfo());
            log.error(signatureDeviceNotAvailableException);
            throw signatureDeviceNotAvailableException;
        }
    }
}

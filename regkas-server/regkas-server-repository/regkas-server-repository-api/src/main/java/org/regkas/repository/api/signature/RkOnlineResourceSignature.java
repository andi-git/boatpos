package org.regkas.repository.api.signature;

import org.regkas.repository.api.values.JWSPayload;

public interface RkOnlineResourceSignature {

    CompactJWSRepresentation sign(JWSPayload jwsPayload) throws SignatureDeviceNotAvailableException;
}

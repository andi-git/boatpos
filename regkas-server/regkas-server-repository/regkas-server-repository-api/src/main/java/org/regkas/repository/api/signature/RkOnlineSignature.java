package org.regkas.repository.api.signature;

import org.regkas.repository.api.values.JWSPayload;

public interface RkOnlineSignature {

    CompactJWSRepresentation sign(JWSPayload jwsPayload);
}

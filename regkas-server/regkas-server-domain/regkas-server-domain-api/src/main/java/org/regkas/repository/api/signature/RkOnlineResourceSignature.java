package org.regkas.repository.api.signature;

import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.values.JWSPayload;

public interface RkOnlineResourceSignature {

    CompactJWSRepresentation sign(JWSPayload jwsPayload, ReceiptType receiptType);
}

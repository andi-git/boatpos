package org.regkas.domain.api.signature;

import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.values.JWSPayload;

public interface RkOnlineResourceSignature {

    CompactJWSRepresentation sign(JWSPayload jwsPayload, ReceiptType receiptType);

    CompactJWSRepresentation signWhenSignatureIsNotAvailable(JWSPayload jwsPayload, ReceiptType receiptType);
}

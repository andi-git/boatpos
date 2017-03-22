package org.regkas.repository.core.signature;

import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.signature.RkOnlineResourceSignature;
import org.regkas.repository.api.values.JWSPayload;

import javax.enterprise.inject.Alternative;

@Alternative
public class MockRkOnlineResourceSignature implements RkOnlineResourceSignature {

    @Override
    public CompactJWSRepresentation sign(JWSPayload jwsPayload, ReceiptType receiptType) {
        return null;
    }
}

package org.regkas.domain.core.signature;

import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.signature.CompactJWSRepresentation;
import org.regkas.domain.api.signature.RkOnlineResourceSignature;
import org.regkas.domain.api.values.JWSPayload;

import javax.enterprise.inject.Alternative;

@Alternative
public class MockRkOnlineResourceSignature implements RkOnlineResourceSignature {

    @Override
    public CompactJWSRepresentation sign(JWSPayload jwsPayload, ReceiptType receiptType) {
        return null;
    }
}

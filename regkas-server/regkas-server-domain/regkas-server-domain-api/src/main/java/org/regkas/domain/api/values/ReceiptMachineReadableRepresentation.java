package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The machine readable representation of a receipt.
 */
public class ReceiptMachineReadableRepresentation extends SimpleValueObject<ReceiptMachineReadableRepresentation, String> {

    public ReceiptMachineReadableRepresentation(String value) {
        super(value);
    }
}

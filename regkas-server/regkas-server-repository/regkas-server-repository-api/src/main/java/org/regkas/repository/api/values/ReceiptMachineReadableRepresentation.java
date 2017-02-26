package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The machine readable representation of a receipt.
 */
public class ReceiptMachineReadableRepresentation extends SimpleValueObject<ReceiptMachineReadableRepresentation, String> {

    public ReceiptMachineReadableRepresentation(String value) {
        super(value);
    }
}

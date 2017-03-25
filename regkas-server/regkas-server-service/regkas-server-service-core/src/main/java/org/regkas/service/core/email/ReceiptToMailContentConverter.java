package org.regkas.service.core.email;

import javax.enterprise.context.ApplicationScoped;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.regkas.domain.api.model.Receipt;

@ApplicationScoped
public class ReceiptToMailContentConverter {

    public String convertToMailContent(Receipt receipt) {
        return "cash-box: " +
            SimpleValueObject.nullSafe(receipt.getCashBox().getName()) +
            "\n" +
            "receipt-id: " +
            SimpleValueObject.nullSafe(receipt.getReceiptId()) +
            "\n" +
            "receipt-time: " +
            SimpleValueObject.nullSafe(receipt.getReceiptDate()) +
            "\n" +
            "receipt-type: " +
            SimpleValueObject.nullSafe(receipt.getReceiptType().getName());
    }
}

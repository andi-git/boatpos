package org.regkas.service.core.util;

import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.service.api.bean.BillBean;

import javax.enterprise.context.RequestScoped;

/**
 * Convert a {@link Receipt} to a {@link BillBean}.
 */
@RequestScoped
public class ReceiptToBillConverter {

    public BillBean convert(Receipt receipt) {
        BillBean bill = new BillBean();
        bill.setCompany(receipt.getCompany().asDto());
        bill.setCashBoxID(receipt.getCashBox().getName().get());
        bill.setReceiptIdentifier(receipt.getReceiptId().get());
        bill.setReceiptDateAndTime(receipt.getReceiptDate().get());
        bill.setEncryptedTurnoverValue("");
        bill.setSignatureCertificateSerialNumber("");
        bill.setSignatureValuePreviousReceipt("");
        for (ReceiptElement receiptElement : receipt.getReceiptElements()) {
            TaxSet.addToBill(receiptElement, bill);
        }
        return bill;
    }
}

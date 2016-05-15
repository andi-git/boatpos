package org.regkas.service.core.util;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.service.api.bean.BillBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Convert a {@link Receipt} to a {@link BillBean}.
 */
@RequestScoped
public class ReceiptToBillConverter {

    @Inject
    @Current
    private CashBox cashBox;

    @Inject
    @Current
    private Company company;

    public BillBean convert(Receipt receipt) {
        BillBean bill = new BillBean();
        bill.setCompany(company.asDto());
        bill.setCashBoxID(cashBox.getName().get());
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

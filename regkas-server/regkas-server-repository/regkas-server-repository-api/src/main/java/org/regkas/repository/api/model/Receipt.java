package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.DomainModelWithDto;
import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.regkas.model.CompanyEntity;
import org.regkas.model.ReceiptEntity;
import org.regkas.repository.api.values.*;
import org.regkas.service.api.bean.CompanyBean;
import org.regkas.service.api.bean.ReceiptBean;

import java.util.Set;

/**
 * The domain model for a company.
 */
public interface Receipt extends DomainModelWithDto<Receipt, ReceiptEntity, ReceiptBean> {

    ReceiptId getReceiptId();

    Receipt setReceiptId(ReceiptId receiptId);

    ReceiptDate getReceiptDate();

    Receipt setReceiptDate(ReceiptDate receiptDate);

    EncryptedTurnoverValue getEncryptedTurnoverValue();

    Receipt setEncryptedTurnoverValue(EncryptedTurnoverValue encryptedTurnoverValue);

    SignatureValuePreviousReceipt getSignatureValuePreviousReceipt();

    Receipt setSignatureValuePreviousReceipt(SignatureValuePreviousReceipt signatureValuePreviousReceipt);

    Company getCompany();

    Receipt setCompany(Company company);

    User getUser();

    Receipt setUser(User user);

    ReceiptType getReceiptType();

    Receipt setReceiptType(ReceiptType receiptType);

    CashBox getCashBox();

    Receipt setCashBox(CashBox cashBox);

    Set<ReceiptElement> getReceiptElements();

    Receipt addReceiptElements(Set<ReceiptElement> receiptElements);

    Receipt addReceiptElement(ReceiptElement receiptElement);

    Receipt clearReceiptElements();
}

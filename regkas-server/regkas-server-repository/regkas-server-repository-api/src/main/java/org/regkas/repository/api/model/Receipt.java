package org.regkas.repository.api.model;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.api.model.DomainModelWithDto;
import org.regkas.model.ReceiptEntity;
import org.regkas.model.TimeType;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.ReceiptDate;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
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

    PaymentMethod getPaymentMethod();

    Receipt setPaymentMethod(PaymentMethod paymentMethod);

    TimeType getTimeType();

    Receipt setTimeType(TimeType timeType);

    Set<ReceiptElement> getReceiptElements();

    Receipt addReceiptElements(Set<ReceiptElement> receiptElements);

    Receipt addReceiptElement(ReceiptElement receiptElement);

    Receipt clearReceiptElements();
}

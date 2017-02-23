package org.regkas.repository.api.model;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.api.model.DomainModelWithDto;
import org.regkas.model.ReceiptEntity;
import org.regkas.repository.api.values.*;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ReceiptBean;

import java.util.List;

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

    DEPString getDEP();

    Receipt setDEP(DEPString dep);

    TotalPrice getTotalPrice();

    Receipt setTotalPrice(TotalPrice totalPrice);

    <T extends TaxSet> TotalPrice getTotalPriceAfterTaxOf(Class<T> taxSet);

    <T extends TaxSet> TotalPrice getTotalTaxOf(Class<T> taxSet);

    <T extends TaxSet> TotalPrice getTotalPriceBeforeTaxOf(Class<T> taxSet);

    SuiteId getSuiteId();

    Receipt setSuiteId(SuiteId suiteId);

    List<ReceiptElement> getReceiptElements();

    Receipt addReceiptElements(List<ReceiptElement> receiptElements);

    Receipt addReceiptElement(ReceiptElement receiptElement);

    Receipt clearReceiptElements();

    JWSPayload getDataToBeSigned();

    BillBean asBillBean();

    Receipt setJWSCompactRepresentation(JWSCompactRepresentation jwsCompactRepresentation);

    JWSCompactRepresentation getJWSCompactRepresentation();
}

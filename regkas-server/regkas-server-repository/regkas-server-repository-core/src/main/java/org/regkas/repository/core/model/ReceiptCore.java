package org.regkas.repository.core.model;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.regkas.model.ReceiptEntity;
import org.regkas.model.TimeType;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.ReceiptDate;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
import org.regkas.repository.core.mapping.ReceiptMapping;
import org.regkas.service.api.bean.ReceiptBean;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class ReceiptCore extends DomainModelCore<Receipt, ReceiptEntity> implements Receipt {

    public ReceiptCore(DomainId id,
                       Version version,
                       ReceiptId receiptId,
                       ReceiptDate receiptDate,
                       EncryptedTurnoverValue encryptedTurnoverValue,
                       SignatureValuePreviousReceipt signatureValuePreviousReceipt,
                       Company company,
                       User user,
                       ReceiptType receiptType,
                       CashBox cashBox,
                       PaymentMethod paymentMethod,
                       TimeType timeType,
                       List<ReceiptElement> receiptElements) {
        super(id, version);
        checkNotNull(receiptId, "'receiptId' must not be null");
        checkNotNull(receiptDate, "'receiptDate' must not be null");
        checkNotNull(encryptedTurnoverValue, "'encryptedTurnoverValue' must not be null");
        checkNotNull(signatureValuePreviousReceipt, "'signatureValuePreviousReceipt' must not be null");
        checkNotNull(company, "'company' must not be null");
        checkNotNull(user, "'user' must not be null");
        checkNotNull(receiptType, "'receiptType' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        checkNotNull(paymentMethod, "'paymentMethod' must not be null");
        checkNotNull(timeType, "'timeType' must not be null");
        checkNotNull(receiptElements, "'receiptElements' must not be null");
        setReceiptId(receiptId);
        setReceiptDate(receiptDate);
        setEncryptedTurnoverValue(encryptedTurnoverValue);
        setSignatureValuePreviousReceipt(signatureValuePreviousReceipt);
        setCompany(company);
        setUser(user);
        setReceiptType(receiptType);
        setCashBox(cashBox);
        setPaymentMethod(paymentMethod);
        setTimeType(timeType);
        addReceiptElements(receiptElements);
    }

    public ReceiptCore(ReceiptEntity receipt) {
        super(receipt);
    }

    @Override
    public ReceiptId getReceiptId() {
        return new ReceiptId(getEntity().getReceiptId());
    }

    @Override
    public Receipt setReceiptId(ReceiptId receiptId) {
        getEntity().setReceiptId(SimpleValueObject.nullSafe(receiptId));
        return this;
    }

    @Override
    public ReceiptDate getReceiptDate() {
        return new ReceiptDate(getEntity().getReceiptDate());
    }

    @Override
    public Receipt setReceiptDate(ReceiptDate receiptDate) {
        getEntity().setReceiptDate(SimpleValueObject.nullSafe(receiptDate));
        return this;
    }

    @Override
    public EncryptedTurnoverValue getEncryptedTurnoverValue() {
        return new EncryptedTurnoverValue(getEntity().getEncryptedTurnoverValue());
    }

    @Override
    public Receipt setEncryptedTurnoverValue(EncryptedTurnoverValue encryptedTurnoverValue) {
        getEntity().setEncryptedTurnoverValue(SimpleValueObject.nullSafe(encryptedTurnoverValue));
        return this;
    }

    @Override
    public SignatureValuePreviousReceipt getSignatureValuePreviousReceipt() {
        return new SignatureValuePreviousReceipt(getEntity().getSignatureValuePreviousReceipt());
    }

    @Override
    public Receipt setSignatureValuePreviousReceipt(SignatureValuePreviousReceipt signatureValuePreviousReceipt) {
        getEntity().setSignatureValuePreviousReceipt(SimpleValueObject.nullSafe(signatureValuePreviousReceipt));
        return this;
    }

    @Override
    public Company getCompany() {
        return new CompanyCore(getEntity().getCompany());
    }

    @Override
    public Receipt setCompany(Company company) {
        if (company != null) getEntity().setCompany(company.asEntity());
        return this;
    }

    @Override
    public User getUser() {
        return new UserCore(getEntity().getUser());
    }

    @Override
    public Receipt setUser(User user) {
        if (user != null) getEntity().setUser(user.asEntity());
        return this;
    }

    @Override
    public ReceiptType getReceiptType() {
        return new ReceiptTypeCore(getEntity().getReceiptType());
    }

    @Override
    public Receipt setReceiptType(ReceiptType receiptType) {
        if (receiptType != null) getEntity().setReceiptType(receiptType.asEntity());
        return this;
    }

    @Override
    public CashBox getCashBox() {
        return new CashBoxCore(getEntity().getCashBox());
    }

    @Override
    public Receipt setCashBox(CashBox cashBox) {
        if (cashBox != null) getEntity().setCashBox(cashBox.asEntity());
        return this;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return getEntity().getPaymentMethod();
    }

    @Override
    public Receipt setPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod != null) getEntity().setPaymentMethod(paymentMethod);
        return this;
    }

    @Override
    public TimeType getTimeType() {
        return getEntity().getTimeType();
    }

    @Override
    public Receipt setTimeType(TimeType timeType) {
        if (timeType != null) getEntity().setTimeType(timeType);
        return this;
    }

    @Override
    public List<ReceiptElement> getReceiptElements() {
        return Collections.unmodifiableList(getEntity().getReceiptElements().stream().map(ReceiptElementCore::new).collect(Collectors.toList()));
    }

    @Override
    public Receipt addReceiptElements(List<ReceiptElement> receiptElements) {
        if (receiptElements != null) {
            receiptElements.stream().map(DomainModel::asEntity).forEach((re) -> re.setReceipt(getEntity()));
            getEntity().setReceiptElements(receiptElements.stream()
                    .map(DomainModel::asEntity)
                    .collect(Collectors.toList()));
        }
        return this;
    }

    @Override
    public Receipt addReceiptElement(ReceiptElement receiptElement) {
        if (receiptElement != null) {
            getEntity().getReceiptElements().add(receiptElement.asEntity());
        }
        return this;
    }

    @Override
    public Receipt clearReceiptElements() {
        getEntity().getReceiptElements().clear();
        return this;
    }


    @Override
    public ReceiptBean asDto() {
        return ReceiptMapping.fromCDI().mapEntity(getEntity());
    }
}
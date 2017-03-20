package org.regkas.repository.core.model;

import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.values.InputForChainCalculation;
import org.regkas.repository.api.values.LastReceiptMandatory;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.SignatureMandatory;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
import org.regkas.repository.core.crypto.Crypto;

import javax.enterprise.inject.spi.CDI;
import java.util.Optional;

public abstract class ReceiptTypeCore<MODEL extends ReceiptType, ENTITY extends ReceiptTypeEntity> extends MasterDataCore<MODEL, ENTITY> implements ReceiptType<MODEL, ENTITY> {

    public ReceiptTypeCore(ENTITY receiptType) {
        super(receiptType);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public SignatureMandatory getSignatureMandatory() {
        return new SignatureMandatory(getEntity().getSignatureMandatory());
    }

    protected <T> T fromCDI(Class<T> type) {
        return CDI.current().select(type).get();
    }

    @Override
    public SignatureValuePreviousReceipt calculateChainValue(CashBox cashBox) {
        return fromCDI(Crypto.class).calculateChainValue(getInputForChainCalculation(cashBox));
    }

    protected InputForChainCalculation getInputForChainCalculation(CashBox cashBox) {
        Optional<Receipt> receipt = fromCDI(ReceiptRepository.class).loadLastReceipt(cashBox);
        if (receipt.isPresent()) {
            return new InputForChainCalculation(receipt.get().getCompactJwsRepresentation().getCompactJwsRepresentation());
        } else {
            throw new RuntimeException("no last receipt available to get the input for chain-calculation");
        }
    }

    @Override
    public LastReceiptMandatory isLastReceiptMandatory() {
        return LastReceiptMandatory.TRUE;
    }
}
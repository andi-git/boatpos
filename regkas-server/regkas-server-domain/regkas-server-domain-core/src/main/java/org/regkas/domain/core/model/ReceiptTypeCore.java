package org.regkas.domain.core.model;

import org.boatpos.common.domain.core.model.MasterDataCore;
import org.regkas.domain.core.crypto.Crypto;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.values.InputForChainCalculation;
import org.regkas.domain.api.values.LastReceiptMandatory;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.SignatureMandatory;
import org.regkas.domain.api.values.SignatureValuePreviousReceipt;

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
package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.MasterData;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.api.values.LastReceiptMandatory;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.SignatureValuePreviousReceipt;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.domain.api.values.SignatureMandatory;

public interface ReceiptType<MODEL extends ReceiptType, ENTITY extends ReceiptTypeEntity> extends MasterData<MODEL, ENTITY> {

    Name getName();

    SignatureMandatory getSignatureMandatory();

    UpdateTurnoverCounter getUpdateTurnoverCounter();

    EncryptTurnoverCounter getEncryptTurnoverCounter();

    SignatureValuePreviousReceipt calculateChainValue(CashBox cashBox);

    LastReceiptMandatory isLastReceiptMandatory();
}

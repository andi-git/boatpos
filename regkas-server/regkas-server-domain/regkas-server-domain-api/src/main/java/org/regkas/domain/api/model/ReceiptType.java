package org.regkas.domain.api.model;

import java.util.List;

import org.boatpos.common.domain.api.model.MasterData;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.SignatureMandatory;
import org.regkas.domain.api.values.SignatureValuePreviousReceipt;
import org.regkas.model.ReceiptTypeEntity;

public interface ReceiptType<MODEL extends ReceiptType, ENTITY extends ReceiptTypeEntity> extends MasterData<MODEL, ENTITY> {

    Name getName();

    SignatureMandatory getSignatureMandatory();

    UpdateTurnoverCounter getUpdateTurnoverCounter();

    EncryptTurnoverCounter getEncryptTurnoverCounter();

    SignatureValuePreviousReceipt calculateChainValue(CashBox cashBox);

    List<Class<? extends Precondition>> getPreconditions();
}

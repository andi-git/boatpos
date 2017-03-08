package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.SignatureMandatory;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;

/**
 * The domain model for a receipt-type.
 */
public interface ReceiptType<MODEL extends ReceiptType, ENTITY extends ReceiptTypeEntity> extends MasterData<MODEL, ENTITY> {

    Name getName();

    SignatureMandatory getSignatureMandatory();

    UpdateTurnoverCounter getUpdateTurnoverCounter();

    EncryptTurnoverCounter getEncryptTurnoverCounter();

    SignatureValuePreviousReceipt calculateChainValue(CashBox cashBox);
}

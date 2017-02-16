package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.api.values.Name;

/**
 * The domain model for a receipt-type.
 */
public interface ReceiptType<MODEL extends ReceiptType, ENTITY extends ReceiptTypeEntity> extends MasterData<MODEL, ENTITY> {

    Name getName();

    UpdateTurnoverCounter getUpdateTurnoverCounter();

    EncryptTurnoverCounter getEncryptTurnoverCounter();
}

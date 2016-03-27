package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.values.Name;

/**
 * The domain model for a receipt-type.
 */
public interface ReceiptType extends MasterData<ReceiptType, ReceiptTypeEntity> {

    Name getName();

    ReceiptType setName(Name name);
}

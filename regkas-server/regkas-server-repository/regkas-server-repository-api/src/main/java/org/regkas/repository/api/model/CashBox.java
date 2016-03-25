package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.regkas.model.CashBoxEntity;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.SignatureCertificateSerialNumber;

/**
 * The domain model for a cash-box.
 */
public interface CashBox extends MasterData<CashBox, CashBoxEntity> {

    Name getName();

    CashBox setName(Name name);

    SignatureCertificateSerialNumber getSignatureCertificateSerialNumber();

    CashBox setSignatureCertificateSerialNumber(SignatureCertificateSerialNumber signatureCertificateSerialNumber);
}

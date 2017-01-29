package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.regkas.model.CashBoxEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.values.AESKeyBase64;
import org.regkas.repository.api.values.IpAddress;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.SignatureCertificateSerialNumber;

/**
 * Builder for {@link CashBox}.
 */
public interface CashBoxBuilder extends MasterDataBuilder<CashBoxBuilder, CashBox, CashBoxEntity> {

    CashBoxBuilder add(Name name);

    CashBoxBuilder add(SignatureCertificateSerialNumber signatureCertificateSerialNumber);

    CashBoxBuilder add(IpAddress printerIpAddress);

    CashBoxBuilder add(AESKeyBase64 aesKeyBase64);
}

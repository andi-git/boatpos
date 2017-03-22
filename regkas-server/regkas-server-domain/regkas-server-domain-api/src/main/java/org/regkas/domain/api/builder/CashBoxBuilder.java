package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilder;
import org.regkas.domain.api.values.AESKeyBase64;
import org.regkas.domain.api.values.CertificationServiceProvider;
import org.regkas.domain.api.values.IpAddress;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.RkOnlinePassword;
import org.regkas.domain.api.values.RkOnlineUsername;
import org.regkas.domain.api.values.SignatureCertificateSerialNumber;
import org.regkas.domain.api.values.TotalPriceCent;
import org.regkas.model.CashBoxEntity;
import org.regkas.domain.api.model.CashBox;

/**
 * Builder for {@link CashBox}.
 */
public interface CashBoxBuilder extends MasterDataBuilder<CashBoxBuilder, CashBox, CashBoxEntity> {

    CashBoxBuilder add(Name name);

    CashBoxBuilder add(SignatureCertificateSerialNumber signatureCertificateSerialNumber);

    CashBoxBuilder add(IpAddress printerIpAddress);

    CashBoxBuilder add(AESKeyBase64 aesKeyBase64);

    CashBoxBuilder add(TotalPriceCent totalPriceCent);

    CashBoxBuilder add(CertificationServiceProvider certificationServiceProvider);

    CashBoxBuilder add(RkOnlineUsername rkOnlineUsername);

    CashBoxBuilder add(RkOnlinePassword rkOnlinePassword);
}

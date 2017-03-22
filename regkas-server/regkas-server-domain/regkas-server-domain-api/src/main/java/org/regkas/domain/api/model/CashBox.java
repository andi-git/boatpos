package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.MasterData;
import org.regkas.domain.api.values.AESKeyBase64;
import org.regkas.domain.api.values.CertificationServiceProvider;
import org.regkas.domain.api.values.IpAddress;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.RkOnlinePassword;
import org.regkas.domain.api.values.RkOnlineUsername;
import org.regkas.domain.api.values.SignatureCertificateSerialNumber;
import org.regkas.domain.api.values.TotalPriceCent;
import org.regkas.model.CashBoxEntity;

/**
 * The domain model for a cash-box.
 */
public interface CashBox extends MasterData<CashBox, CashBoxEntity> {

    Name getName();

    CashBox setName(Name name);

    SignatureCertificateSerialNumber getSignatureCertificateSerialNumber();

    CashBox setSignatureCertificateSerialNumber(SignatureCertificateSerialNumber signatureCertificateSerialNumber);

    IpAddress getPrinterIpAddress();

    CashBox setPrinterIpAddress(IpAddress printerIpAddress);

    AESKeyBase64 getAesKeyBase64();

    CashBox setAesKeyBase64(AESKeyBase64 aesKeyBase64);

    TotalPriceCent getTurnoverCountCent();

    void addCentsToTurnoverCount(TotalPriceCent totalPriceCent);

    void subtractCentsFromTurnoverCount(TotalPriceCent totalPriceCent);

    CertificationServiceProvider getCertificationServiceProvider();

    CashBox setRkOnlineUsername(RkOnlineUsername rkOnlineUsername);

    RkOnlineUsername getRkOnlineUsername();

    CashBox setRkOnlinePassword(RkOnlinePassword rkOnlinePassword);

    RkOnlinePassword getRkOnlinePassword();

    Company getCompany();
}

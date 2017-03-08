package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.regkas.model.CashBoxEntity;
import org.regkas.repository.api.values.*;

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

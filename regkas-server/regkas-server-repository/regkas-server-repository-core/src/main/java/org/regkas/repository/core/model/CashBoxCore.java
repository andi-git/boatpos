package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.CashBoxEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.values.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class CashBoxCore extends MasterDataCore<CashBox, CashBoxEntity> implements CashBox {

    public CashBoxCore(DomainId id,
                       Version version,
                       Enabled enabled,
                       Priority priority,
                       KeyBinding keyBinding,
                       PictureUrl pictureUrl,
                       PictureUrlThumb pictureUrlThumb,
                       Name name,
                       SignatureCertificateSerialNumber signatureCertificateSerialNumber,
                       IpAddress printerIpAddress,
                       AESKeyBase64 aesKeyBase64,
                       TotalPriceCent totalPriceCent,
                       CertificationServiceProvider certificationServiceProvider) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(signatureCertificateSerialNumber, "'signatureCertificateSerialNumber' must not be null");
        checkNotNull(printerIpAddress, "'printerIpAddress' must not be null");
        checkNotNull(aesKeyBase64, "'aesKeyBas64' must not be null");
        checkNotNull(totalPriceCent, "'totalPriceCent' must not be null");
        checkNotNull(certificationServiceProvider, "'certificationServiceProvider' must not be null");
        setName(name);
        setSignatureCertificateSerialNumber(signatureCertificateSerialNumber);
        setPrinterIpAddress(printerIpAddress);
        setAesKeyBase64(aesKeyBase64);
        setTurnoverCountCent(totalPriceCent);
        setCertificationServiceProvider(certificationServiceProvider);
    }

    public CashBoxCore(CashBoxEntity cashBox) {
        super(cashBox);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public CashBox setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public SignatureCertificateSerialNumber getSignatureCertificateSerialNumber() {
        return new SignatureCertificateSerialNumber(getEntity().getSignatureCertificateSerialNumber());
    }

    @Override
    public CashBox setSignatureCertificateSerialNumber(SignatureCertificateSerialNumber signatureCertificateSerialNumber) {
        getEntity().setSignatureCertificateSerialNumber(SimpleValueObject.nullSafe(signatureCertificateSerialNumber));
        return this;
    }

    @Override
    public IpAddress getPrinterIpAddress() {
        return new IpAddress(getEntity().getPrinterIpAddress());
    }

    @Override
    public CashBox setPrinterIpAddress(IpAddress printerIpAddress) {
        getEntity().setPrinterIpAddress(SimpleValueObject.nullSafe(printerIpAddress));
        return this;
    }

    @Override
    public AESKeyBase64 getAesKeyBase64() {
        return new AESKeyBase64(getEntity().getAesKeyBase64());
    }

    @Override
    public CashBox setAesKeyBase64(AESKeyBase64 aesKeyBase64) {
        getEntity().setAesKeyBase64(SimpleValueObject.nullSafe(aesKeyBase64));
        return this;
    }

    @Override
    public TotalPriceCent getTurnoverCountCent() {
        return new TotalPriceCent(getEntity().getTurnoverCountCent());
    }

    @Override
    public void addCentsToTurnoverCount(TotalPriceCent centsToAdd) {
        setTurnoverCountCent(getTurnoverCountCent().add(centsToAdd)).persist();
    }

    @Override
    public void subtractCentsFromTurnoverCount(TotalPriceCent centsToSubtract) {
        setTurnoverCountCent(getTurnoverCountCent().subtract(centsToSubtract)).persist();
    }

    @Override
    public CertificationServiceProvider getCertificationServiceProvider() {
        return new CertificationServiceProvider(getEntity().getCertificationServiceProvider());
    }

    private CashBox setTurnoverCountCent(TotalPriceCent totalPriceCent) {
        getEntity().setTurnoverCountCent(SimpleValueObject.nullSafe(totalPriceCent));
        return this;
    }

    private CashBox setCertificationServiceProvider(CertificationServiceProvider certificationServiceProvider) {
        getEntity().setCertificationServiceProvider(SimpleValueObject.nullSafe(certificationServiceProvider));
        return this;
    }
}
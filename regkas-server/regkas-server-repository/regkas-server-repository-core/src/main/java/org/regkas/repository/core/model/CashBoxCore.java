package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.CashBoxEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.values.IpAddress;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.SignatureCertificateSerialNumber;

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
                       IpAddress printerIpAddress) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(signatureCertificateSerialNumber, "'signatureCertificateSerialNumber' must not be null");
        checkNotNull(printerIpAddress, "'printerIpAddress' must not be null");
        setName(name);
        setSignatureCertificateSerialNumber(signatureCertificateSerialNumber);
        setPrinterIpAddress(printerIpAddress);
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
}
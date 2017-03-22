package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCore;
import org.regkas.model.CashBoxEntity;
import org.regkas.repository.api.builder.CashBoxBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.values.*;
import org.regkas.repository.core.model.CashBoxCore;

import javax.enterprise.context.Dependent;

@Dependent
public class CashBoxBuilderCore
        extends MasterDataBuilderCore<CashBoxBuilder, CashBox, CashBoxCore, CashBoxEntity>
        implements CashBoxBuilder {

    private Name name;
    private SignatureCertificateSerialNumber signatureCertificateSerialNumber;
    private IpAddress printerIpAddress;
    private AESKeyBase64 aesKeyBase64;
    private TotalPriceCent totalPriceCent;
    private CertificationServiceProvider certificationServiceProvider;
    private RkOnlineUsername rkOnlineUsername;
    private RkOnlinePassword rkOnlinePassword;

    @Override
    public CashBox build() {
        return new CashBoxCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name, signatureCertificateSerialNumber, printerIpAddress, aesKeyBase64, totalPriceCent, certificationServiceProvider, rkOnlineUsername, rkOnlinePassword);
    }

    @Override
    public CashBoxBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public CashBoxBuilder add(SignatureCertificateSerialNumber signatureCertificateSerialNumber) {
        this.signatureCertificateSerialNumber = signatureCertificateSerialNumber;
        return this;
    }

    @Override
    public CashBoxBuilder add(IpAddress printerIpAddress) {
        this.printerIpAddress = printerIpAddress;
        return this;
    }

    @Override
    public CashBoxBuilder add(AESKeyBase64 aesKeyBase64) {
        this.aesKeyBase64 = aesKeyBase64;
        return this;
    }

    @Override
    public CashBoxBuilder add(TotalPriceCent totalPriceCent) {
        this.totalPriceCent = totalPriceCent;
        return this;
    }

    @Override
    public CashBoxBuilder add(CertificationServiceProvider certificationServiceProvider) {
        this.certificationServiceProvider = certificationServiceProvider;
        return this;
    }

    @Override
    public CashBoxBuilder add(RkOnlineUsername rkOnlineUsername) {
        this.rkOnlineUsername = rkOnlineUsername;
        return this;
    }

    @Override
    public CashBoxBuilder add(RkOnlinePassword rkOnlinePassword) {
        this.rkOnlinePassword = rkOnlinePassword;
        return this;
    }
}

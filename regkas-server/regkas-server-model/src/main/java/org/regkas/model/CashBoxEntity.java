package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.swing.text.StyleConstants.Size;

/**
 * Entity of a cash-box.
 */
@Entity
@Table(name = "cashbox")
public class CashBoxEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Size(min = 0, max = 50)
    @Expose
    private String signatureCertificateSerialNumber;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CompanyEntity company;

    @Size(min = 5, max = 15)
    @Expose
    private String printerIpAddress;

    @Size(min = 44, max = 44)
    @NotNull
    private String aesKeyBase64;

    @Min(0)
    @NotNull
    private Integer turnoverCountCent;

    public CashBoxEntity() {

    }

    public CashBoxEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, String signatureCertificateSerialNumber, CompanyEntity company, String printerIpAddress, String aesKeyBase64, Integer turnoverCountCent) {
        super(id, version, enabled, priority, '#', pictureUrl, pictureUrlThumb);
        this.name = name;
        this.signatureCertificateSerialNumber = signatureCertificateSerialNumber;
        this.company = company;
        this.printerIpAddress = printerIpAddress;
        this.aesKeyBase64 = aesKeyBase64;
        this.turnoverCountCent = turnoverCountCent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignatureCertificateSerialNumber() {
        return signatureCertificateSerialNumber;
    }

    public void setSignatureCertificateSerialNumber(String signatureCertificateSerialNumber) {
        this.signatureCertificateSerialNumber = signatureCertificateSerialNumber;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public String getPrinterIpAddress() {
        return printerIpAddress;
    }

    public void setPrinterIpAddress(String printerIpAddress) {
        this.printerIpAddress = printerIpAddress;
    }

    public String getAesKeyBase64() {
        return aesKeyBase64;
    }

    public void setAesKeyBase64(String aesKeyBase64) {
        this.aesKeyBase64 = aesKeyBase64;
    }

    public Integer getTurnoverCountCent() {
        return turnoverCountCent;
    }

    public void setTurnoverCountCent(Integer turnoverCountCent) {
        this.turnoverCountCent = turnoverCountCent;
    }
}

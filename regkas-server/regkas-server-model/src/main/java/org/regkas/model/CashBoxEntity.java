package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
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
    @Size(min = 3, max = 50)
    @Expose
    private String signatureCertificateSerialNumber;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CompanyEntity company;

    public CashBoxEntity() {

    }

    public CashBoxEntity(Long id, Integer version, boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, String signatureCertificateSerialNumber, CompanyEntity company) {
        super(id, version, enabled, priority, ' ', pictureUrl, pictureUrlThumb);
        this.name = name;
        this.signatureCertificateSerialNumber = signatureCertificateSerialNumber;
        this.company = company;
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
}

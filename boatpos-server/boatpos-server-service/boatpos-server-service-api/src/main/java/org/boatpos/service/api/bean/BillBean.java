package org.boatpos.service.api.bean;

import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.service.api.bean.LocalDateTimeAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a boat-rental.
 */
@SuppressWarnings("unused")
public class BillBean extends AbstractBean {

    //REF TO SPECIFICATION: Detailspezifikation/Abs 4, Abs 5
    //    @SerializedName("Kassen-ID")
    private String cashBoxID;

    //    @SerializedName("Belegnummer")
    private String receiptIdentifier;

    //    @SerializedName("Beleg-Datum-Uhrzeit")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime receiptDateAndTime;

    //    @SerializedName("Betrag-Satz-Normal")
    private BigDecimal sumTaxSetNormal = BigDecimal.ZERO;

    //    @SerializedName("Betrag-Satz-Ermaessigt-1")
    private BigDecimal sumTaxSetErmaessigt1 = BigDecimal.ZERO;

    //    @SerializedName("Betrag-Satz-Ermaessigt-2")
    private BigDecimal sumTaxSetErmaessigt2 = BigDecimal.ZERO;

    //    @SerializedName("Betrag-Satz-Null")
    private BigDecimal sumTaxSetNull = BigDecimal.ZERO;

    //    @SerializedName("Betrag-Satz-Besonders")
    private BigDecimal sumTaxSetBesonders = BigDecimal.ZERO;

    //    @SerializedName("Stand-Umsatz-Zaehler-AES256-ICM")
    private String encryptedTurnoverValue;

    //    @SerializedName("Zertifikat-Seriennummer")
    private String signatureCertificateSerialNumber;

    //    @SerializedName("Sig-Voriger-Beleg")
    private String signatureValuePreviousReceipt;

    private CompanyBean company;

    private List<TaxSetBean.TaxSetNormalBean> taxSetNormal = new ArrayList<>();

    private List<TaxSetBean.TaxSetErmaessigt1Bean> taxSetErmaessigt1 = new ArrayList<>();

    private List<TaxSetBean.TaxSetErmaessigt2Bean> taxSetErmaessigt2 = new ArrayList<>();

    private List<TaxSetBean.TaxSetBesonderesBean> taxSetBesonderesBean = new ArrayList<>();

    private List<TaxSetBean.TaxSetNullBean> taxSetNullBean = new ArrayList<>();

    public BillBean() {
    }

    public BillBean(CompanyBean company, String cashBoxID, String receiptIdentifier, LocalDateTime receiptDateAndTime, List<TaxSetBean.TaxSetNormalBean> taxSetNormal, List<TaxSetBean.TaxSetErmaessigt1Bean> taxSetErmaessigt1, List<TaxSetBean.TaxSetErmaessigt2Bean> taxSetErmaessigt2, List<TaxSetBean.TaxSetBesonderesBean> taxSetBesonderesBean, List<TaxSetBean.TaxSetNullBean> taxSetNullBean) {
        this.cashBoxID = cashBoxID;
        this.receiptIdentifier = receiptIdentifier;
        this.receiptDateAndTime = receiptDateAndTime;
        this.company = company;
        this.taxSetNormal.addAll(taxSetNormal);
        this.taxSetErmaessigt1.addAll(taxSetErmaessigt1);
        this.taxSetErmaessigt2.addAll(taxSetErmaessigt2);
        this.taxSetBesonderesBean.addAll(taxSetBesonderesBean);
        this.taxSetNullBean.addAll(taxSetNullBean);
        if (this.taxSetNormal != null) {
            taxSetNormal.stream().forEach((p) -> sumTaxSetNormal = sumTaxSetNormal.add(p.getValue()));
        }
        if (this.taxSetErmaessigt1 != null) {
            taxSetErmaessigt1.stream().forEach((p) -> sumTaxSetErmaessigt1 = sumTaxSetErmaessigt1.add(p.getValue()));
        }
        if (this.taxSetErmaessigt2 != null) {
            taxSetErmaessigt2.stream().forEach((p) -> sumTaxSetErmaessigt2 = sumTaxSetErmaessigt2.add(p.getValue()));
        }
        if (this.taxSetBesonderesBean != null) {
            taxSetBesonderesBean.stream().forEach((p) -> sumTaxSetBesonders = sumTaxSetBesonders.add(p.getValue()));
        }
        if (this.taxSetNullBean != null) {
            taxSetNullBean.stream().forEach((p) -> sumTaxSetNull = sumTaxSetNull.add(p.getValue()));
        }
    }

    public String getCashBoxID() {
        return cashBoxID;
    }

    public void setCashBoxID(String cashBoxID) {
        this.cashBoxID = cashBoxID;
    }

    public String getReceiptIdentifier() {
        return receiptIdentifier;
    }

    public void setReceiptIdentifier(String receiptIdentifier) {
        this.receiptIdentifier = receiptIdentifier;
    }

    public LocalDateTime getReceiptDateAndTime() {
        return receiptDateAndTime;
    }

    public void setReceiptDateAndTime(LocalDateTime receiptDateAndTime) {
        this.receiptDateAndTime = receiptDateAndTime;
    }

    public BigDecimal getSumTaxSetNormal() {
        return sumTaxSetNormal;
    }

    public void setSumTaxSetNormal(BigDecimal sumTaxSetNormal) {
        this.sumTaxSetNormal = sumTaxSetNormal;
    }

    public BigDecimal getSumTaxSetErmaessigt1() {
        return sumTaxSetErmaessigt1;
    }

    public void setSumTaxSetErmaessigt1(BigDecimal sumTaxSetErmaessigt1) {
        this.sumTaxSetErmaessigt1 = sumTaxSetErmaessigt1;
    }

    public BigDecimal getSumTaxSetErmaessigt2() {
        return sumTaxSetErmaessigt2;
    }

    public void setSumTaxSetErmaessigt2(BigDecimal sumTaxSetErmaessigt2) {
        this.sumTaxSetErmaessigt2 = sumTaxSetErmaessigt2;
    }

    public BigDecimal getSumTaxSetNull() {
        return sumTaxSetNull;
    }

    public void setSumTaxSetNull(BigDecimal sumTaxSetNull) {
        this.sumTaxSetNull = sumTaxSetNull;
    }

    public BigDecimal getSumTaxSetBesonders() {
        return sumTaxSetBesonders;
    }

    public void setSumTaxSetBesonders(BigDecimal sumTaxSetBesonders) {
        this.sumTaxSetBesonders = sumTaxSetBesonders;
    }

    public String getEncryptedTurnoverValue() {
        return encryptedTurnoverValue;
    }

    public void setEncryptedTurnoverValue(String encryptedTurnoverValue) {
        this.encryptedTurnoverValue = encryptedTurnoverValue;
    }

    public String getSignatureCertificateSerialNumber() {
        return signatureCertificateSerialNumber;
    }

    public void setSignatureCertificateSerialNumber(String signatureCertificateSerialNumber) {
        this.signatureCertificateSerialNumber = signatureCertificateSerialNumber;
    }

    public String getSignatureValuePreviousReceipt() {
        return signatureValuePreviousReceipt;
    }

    public void setSignatureValuePreviousReceipt(String signatureValuePreviousReceipt) {
        this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
    }

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public List<TaxSetBean.TaxSetNormalBean> getTaxSetNormal() {
        return taxSetNormal;
    }

    public void setTaxSetNormal(List<TaxSetBean.TaxSetNormalBean> taxSetNormal) {
        this.taxSetNormal = taxSetNormal;
    }

    public List<TaxSetBean.TaxSetErmaessigt1Bean> getTaxSetErmaessigt1() {
        return taxSetErmaessigt1;
    }

    public void setTaxSetErmaessigt1(List<TaxSetBean.TaxSetErmaessigt1Bean> taxSetErmaessigt1) {
        this.taxSetErmaessigt1 = taxSetErmaessigt1;
    }

    public List<TaxSetBean.TaxSetErmaessigt2Bean> getTaxSetErmaessigt2() {
        return taxSetErmaessigt2;
    }

    public void setTaxSetErmaessigt2(List<TaxSetBean.TaxSetErmaessigt2Bean> taxSetErmaessigt2) {
        this.taxSetErmaessigt2 = taxSetErmaessigt2;
    }

    public List<TaxSetBean.TaxSetBesonderesBean> getTaxSetBesonderesBean() {
        return taxSetBesonderesBean;
    }

    public void setTaxSetBesonderesBean(List<TaxSetBean.TaxSetBesonderesBean> taxSetBesonderesBean) {
        this.taxSetBesonderesBean = taxSetBesonderesBean;
    }

    public List<TaxSetBean.TaxSetNullBean> getTaxSetNullBean() {
        return taxSetNullBean;
    }

    public void setTaxSetNullBean(List<TaxSetBean.TaxSetNullBean> taxSetNullBean) {
        this.taxSetNullBean = taxSetNullBean;
    }
}

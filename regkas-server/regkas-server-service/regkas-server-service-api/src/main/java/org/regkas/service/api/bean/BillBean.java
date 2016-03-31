package org.regkas.service.api.bean;

import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.service.api.bean.LocalDateTimeAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a bill / payment.
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
    private BigDecimal sumTaxSetNormal = new BigDecimal("0.00");

    //    @SerializedName("Betrag-Satz-Ermaessigt-1")
    private BigDecimal sumTaxSetErmaessigt1 = new BigDecimal("0.00");

    //    @SerializedName("Betrag-Satz-Ermaessigt-2")
    private BigDecimal sumTaxSetErmaessigt2 = new BigDecimal("0.00");

    //    @SerializedName("Betrag-Satz-Null")
    private BigDecimal sumTaxSetNull = new BigDecimal("0.00");

    //    @SerializedName("Betrag-Satz-Besonders")
    private BigDecimal sumTaxSetBesonders = new BigDecimal("0.00");

    //    @SerializedName("Stand-Umsatz-Zaehler-AES256-ICM")
    private String encryptedTurnoverValue;

    //    @SerializedName("Zertifikat-Seriennummer")
    private String signatureCertificateSerialNumber;

    //    @SerializedName("Sig-Voriger-Beleg")
    private String signatureValuePreviousReceipt;

    private CompanyBean company;

    private List<BillTaxSetElementBean> billTaxSetElements = new ArrayList<>();

    private BigDecimal sumTotal = new BigDecimal("0.00");

    public BillBean() {
    }

    public BillBean(CompanyBean company, String cashBoxID, String receiptIdentifier, LocalDateTime receiptDateAndTime, BigDecimal sumTaxSetNormal, BigDecimal sumTaxSetErmaessigt1, BigDecimal sumTaxSetErmaessigt2, BigDecimal sumTaxSetBesonderes, BigDecimal sumTaxSetNull, List<BillTaxSetElementBean> billTaxSetElements, BigDecimal sumTotal) {
        this.cashBoxID = cashBoxID;
        this.receiptIdentifier = receiptIdentifier;
        this.receiptDateAndTime = receiptDateAndTime;
        this.company = company;
        this.sumTaxSetNormal = sumTaxSetNormal;
        this.sumTaxSetErmaessigt1 = sumTaxSetErmaessigt1;
        this.sumTaxSetErmaessigt2 = sumTaxSetErmaessigt2;
        this.sumTaxSetBesonders = sumTaxSetBesonderes;
        this.sumTaxSetNull = sumTaxSetNull;
        this.billTaxSetElements = billTaxSetElements;
        this.sumTotal = sumTotal;
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

    public List<BillTaxSetElementBean> getBillTaxSetElements() {
        return billTaxSetElements;
    }

    public void setBillTaxSetElements(List<BillTaxSetElementBean> billTaxSetElements) {
        this.billTaxSetElements = billTaxSetElements;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
    }
}

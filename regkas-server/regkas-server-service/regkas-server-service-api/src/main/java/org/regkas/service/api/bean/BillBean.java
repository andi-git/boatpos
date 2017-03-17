package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.service.api.bean.LocalDateTimeAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representation of a bill / payment.
 */
@SuppressWarnings("unused")
public class BillBean extends AbstractBean {

    //REF TO SPECIFICATION: Detailspezifikation/Abs 4, Abs 5
    @SerializedName("Kassen-ID")
    @Expose
    private String cashBoxID;

    @SerializedName("Belegnummer")
    @Expose
    private String receiptIdentifier;

    @SerializedName("Beleg-Datum-Uhrzeit")
    @Expose
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime receiptDateAndTime;

    @SerializedName("Betrag-Satz-Normal")
    @Expose
    private BigDecimal sumTaxSetNormal = new BigDecimal("0.00");

    @SerializedName("Betrag-Satz-Ermaessigt-1")
    @Expose
    private BigDecimal sumTaxSetErmaessigt1 = new BigDecimal("0.00");

    @SerializedName("Betrag-Satz-Ermaessigt-2")
    @Expose
    private BigDecimal sumTaxSetErmaessigt2 = new BigDecimal("0.00");

    @SerializedName("Betrag-Satz-Null")
    @Expose
    private BigDecimal sumTaxSetNull = new BigDecimal("0.00");

    @SerializedName("Betrag-Satz-Besonders")
    @Expose
    private BigDecimal sumTaxSetBesonders = new BigDecimal("0.00");

    @SerializedName("Stand-Umsatz-Zaehler-AES256-ICM")
    @Expose
    private String encryptedTurnoverValue;

    @SerializedName("Zertifikat-Seriennummer")
    @Expose
    private String signatureCertificateSerialNumber;

    @SerializedName("Sig-Voriger-Beleg")
    @Expose
    private String signatureValuePreviousReceipt;

    private CompanyBean company;

    @SerializedName("Belegelemente")
    @Expose
    private List<BillTaxSetElementBean> billTaxSetElements = new ArrayList<>();

    @SerializedName("Gesamtbetrag")
    @Expose
    private BigDecimal sumTotal = new BigDecimal("0.00");

    @SerializedName("Beleg-Art")
    @Expose
    private String receiptType = "Standard-Beleg";

    @SerializedName("JWS-Kompakt")
    @Expose
    private String jwsCompact;

    private BillBean sammelBeleg;

    public BillBean() {
    }

    public BillBean(CompanyBean company, String cashBoxID, String receiptIdentifier, LocalDateTime receiptDateAndTime, BigDecimal sumTaxSetNormal, BigDecimal sumTaxSetErmaessigt1, BigDecimal sumTaxSetErmaessigt2, BigDecimal sumTaxSetBesonderes, BigDecimal sumTaxSetNull, List<BillTaxSetElementBean> billTaxSetElements, BigDecimal sumTotal, String receiptType, String jwsCompact) {
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
        this.receiptType = receiptType;
        this.jwsCompact = jwsCompact;
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

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getJwsCompact() {
        return jwsCompact;
    }

    public void setJwsCompact(String jwsCompact) {
        this.jwsCompact = jwsCompact;
    }

    public BillBean getSammelBeleg() {
        return sammelBeleg;
    }

    public void setSammelBeleg(BillBean sammelBeleg) {
        this.sammelBeleg = sammelBeleg;
    }

    public static BillBean fromJwsCompact(String jwsCompact) {
        BillBean billBean = new BillBean();
        String[] jwsCompatElement = jwsCompact.split("_");
        billBean.setCashBoxID(jwsCompatElement[2]);
        billBean.setReceiptIdentifier(jwsCompatElement[3]);
        billBean.setReceiptDateAndTime(LocalDateTime.parse(jwsCompatElement[4]));
        billBean.setSumTaxSetNormal(new BigDecimal(jwsCompatElement[5].replace(",", ".")));
        billBean.setSumTaxSetErmaessigt1(new BigDecimal(jwsCompatElement[6].replace(",", ".")));
        billBean.setSumTaxSetErmaessigt2(new BigDecimal(jwsCompatElement[7].replace(",", ".")));
        billBean.setSumTaxSetNull(new BigDecimal(jwsCompatElement[8].replace(",", ".")));
        billBean.setSumTaxSetBesonders(new BigDecimal(jwsCompatElement[9].replace(",", ".")));
        billBean.setEncryptedTurnoverValue(jwsCompatElement[10]);
        billBean.setSignatureCertificateSerialNumber(jwsCompatElement[11]);
        billBean.setSignatureValuePreviousReceipt(jwsCompatElement[12]);
        billBean.setJwsCompact(jwsCompact);
        return billBean;
    }
}

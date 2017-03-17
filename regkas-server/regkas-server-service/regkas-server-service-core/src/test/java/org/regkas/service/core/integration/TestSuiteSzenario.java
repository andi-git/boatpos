package org.regkas.service.core.integration;

import com.google.gson.annotations.Expose;

import java.util.List;

@SuppressWarnings("unused")
public class TestSuiteSzenario {

    @Expose
    private String cashBoxId;

    @Expose
    private String base64AesKey;

    @Expose
    private String companyID;

    @Expose
    private String simulationRunLabel;

    @Expose
    private String numberOfSignatureDevices;

    @Expose
    private List<CashBoxInstruction> cashBoxInstructionList;

    public String getCashBoxId() {
        return cashBoxId;
    }

    public void setCashBoxId(String cashBoxId) {
        this.cashBoxId = cashBoxId;
    }

    public String getBase64AesKey() {
        return base64AesKey;
    }

    public void setBase64AesKey(String base64AesKey) {
        this.base64AesKey = base64AesKey;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getSimulationRunLabel() {
        return simulationRunLabel;
    }

    public void setSimulationRunLabel(String simulationRunLabel) {
        this.simulationRunLabel = simulationRunLabel;
    }

    public String getNumberOfSignatureDevices() {
        return numberOfSignatureDevices;
    }

    public void setNumberOfSignatureDevices(String numberOfSignatureDevices) {
        this.numberOfSignatureDevices = numberOfSignatureDevices;
    }

    public List<CashBoxInstruction> getCashBoxInstructionList() {
        return cashBoxInstructionList;
    }

    public void setCashBoxInstructionList(List<CashBoxInstruction> cashBoxInstructionList) {
        this.cashBoxInstructionList = cashBoxInstructionList;
    }

    public static class CashBoxInstruction {

        @Expose
        private String signatureDeviceDamaged;

        @Expose
        private String receiptIdentifier;

        @Expose
        private String dateToUse;

        @Expose
        private String usedSignatureDevice;

        @Expose
        private SimplifiedReceipt simplifiedReceipt;

        @Expose
        private String typeOfReceipt;

        public String getSignatureDeviceDamaged() {
            return signatureDeviceDamaged;
        }

        public void setSignatureDeviceDamaged(String signatureDeviceDamaged) {
            this.signatureDeviceDamaged = signatureDeviceDamaged;
        }

        public String getReceiptIdentifier() {
            return receiptIdentifier;
        }

        public void setReceiptIdentifier(String receiptIdentifier) {
            this.receiptIdentifier = receiptIdentifier;
        }

        public String getDateToUse() {
            return dateToUse;
        }

        public void setDateToUse(String dateToUse) {
            this.dateToUse = dateToUse;
        }

        public String getUsedSignatureDevice() {
            return usedSignatureDevice;
        }

        public void setUsedSignatureDevice(String usedSignatureDevice) {
            this.usedSignatureDevice = usedSignatureDevice;
        }

        public SimplifiedReceipt getSimplifiedReceipt() {
            return simplifiedReceipt;
        }

        public void setSimplifiedReceipt(SimplifiedReceipt simplifiedReceipt) {
            this.simplifiedReceipt = simplifiedReceipt;
        }

        public String getTypeOfReceipt() {
            return typeOfReceipt;
        }

        public void setTypeOfReceipt(String typeOfReceipt) {
            this.typeOfReceipt = typeOfReceipt;
        }
    }

    public static class SimplifiedReceipt {

        @Expose
        private String taxSetNormal;

        @Expose
        private String taxSetErmaessigt1;

        @Expose
        private String taxSetErmaessigt2;

        @Expose
        private String taxSetNull;

        @Expose
        private String taxSetBesonders;

        public String getTaxSetNormal() {
            return taxSetNormal;
        }

        public void setTaxSetNormal(String taxSetNormal) {
            this.taxSetNormal = taxSetNormal;
        }

        public String getTaxSetErmaessigt1() {
            return taxSetErmaessigt1;
        }

        public void setTaxSetErmaessigt1(String taxSetErmaessigt1) {
            this.taxSetErmaessigt1 = taxSetErmaessigt1;
        }

        public String getTaxSetErmaessigt2() {
            return taxSetErmaessigt2;
        }

        public void setTaxSetErmaessigt2(String taxSetErmaessigt2) {
            this.taxSetErmaessigt2 = taxSetErmaessigt2;
        }

        public String getTaxSetNull() {
            return taxSetNull;
        }

        public void setTaxSetNull(String taxSetNull) {
            this.taxSetNull = taxSetNull;
        }

        public String getTaxSetBesonders() {
            return taxSetBesonders;
        }

        public void setTaxSetBesonders(String taxSetBesonders) {
            this.taxSetBesonders = taxSetBesonders;
        }
    }
}

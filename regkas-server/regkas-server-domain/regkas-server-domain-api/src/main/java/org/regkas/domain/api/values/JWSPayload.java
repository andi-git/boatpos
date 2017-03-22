package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * A String-based representation of a signature.
 */
@SuppressWarnings("unused")
public class JWSPayload extends SimpleValueObject<JWSPayload, String> {

    public JWSPayload(String value) {
        super(value);
    }

    public static class Builder {

        private SuiteId suiteId;

        private ReceiptId receiptId;

        private Name cashBoxName;

        private ReceiptDate receiptDate;

        private TotalPrice totalPriceTaxNormal;

        private TotalPrice totalPriceTaxErmaessigt1;

        private TotalPrice totalPriceTaxErmaessigt2;

        private TotalPrice totalPriceTaxNull;

        private TotalPrice totalPriceTaxBesonders;

        private EncryptedTurnoverValue encryptedTurnoverValue;

        private SignatureCertificateSerialNumber signatureCertificateSerialNumber;

        private SignatureValuePreviousReceipt signatureValuePreviousReceipt;

        public Builder addSuiteId(SuiteId suiteId) {
            this.suiteId = suiteId;
            return this;
        }

        public Builder addReceiptId(ReceiptId receiptId) {
            this.receiptId = receiptId;
            return this;
        }

        public Builder addCashBoxName(Name cashBoxName) {
            this.cashBoxName = cashBoxName;
            return this;
        }

        public Builder addReceiptDate(ReceiptDate receiptDate) {
            this.receiptDate = receiptDate;
            return this;
        }

        public Builder addTotalPriceTaxNormal(TotalPrice totalPriceTaxNormal) {
            this.totalPriceTaxNormal = totalPriceTaxNormal;
            return this;
        }

        public Builder addTotalPriceTaxErmaessigt1(TotalPrice totalPriceTaxErmaessigt1) {
            this.totalPriceTaxErmaessigt1 = totalPriceTaxErmaessigt1;
            return this;
        }

        public Builder addTotalPriceTaxErmaessigt2(TotalPrice totalPriceTaxErmaessigt2) {
            this.totalPriceTaxErmaessigt2 = totalPriceTaxErmaessigt2;
            return this;
        }

        public Builder addTotalPriceTaxNull(TotalPrice totalPriceTaxNull) {
            this.totalPriceTaxNull = totalPriceTaxNull;
            return this;
        }

        public Builder addTotalPriceTaxBesonders(TotalPrice totalPriceTaxBesonders) {
            this.totalPriceTaxBesonders = totalPriceTaxBesonders;
            return this;
        }

        public Builder addEncryptedTurnoverValue(EncryptedTurnoverValue encryptedTurnoverValue) {
            this.encryptedTurnoverValue = encryptedTurnoverValue;
            return this;
        }

        public Builder addSignatureCertificateSerialNumber(SignatureCertificateSerialNumber signatureCertificateSerialNumber) {
            this.signatureCertificateSerialNumber = signatureCertificateSerialNumber;
            return this;
        }

        public Builder addSignatureValuePreviousReceipt(SignatureValuePreviousReceipt signatureValuePreviousReceipt) {
            this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
            return this;
        }

        private <SVO extends SimpleValueObject, T extends Comparable> String getMandatoryAsString(SimpleValueObject<SVO, T> value) {
            if (value == null || value.get() == null) {
                throw new IllegalArgumentException("");
            }
            return value.asStringToBeSigned();
        }

        private <SVO extends SimpleValueObject, T extends Comparable> void appendWithPrefix(StringBuilder sb, SimpleValueObject<SVO, T> value) {
            sb.append("_");
            sb.append(getMandatoryAsString(value));
        }

        public JWSPayload build() {
            StringBuilder sb = new StringBuilder();
            appendWithPrefix(sb, suiteId);
            appendWithPrefix(sb, cashBoxName);
            appendWithPrefix(sb, receiptId);
            appendWithPrefix(sb, receiptDate);
            appendWithPrefix(sb, totalPriceTaxNormal);
            appendWithPrefix(sb, totalPriceTaxErmaessigt1);
            appendWithPrefix(sb, totalPriceTaxErmaessigt2);
            appendWithPrefix(sb, totalPriceTaxNull);
            appendWithPrefix(sb, totalPriceTaxBesonders);
            appendWithPrefix(sb, encryptedTurnoverValue);
            appendWithPrefix(sb, signatureCertificateSerialNumber);
            appendWithPrefix(sb, signatureValuePreviousReceipt);
            return new JWSPayload(sb.toString());
        }
    }
}

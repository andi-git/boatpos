package org.regkas.repository.api.builder;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.regkas.model.ReceiptEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.values.DEPString;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.ReceiptDate;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
import org.regkas.repository.api.values.SuiteId;
import org.regkas.repository.api.values.TotalPrice;

/**
 * Builder for {@link Receipt}.
 */
public interface ReceiptBuilder extends DomainModelBuilder<ReceiptBuilder, Receipt, ReceiptEntity> {

    ReceiptBuilder add(ReceiptId receiptId);

    ReceiptBuilder add(ReceiptDate receiptDate);

    ReceiptBuilder add(EncryptedTurnoverValue encryptedTurnoverValue);

    ReceiptBuilder add(SignatureValuePreviousReceipt signatureValuePreviousReceipt);

    ReceiptBuilder add(Company company);

    ReceiptBuilder add(User user);

    ReceiptBuilder add(ReceiptType receiptType);

    ReceiptBuilder add(CashBox cashBox);

    ReceiptBuilder add(PaymentMethod paymentMethod);

    ReceiptBuilder add(DEPString dep);

    ReceiptBuilder add(TotalPrice totalPrice);

    ReceiptBuilder add(SuiteId suiteId);

    ReceiptBuilder add(CompactJWSRepresentation compactJwsRepresentation);

    ReceiptBuilder add(ReceiptElement receiptElement);
}

package org.regkas.domain.api.builder;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.domain.api.builder.DomainModelBuilder;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptElement;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.signature.CompactJWSRepresentation;
import org.regkas.domain.api.values.DEPString;
import org.regkas.domain.api.values.EncryptedTurnoverValue;
import org.regkas.domain.api.values.ReceiptDate;
import org.regkas.domain.api.values.SignatureValuePreviousReceipt;
import org.regkas.domain.api.values.SuiteId;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.model.ReceiptEntity;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.values.ReceiptId;

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

package org.regkas.repository.api.builder;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.regkas.model.ReceiptEntity;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.values.*;

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

    ReceiptBuilder add(ReceiptElement receiptElement);
}

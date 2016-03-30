package org.regkas.service.core.util;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.values.ReceiptId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class ReceiptIdCalculator {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    @Current
    private CashBox cashBox;

    public ReceiptId getNextReceiptId() {
        Optional<Receipt> receipt = receiptRepository.loadLastReceipt(cashBox);
        int nextNumber = 1;
        if (receipt.isPresent() && receipt.get().getReceiptId().getYear() == dateTimeHelper.currentDate().getYear()) {
            nextNumber = receipt.get().getReceiptId().getNumber() + 1;
        }
        return new ReceiptId(dateTimeHelper.currentDate().getYear(), nextNumber);
    }
}

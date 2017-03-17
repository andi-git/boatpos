package org.regkas.service.core.receipt;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.values.ReceiptId;

@ApplicationScoped
public class ReceiptIdCalculatorCore implements ReceiptIdCalculator {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    @Current
    private CashBox cashBox;

    @Override
    public ReceiptId getNextReceiptId() {
        Optional<Receipt> receipt = receiptRepository.loadLastReceipt(cashBox);
        int nextNumber = 1;
        if (receipt.isPresent() && receipt.get().getReceiptId().getYear() == dateTimeHelper.currentDate().getYear()) {
            nextNumber = receipt.get().getReceiptId().getNumber() + 1;
        }
        return new ReceiptId(dateTimeHelper.currentDate().getYear(), nextNumber);
    }
}

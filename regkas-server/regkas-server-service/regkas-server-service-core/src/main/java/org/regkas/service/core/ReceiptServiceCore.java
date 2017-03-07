package org.regkas.service.core;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.service.api.ReceiptService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RequestScoped
public class ReceiptServiceCore implements ReceiptService {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    @Current
    private CashBox cashBox;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Override
    public Boolean isStartReceiptCreated() {
        return receiptRepository.loadLatestWithReceiptTypeStart(cashBox).isPresent();
    }

    @Override
    public Boolean shouldCreateMonthReceipt() {
        boolean shouldCreateMonthReceipt = false;
        Optional<Receipt> receipt = receiptRepository.loadLastReceipt(cashBox);
        if (receipt.isPresent() && diffGreaterEqualsOneMonth(dateTimeHelper.currentTime(), receipt.get())) {
            shouldCreateMonthReceipt = true;
        }
        return shouldCreateMonthReceipt;
    }

    @Override
    public String getCurrentRkOnlineEnvironment() {
        return rkOnlineContext.getEnvironment().getName();
    }

    @Override
    public void setRkOnlineEnvironment(String environment) {
        rkOnlineContext.setEnvironment(Environment.get(environment));
    }

    private boolean diffGreaterEqualsOneMonth(LocalDateTime currentDateTime, Receipt receipt) {
        return getFirstDayOfMonth(receipt.getReceiptDate().get()).until(getFirstDayOfMonth(currentDateTime), ChronoUnit.MONTHS) >= 1;
    }

    private LocalDate getFirstDayOfMonth(LocalDateTime dateTime) {
        return dateTime.toLocalDate().withDayOfMonth(1);
    }
}

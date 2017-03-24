package org.regkas.service.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptTypeMonat;
import org.regkas.domain.api.model.ReceiptTypeTag;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.service.api.ReceiptService;

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
        if (receipt.isPresent() &&
            receipt.get().getReceiptType().getClass() != ReceiptTypeMonat.class &&
            diffGreaterEqualsOneMonth(dateTimeHelper.currentTime(), receipt.get())) {
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

    @Override
    public boolean shouldCreateDayReceipt() {
        boolean shouldCreateDayReceipt = false;
        Optional<Receipt> receipt = receiptRepository.loadLastReceipt(cashBox);
        if (receipt.isPresent() &&
            receipt.get().getReceiptType().getClass() != ReceiptTypeTag.class &&
            isANewDay(dateTimeHelper.currentTime(), receipt.get())) {
            shouldCreateDayReceipt = true;
        }
        return shouldCreateDayReceipt;
    }

    private boolean isANewDay(LocalDateTime currentDateTime, Receipt lastReceipt) {
        return lastReceipt.getReceiptDate().get().until(currentDateTime, ChronoUnit.DAYS) >= 1;
    }

    private boolean diffGreaterEqualsOneMonth(LocalDateTime currentDateTime, Receipt lastReceipt) {
        return getFirstDayOfMonth(lastReceipt.getReceiptDate().get()).until(getFirstDayOfMonth(currentDateTime), ChronoUnit.MONTHS) >= 1;
    }

    private LocalDate getFirstDayOfMonth(LocalDateTime dateTime) {
        return dateTime.toLocalDate().withDayOfMonth(1);
    }
}

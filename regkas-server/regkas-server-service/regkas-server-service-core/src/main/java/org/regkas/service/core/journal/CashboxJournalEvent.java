package org.regkas.service.core.journal;

import java.time.LocalDateTime;
import java.util.Optional;

import org.regkas.repository.api.model.CashBox;

public class CashboxJournalEvent {

    private final String message;

    private final CashBox cashBox;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<LocalDateTime> dateTime;

    public CashboxJournalEvent(String message, CashBox cashBox) {
        this(message, cashBox, null);
    }

    public CashboxJournalEvent(String message, CashBox cashBox, LocalDateTime dateTime) {
        this.message = message;
        this.cashBox = cashBox;
        this.dateTime = Optional.ofNullable(dateTime);
    }

    public String getMessage() {
        return message;
    }

    public CashBox getCashBox() {
        return cashBox;
    }

    public Optional<LocalDateTime> getDateTime() {
        return dateTime;
    }
}

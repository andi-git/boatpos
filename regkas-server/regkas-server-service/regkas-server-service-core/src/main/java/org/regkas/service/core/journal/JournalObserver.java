package org.regkas.service.core.journal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.regkas.domain.api.builder.CashboxJournalBuilder;
import org.regkas.domain.api.builder.SystemJournalBuilder;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;

@ApplicationScoped
public class JournalObserver {

    @Inject
    private CashboxJournalBuilder cashboxJournalBuilder;

    @Inject
    private SystemJournalBuilder systemJournalBuilder;

    @Inject
    private DateTimeHelper dateTimeHelper;

    public void observeCashboxJournalEvent(@Observes CashboxJournalEvent cashboxJournalEvent) {
        cashboxJournalBuilder
            .reset()
            .add(new JournalMessage(cashboxJournalEvent.getMessage()))
            .add(
                new JournalDate(
                    cashboxJournalEvent.getDateTime().isPresent() ? cashboxJournalEvent.getDateTime().get() : dateTimeHelper.currentTime()))
            .add(cashboxJournalEvent.getCashBox())
            .build()
            .persist();
    }

    public void observeSystemJournalEvent(@Observes SystemJournalEvent systemJournalEvent) {
        systemJournalBuilder
            .reset()
            .add(new JournalMessage(systemJournalEvent.getMessage()))
            .add(new JournalDate(dateTimeHelper.currentTime()))
            .build()
            .persist();
    }
}

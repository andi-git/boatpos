package org.regkas.repository.core.builder;

import javax.enterprise.context.Dependent;

import org.boatpos.common.repository.core.builder.ModelBuilderCore;
import org.regkas.repository.api.builder.CashboxJournalBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.CashboxJournal;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;
import org.regkas.repository.core.model.CashboxJournalCore;

@Dependent
public class CashboxJournalBuilderCore extends ModelBuilderCore<CashboxJournal> implements CashboxJournalBuilder {

    private JournalMessage journalMessage;

    private JournalDate journalDate;

    private CashBox cashBox;

    @Override
    public CashboxJournal build() {
        return new CashboxJournalCore(journalDate, journalMessage, cashBox);
    }

    @Override
    public CashboxJournalBuilder add(JournalDate journalDate) {
        this.journalDate = journalDate;
        return this;
    }

    @Override
    public CashboxJournalBuilder add(CashBox cashBox) {
        this.cashBox = cashBox;
        return this;
    }

    @Override
    public CashboxJournalBuilder add(JournalMessage journalMessage) {
        this.journalMessage = journalMessage;
        return this;
    }
}

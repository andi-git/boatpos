package org.regkas.domain.core.builder;

import org.boatpos.common.domain.core.builder.ModelBuilderCore;
import org.regkas.domain.api.builder.CashboxJournalBuilder;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.CashboxJournal;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;
import org.regkas.domain.core.model.CashboxJournalCore;

import javax.enterprise.context.Dependent;

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

    @Override
    public CashboxJournalBuilder reset() {
        return new CashboxJournalBuilderCore();
    }
}

package org.regkas.domain.core.builder;

import org.boatpos.common.domain.core.builder.ModelBuilderCore;
import org.regkas.domain.api.builder.SystemJournalBuilder;
import org.regkas.domain.api.model.SystemJournal;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;
import org.regkas.domain.core.model.SystemJournalCore;

import javax.enterprise.context.Dependent;

@Dependent
public class SystemJournalBuilderCore extends ModelBuilderCore<SystemJournal> implements SystemJournalBuilder {

    private JournalMessage journalMessage;

    private JournalDate journalDate;

    @Override
    public SystemJournalCore build() {
        return new SystemJournalCore(journalDate, journalMessage);
    }

    @Override
    public SystemJournalBuilder add(JournalDate journalDate) {
        this.journalDate = journalDate;
        return this;
    }

    @Override
    public SystemJournalBuilder add(JournalMessage journalMessage) {
        this.journalMessage = journalMessage;
        return this;
    }

    @Override
    public SystemJournalBuilder reset() {
        return new SystemJournalBuilderCore();
    }
}

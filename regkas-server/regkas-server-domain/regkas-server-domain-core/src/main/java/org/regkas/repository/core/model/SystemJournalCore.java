package org.regkas.repository.core.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.core.model.ModelCore;
import org.regkas.model.CashboxJournalEntity;
import org.regkas.model.SystemJournalEntity;
import org.regkas.repository.api.model.SystemJournal;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;

public class SystemJournalCore extends ModelCore<SystemJournal, SystemJournalEntity> implements SystemJournal {

    public SystemJournalCore(JournalDate journalDate, JournalMessage journalMessage) {
        checkNotNull(journalDate, "'journalDate' must not be null");
        checkNotNull(journalMessage, "'journalMessage' must not be null");
        setJournalDate(journalDate);
        setJournalMessage(journalMessage);
    }

    public SystemJournalCore(SystemJournalEntity systemJournalEntity) {
        super(systemJournalEntity);
    }

    @Override
    public JournalDate getJournalDate() {
        return new JournalDate(getEntity().getMessageDate());
    }

    private void setJournalDate(JournalDate journalDate) {
        getEntity().setMessageDate(SimpleValueObject.nullSafe(journalDate));
    }

    @Override
    public JournalMessage getJournalMessage() {
        return new JournalMessage(getEntity().getMessage());
    }

    private void setJournalMessage(JournalMessage journalMessage) {
        getEntity().setMessage(SimpleValueObject.nullSafe(journalMessage));
    }
}

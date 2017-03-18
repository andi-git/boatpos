package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.ModelBuilder;
import org.regkas.repository.api.model.SystemJournal;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;

public interface SystemJournalBuilder extends ModelBuilder<SystemJournal> {

    SystemJournalBuilder add(JournalMessage journalMessage);

    SystemJournalBuilder add(JournalDate journalDate);
}

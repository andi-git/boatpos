package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.ModelBuilder;
import org.regkas.domain.api.model.SystemJournal;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;

public interface SystemJournalBuilder extends ModelBuilder<SystemJournal> {

    SystemJournalBuilder add(JournalMessage journalMessage);

    SystemJournalBuilder add(JournalDate journalDate);

    SystemJournalBuilder reset();
}

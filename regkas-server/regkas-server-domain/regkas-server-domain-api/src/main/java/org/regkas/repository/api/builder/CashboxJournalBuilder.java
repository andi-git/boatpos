package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.ModelBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.CashboxJournal;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;

public interface CashboxJournalBuilder extends ModelBuilder<CashboxJournal> {

    CashboxJournalBuilder add(JournalMessage journalMessage);

    CashboxJournalBuilder add(JournalDate journalDate);

    CashboxJournalBuilder add(CashBox cashBox);

    CashboxJournalBuilder reset();
}

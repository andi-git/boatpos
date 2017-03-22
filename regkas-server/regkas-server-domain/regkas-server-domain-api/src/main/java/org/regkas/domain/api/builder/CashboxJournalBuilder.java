package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.ModelBuilder;
import org.regkas.domain.api.model.CashboxJournal;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;
import org.regkas.domain.api.model.CashBox;

public interface CashboxJournalBuilder extends ModelBuilder<CashboxJournal> {

    CashboxJournalBuilder add(JournalMessage journalMessage);

    CashboxJournalBuilder add(JournalDate journalDate);

    CashboxJournalBuilder add(CashBox cashBox);

    CashboxJournalBuilder reset();
}

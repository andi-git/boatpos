package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.Model;
import org.regkas.model.CashboxJournalEntity;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;

public interface CashboxJournal extends Model<CashboxJournal, CashboxJournalEntity> {

    JournalMessage getJournalMessage();

    JournalDate getJournalDate();

    CashBox getCashBox();
}

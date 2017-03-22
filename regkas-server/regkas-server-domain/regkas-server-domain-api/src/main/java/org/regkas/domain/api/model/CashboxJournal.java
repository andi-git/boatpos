package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.Model;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;
import org.regkas.model.CashboxJournalEntity;

public interface CashboxJournal extends Model<CashboxJournal, CashboxJournalEntity> {

    JournalMessage getJournalMessage();

    JournalDate getJournalDate();

    CashBox getCashBox();
}

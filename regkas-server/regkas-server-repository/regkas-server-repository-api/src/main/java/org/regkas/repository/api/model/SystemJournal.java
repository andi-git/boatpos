package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.Model;
import org.regkas.model.SystemJournalEntity;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;

public interface SystemJournal extends Model<SystemJournal, SystemJournalEntity> {

    JournalMessage getJournalMessage();

    JournalDate getJournalDate();
}

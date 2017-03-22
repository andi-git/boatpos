package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.Model;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;
import org.regkas.model.SystemJournalEntity;

public interface SystemJournal extends Model<SystemJournal, SystemJournalEntity> {

    JournalMessage getJournalMessage();

    JournalDate getJournalDate();
}

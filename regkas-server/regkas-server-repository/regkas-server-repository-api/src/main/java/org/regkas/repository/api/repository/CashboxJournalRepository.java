package org.regkas.repository.api.repository;

import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.CashboxJournal;

import java.util.List;

public interface CashboxJournalRepository {

    List<CashboxJournal> loadAll();

    List<CashboxJournal> loadBy(CashBox cashBox);
}

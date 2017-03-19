package org.regkas.repository.api.repository;

import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.CashboxJournal;
import org.regkas.service.api.bean.Period;

import java.util.List;

public interface CashboxJournalRepository {

    List<CashboxJournal> loadAll();

    List<CashboxJournal> loadBy(CashBox cashBox);

    List<CashboxJournal> loadBy(CashBox cashBox, Period period);
}

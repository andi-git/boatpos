package org.regkas.repository.api.repository;

import java.util.List;

import org.regkas.repository.api.model.SystemJournal;
import org.regkas.service.api.bean.Period;

public interface SystemJournalRepository {

    List<SystemJournal> loadAll();

    List<SystemJournal> loadBy(Period period);
}

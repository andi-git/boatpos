package org.regkas.domain.api.repository;

import java.util.List;

import org.regkas.domain.api.model.SystemJournal;
import org.regkas.service.api.bean.Period;

public interface SystemJournalRepository {

    List<SystemJournal> loadAll();

    List<SystemJournal> loadBy(Period period);
}

package org.regkas.repository.api.repository;

import java.util.List;

import org.regkas.repository.api.model.SystemJournal;

public interface SystemJournalRepository {

    List<SystemJournal> loadAll();
}

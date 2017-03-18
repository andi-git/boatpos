package org.regkas.repository.core.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.boatpos.common.repository.core.JPAHelper;
import org.regkas.model.SystemJournalEntity;
import org.regkas.repository.api.model.SystemJournal;
import org.regkas.repository.api.repository.SystemJournalRepository;
import org.regkas.repository.core.model.SystemJournalCore;

@Dependent
public class SystemJournalRepositoryCore implements SystemJournalRepository {

    @Inject
    private JPAHelper jpaHelper;

    @Override
    public List<SystemJournal> loadAll() {
        return jpaHelper
            .createNamedQuery("systemjournal.getAll", SystemJournalEntity.class)
            .getResultList()
            .stream()
            .map(SystemJournalCore::new)
            .collect(Collectors.toList());
    }
}

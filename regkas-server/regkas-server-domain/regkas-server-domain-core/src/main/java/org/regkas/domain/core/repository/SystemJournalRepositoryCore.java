package org.regkas.domain.core.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.boatpos.common.domain.core.JPAHelper;
import org.regkas.model.SystemJournalEntity;
import org.regkas.domain.api.model.SystemJournal;
import org.regkas.domain.api.repository.SystemJournalRepository;
import org.regkas.domain.core.model.SystemJournalCore;
import org.regkas.service.api.bean.Period;

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

    @Override
    public List<SystemJournal> loadBy(Period period) {
        checkNotNull(period, "'period' must not be null");
        return jpaHelper
            .createNamedQuery("systemjournal.getAllWithinPeriod", SystemJournalEntity.class)
            .setParameter("start", period.getStartDay())
            .setParameter("end", period.getEndDay())
            .getResultList()
            .stream()
            .map(SystemJournalCore::new)
            .collect(Collectors.toList());
    }
}

package org.regkas.domain.core.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.boatpos.common.domain.core.JPAHelper;
import org.regkas.domain.core.model.CashboxJournalCore;
import org.regkas.model.CashboxJournalEntity;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.CashboxJournal;
import org.regkas.domain.api.repository.CashboxJournalRepository;
import org.regkas.service.api.bean.Period;

@Dependent
public class CashboxJournalRepositoryCore implements CashboxJournalRepository {

    @Inject
    private JPAHelper jpaHelper;

    @Override
    public List<CashboxJournal> loadAll() {
        return jpaHelper
            .createNamedQuery("cashboxjournal.getAll", CashboxJournalEntity.class)
            .getResultList()
            .stream()
            .map(CashboxJournalCore::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<CashboxJournal> loadBy(CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return jpaHelper
            .createNamedQuery("cashboxjournal.getAllByCashbox", CashboxJournalEntity.class)
            .setParameter("id", cashBox.getId().get())
            .getResultList()
            .stream()
            .map(CashboxJournalCore::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<CashboxJournal> loadBy(CashBox cashBox, Period period) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        checkNotNull(period, "'period' must not be null");
        return jpaHelper
            .createNamedQuery("cashboxjournal.getAllByCashboxWithinPeriod", CashboxJournalEntity.class)
            .setParameter("id", cashBox.getId().get())
            .setParameter("start", period.getStartDay())
            .setParameter("end", period.getEndDay())
            .getResultList()
            .stream()
            .map(CashboxJournalCore::new)
            .collect(Collectors.toList());
    }
}

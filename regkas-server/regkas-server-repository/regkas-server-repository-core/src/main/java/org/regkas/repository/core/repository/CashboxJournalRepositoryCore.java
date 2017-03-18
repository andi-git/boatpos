package org.regkas.repository.core.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.boatpos.common.repository.core.JPAHelper;
import org.regkas.model.CashboxJournalEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.CashboxJournal;
import org.regkas.repository.api.repository.CashboxJournalRepository;
import org.regkas.repository.core.model.CashboxJournalCore;

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
}

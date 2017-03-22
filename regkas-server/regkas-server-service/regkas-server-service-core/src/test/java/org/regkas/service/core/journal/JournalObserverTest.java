package org.regkas.service.core.journal;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.CashboxJournal;
import org.regkas.domain.api.model.SystemJournal;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.CashboxJournalRepository;
import org.regkas.domain.api.repository.SystemJournalRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class JournalObserverTest extends EntityManagerProviderForRegkas {

    @Inject
    private SystemJournalRepository systemJournalRepository;

    @Inject
    private CashboxJournalRepository cashboxJournalRepository;

    @Inject
    private Event<SystemJournalEvent> systemJournalEvent;

    @Inject
    private Event<CashboxJournalEvent> cashboxJournalEvent;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testObserveSystemJournal() {
        assertEquals(0, systemJournalRepository.loadAll().size());
        systemJournalEvent.fire(new SystemJournalEvent("test"));
        List<SystemJournal> systemJournals = systemJournalRepository.loadAll();
        assertEquals(1, systemJournals.size());
        assertEquals("test", systemJournals.get(0).getJournalMessage().get());
    }

    @Test
    @Transactional
    public void testObserveCashboxJournal() {
        CashBox regKas1 = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        CashBox regKas2 = cashBoxRepository.loadBy(new Name("RegKas2")).get();
        assertEquals(0, cashboxJournalRepository.loadAll().size());
        cashboxJournalEvent.fire(new CashboxJournalEvent("test", regKas1));
        List<CashboxJournal> cashboxJournals = cashboxJournalRepository.loadAll();
        assertEquals(1, cashboxJournals.size());
        assertEquals("test", cashboxJournals.get(0).getJournalMessage().get());
        assertEquals(1, cashboxJournalRepository.loadBy(regKas1).size());
        assertEquals(0, cashboxJournalRepository.loadBy(regKas2).size());
    }
}

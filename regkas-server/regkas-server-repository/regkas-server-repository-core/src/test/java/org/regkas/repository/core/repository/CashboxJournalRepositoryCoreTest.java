package org.regkas.repository.core.repository;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.builder.CashboxJournalBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CashboxJournalRepository;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.bean.Period;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class CashboxJournalRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private CashboxJournalRepository cashboxJournalRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashboxJournalBuilder cashboxJournalBuilder;

    @Test
    @Transactional
    public void testLoadAll() {
        assertEquals(0, cashboxJournalRepository.loadAll().size());
        CashBox regKas1 = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        CashBox regKas2 = cashBoxRepository.loadBy(new Name("RegKas2")).get();
        cashboxJournalBuilder.add(new JournalDate(2017, 3, 8, 21, 13, 30)).add(new JournalMessage("a message")).add(regKas1).build().persist();
        assertEquals(1, cashboxJournalRepository.loadAll().size());
        assertEquals(1, cashboxJournalRepository.loadBy(regKas1).size());
        assertEquals(0, cashboxJournalRepository.loadBy(regKas2).size());
    }

    @Test
    @Transactional
    public void testLoadAllByPeriod() {
        assertEquals(0, cashboxJournalRepository.loadAll().size());
        CashBox regKas1 = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        cashboxJournalBuilder.add(new JournalDate(2017, 3, 8, 21, 13, 30)).add(new JournalMessage("a message")).add(regKas1).build().persist();
        assertEquals(1, cashboxJournalRepository.loadAll().size());
        assertEquals(1, cashboxJournalRepository.loadBy(regKas1, Period.untilNow()).size());
        assertEquals(0, cashboxJournalRepository.loadBy(regKas1, Period.day(LocalDate.of(2014, 1, 1))).size());
    }
}

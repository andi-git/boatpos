package org.regkas.repository.core.repository;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.builder.SystemJournalBuilder;
import org.regkas.repository.api.repository.SystemJournalRepository;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;
import org.regkas.service.api.bean.Period;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class SystemJournalRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private SystemJournalRepository systemJournalRepository;

    @Inject
    private SystemJournalBuilder systemJournalBuilder;

    @Test
    @Transactional
    public void testLoadAll() {
        assertEquals(0, systemJournalRepository.loadAll().size());
        systemJournalBuilder.add(new JournalDate(2017, 3, 8, 21, 13, 30)).add(new JournalMessage("a message")).build().persist();
        assertEquals(1, systemJournalRepository.loadAll().size());
    }

    @Test
    @Transactional
    public void testLoadByPeriod() {
        assertEquals(0, systemJournalRepository.loadAll().size());
        systemJournalBuilder.add(new JournalDate(2017, 3, 8, 21, 13, 30)).add(new JournalMessage("a message")).build().persist();
        assertEquals(1, systemJournalRepository.loadBy(Period.untilNow()).size());
        assertEquals(0, systemJournalRepository.loadBy(Period.day(LocalDate.of(2014, 1, 1))).size());
    }
}

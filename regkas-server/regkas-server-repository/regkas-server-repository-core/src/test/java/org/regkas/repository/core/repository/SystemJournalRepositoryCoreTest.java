package org.regkas.repository.core.repository;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.builder.SystemJournalBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.SystemJournalRepository;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;
import org.regkas.repository.api.values.Name;
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
        systemJournalBuilder
                .add(new JournalDate(2017, 3, 8, 21, 13, 30))
                .add(new JournalMessage("a message"))
                .build()
                .persist();
        assertEquals(1, systemJournalRepository.loadAll().size());
    }
}

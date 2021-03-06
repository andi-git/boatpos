package org.regkas.domain.core.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.builder.SystemJournalBuilder;
import org.regkas.domain.api.model.SystemJournal;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;
import org.regkas.domain.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class SystemJournalCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private SystemJournalBuilder systemJournalBuilder;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    @Transactional
    public void testBuilder() {
        SystemJournal systemJournal = systemJournalBuilder.add(new JournalDate(2017, 3, 8, 21, 13, 30)).add(new JournalMessage("a message")).build();
        assertEquals(systemJournal.getJournalMessage().get(), "a message");
        assertEquals(systemJournal.getJournalDate().get(), LocalDateTime.of(2017, 3, 8, 21, 13, 30));
    }
}

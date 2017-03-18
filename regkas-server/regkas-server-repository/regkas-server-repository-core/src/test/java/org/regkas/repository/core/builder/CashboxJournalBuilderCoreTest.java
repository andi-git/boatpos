package org.regkas.repository.core.builder;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.builder.CashboxJournalBuilder;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class CashboxJournalBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private CashboxJournalBuilder cashboxJournalBuilder;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testBuilder() {
        assertEquals(
            cashboxJournalBuilder
                .add(new JournalDate(2017, 3, 8, 21, 13, 30))
                .add(new JournalMessage("a message"))
                .add(cashBoxRepository.loadBy(new Name("RegKas1")).get())
                .build()
                .getJournalMessage()
                .get(),
            "a message");
    }
}

package org.regkas.domain.core.builder;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.builder.SystemJournalBuilder;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;
import org.regkas.domain.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@RunWith(Arquillian.class)
public class SystemJournalBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private SystemJournalBuilder systemJournalBuilder;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    @Transactional
    public void testBuilder() {
        assertEquals(
            systemJournalBuilder.add(new JournalDate(2017, 3, 8, 21, 13, 30)).add(new JournalMessage("a message")).build().getJournalMessage().get(),
            "a message");
    }
}

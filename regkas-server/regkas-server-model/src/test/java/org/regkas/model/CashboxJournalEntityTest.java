package org.regkas.model;

import java.time.LocalDateTime;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CashboxJournalEntityTest extends JavaBeanTest<CashboxJournalEntity> {

    @Test
    public void testConstructor() {
        new CashboxJournalEntity("", LocalDateTime.now(), null);
    }
}

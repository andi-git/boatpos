package org.regkas.model;

import java.time.LocalDateTime;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SystemJournalEntityTest extends JavaBeanTest<SystemJournalEntity> {

    @Test
    public void testConstructor() {
        new SystemJournalEntity("", LocalDateTime.now());
    }
}

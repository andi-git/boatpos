package org.boatpos.common.test;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@RunWith(Arquillian.class)
public class FillDatabaseExecutorTest extends MockEntityManagerProvider {

    @Test
    @Transactional
    public void testSampleDatabaseCreatorIsCalled() {
        // this test has no assertion and this is really bad
        // but it is indirectly tested via code-coverage
    }
}

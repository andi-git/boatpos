package org.boatpos.common.test.rest;

import org.junit.After;
import org.junit.Before;

import javax.ejb.EJB;

public class FillDatabaseInOtherTransactionTest {

    @EJB
    private FillDatabaseInOtherTransaction fillDatabaseInOtherTransaction;

    @Before
    public void before() throws Exception {
        fillDatabaseInOtherTransaction.fillDatabase();
    }

    @After
    public void after() throws Exception {
        fillDatabaseInOtherTransaction.clearDatabase();
    }
}

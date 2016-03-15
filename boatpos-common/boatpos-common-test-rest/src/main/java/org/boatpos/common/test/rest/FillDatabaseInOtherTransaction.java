package org.boatpos.common.test.rest;

import org.boatpos.common.test.SampleDatabaseCreator;
import org.boatpos.common.util.qualifiers.Current;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FillDatabaseInOtherTransaction {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    @Current
    private EntityManager entityManager;

    @Inject
    private SampleDatabaseCreator sampleDatabaseCreator;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void fillDatabase() throws Exception {
        userTransaction.begin();
        sampleDatabaseCreator.fillDatabase(entityManager);
        userTransaction.commit();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void clearDatabase() throws Exception {
        userTransaction.begin();
        sampleDatabaseCreator.clearDatabase(entityManager);
        userTransaction.commit();
    }
}

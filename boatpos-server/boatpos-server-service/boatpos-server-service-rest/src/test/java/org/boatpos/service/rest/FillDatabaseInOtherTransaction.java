package org.boatpos.service.rest;

import org.boatpos.repository.api.BoatPosDB;
import org.boatpos.test.model.SampleDatabaseCreator;

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
    @BoatPosDB
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

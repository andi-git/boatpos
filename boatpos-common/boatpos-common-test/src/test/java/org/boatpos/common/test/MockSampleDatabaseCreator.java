package org.boatpos.common.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class MockSampleDatabaseCreator implements SampleDatabaseCreator {

    @Override
    public void fillDatabase(EntityManager entityManager) {
    }

    @Override
    public void clearDatabase(EntityManager entityManager) {
    }
}

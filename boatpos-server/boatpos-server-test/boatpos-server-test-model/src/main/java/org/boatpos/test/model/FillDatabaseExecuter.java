package org.boatpos.test.model;

import org.jboss.arquillian.container.spi.event.container.AfterDeploy;
import org.jboss.arquillian.container.spi.event.container.AfterUnDeploy;
import org.jboss.arquillian.container.spi.event.container.BeforeDeploy;
import org.jboss.arquillian.container.spi.event.container.BeforeUnDeploy;
import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.arquillian.junit.event.AfterRules;
import org.jboss.arquillian.junit.event.BeforeRules;
import org.jboss.arquillian.test.spi.event.enrichment.AfterEnrichment;
import org.jboss.arquillian.test.spi.event.enrichment.BeforeEnrichment;
import org.jboss.arquillian.test.spi.event.suite.*;
import org.jboss.arquillian.transaction.spi.event.AfterTransactionEnded;
import org.jboss.arquillian.transaction.spi.event.AfterTransactionStarted;
import org.jboss.arquillian.transaction.spi.event.BeforeTransactionEnded;
import org.jboss.arquillian.transaction.spi.event.BeforeTransactionStarted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.EntityManager;
import java.util.Optional;

public class FillDatabaseExecuter {

    private static final Logger log = LoggerFactory.getLogger(FillDatabaseExecuter.class);

    private Optional<EntityManagerProvider> entityManagerProvider = Optional.empty();

    public void beforeTransactionStarted(@Observes BeforeTransactionStarted event) {
        log.debug("observe event: " + event);
    }

    public void afterTransactionStarted(@Observes AfterTransactionStarted event) {
        log.debug("observe event: " + event);
    }

    public void beforeTransactionEnded(@Observes BeforeTransactionEnded event) {
        log.debug("observe event: " + event);
        if (entityManagerProvider.isPresent()) {
            log.info("clear database");
            getSampleDatabaseCreator().clearDatabase(getEntityManager());
        }
    }

    public void afterTransactionEnded(@Observes AfterTransactionEnded event) {
        log.debug("observe event: " + event);
    }

    @SuppressWarnings("unused")
    public void testEvent(@Observes Test event) {
        log.debug("observe event: " + event);
    }

    @SuppressWarnings("unused")
    public void beforeTest(@Observes Before event) {
        log.debug("observe event: " + event);
    }

    @SuppressWarnings("unused")
    public void beforeRules(@Observes BeforeRules event) {
        log.debug("observe event: " + event);
        if (event.getTestInstance() instanceof EntityManagerProvider) {
            entityManagerProvider = Optional.of((EntityManagerProvider) event.getTestInstance());
            log.debug("EntityManagerProvider set to " + entityManagerProvider.get());
        }
    }

    @SuppressWarnings("unused")
    public void afterRules(@Observes AfterRules event) {
        log.debug("observe event: " + event);
    }

    @SuppressWarnings("unused")
    public void beforeClass(@Observes BeforeClass event) {
    }

    @SuppressWarnings("unused")
    public void afterTest(@Observes After event) throws Exception {
    }

    @SuppressWarnings("unused")
    public void afterClass(@Observes AfterClass event) {
    }

    @SuppressWarnings("unused")
    public void beforeEnrichment(@Observes BeforeEnrichment event) {
    }

    @SuppressWarnings("unused")
    public void afterEnrichment(@Observes(precedence = 5) AfterEnrichment event) throws Exception {
        log.debug("observe event: " + event);
        if (entityManagerProvider.isPresent()) {
            log.info("insert data into database");
            getSampleDatabaseCreator().fillDatabase(getEntityManager());
        }
    }

    @SuppressWarnings("unused")
    public void beforeDeploy(@Observes BeforeDeploy event) {
    }

    @SuppressWarnings("unused")
    public void afterDeploy(@Observes AfterDeploy event) {
    }

    @SuppressWarnings("unused")
    public void beforeUnDeploy(@Observes BeforeUnDeploy event) {
    }

    @SuppressWarnings("unused")
    public void afterUnDeploy(@Observes AfterUnDeploy event) {
    }

    private SampleDatabaseCreator getSampleDatabaseCreator() {
        return CDI.current().select(SampleDatabaseCreator.class).get();
    }

    private EntityManager getEntityManager() {
        return entityManagerProvider.get().getEntityManager();
    }
}

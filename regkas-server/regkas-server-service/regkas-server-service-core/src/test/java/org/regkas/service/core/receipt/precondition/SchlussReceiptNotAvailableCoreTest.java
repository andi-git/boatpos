package org.regkas.service.core.receipt.precondition;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.receipt.precondition.SchlussReceiptNotAvailable;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@RunWith(Arquillian.class)
public class SchlussReceiptNotAvailableCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private SchlussReceiptNotAvailable schlussReceiptNotAvailable;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Before
    public void before() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
    }

    @After
    public void after() {
        cashBoxContext.clear();
    }

    @Test
    @Transactional
    public void isFulfilled() {
        assertTrue(schlussReceiptNotAvailable.isFulfilled(cashBoxContext.get()));
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void fulfill() {
        schlussReceiptNotAvailable.fulfill(null, null);
    }
}

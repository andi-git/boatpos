package org.regkas.domain.core.context;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class CashBoxContextCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private CashBoxContextCore cashBoxContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testCashBoxContext() throws Exception {
        assertNull(cashBoxContext.get());
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertNotNull(cashBoxContext.get());

        cashBoxContext.clear();
        assertNull(cashBoxContext.get());
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")).get());
        assertNotNull(cashBoxContext.get());
    }
}
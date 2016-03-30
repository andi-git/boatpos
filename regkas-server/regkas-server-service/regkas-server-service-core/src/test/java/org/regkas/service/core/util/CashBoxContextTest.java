package org.regkas.service.core.util;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.service.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class CashBoxContextTest extends EntityManagerProviderForRegkas {

    @Inject
    private CashBoxContext cashBoxContext;

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
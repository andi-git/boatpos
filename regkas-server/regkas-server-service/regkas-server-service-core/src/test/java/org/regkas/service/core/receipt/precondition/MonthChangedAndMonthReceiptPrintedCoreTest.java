package org.regkas.service.core.receipt.precondition;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.bean.BillBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@RunWith(Arquillian.class)
public class MonthChangedAndMonthReceiptPrintedCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private MonthChangedAndMonthReceiptPrintedCore monthChangedAndMonthReceiptPrintedCore;

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
    public void isFulfilled() throws Exception {
        assertFalse(monthChangedAndMonthReceiptPrintedCore.isFulfilled(cashBoxContext.get()));
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void fulfill() throws Exception {
        BillBean billBean = new BillBean();
        monthChangedAndMonthReceiptPrintedCore.fulfill(billBean, cashBoxContext.get());
        assertNotNull(billBean.getMonatsBeleg());
    }
}

package org.regkas.service.core;

import com.google.common.collect.Lists;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.*;
import org.regkas.service.core.context.CashBoxContext;
import org.regkas.service.core.context.CompanyContext;
import org.regkas.service.core.context.UserContext;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class JournalServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private JournalService journalService;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private DateTimeHelperMock dateTimeHelperMock;

    @Test
    @Transactional
    public void testIncome() throws Exception {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        IncomeBean income = journalService.totalIncomeFor(2015);
        assertEquals(7, income.getIncomeElements().size());
        assertEquals(new BigDecimal("11.00"), income.getIncomeElements().get(0).getIncome());
        assertEquals(10, income.getIncomeElements().get(0).getTaxPercent().intValue());

        income = journalService.totalIncomeFor(2015, 7);
        assertEquals(7, income.getIncomeElements().size());
        assertEquals(new BigDecimal("11.00"), income.getIncomeElements().get(0).getIncome());
        assertEquals(10, income.getIncomeElements().get(0).getTaxPercent().intValue());

        income = journalService.totalIncomeFor(2015, 7, 1);
        assertEquals(7, income.getIncomeElements().size());
        assertEquals(new BigDecimal("11.00"), income.getIncomeElements().get(0).getIncome());
        assertEquals(10, income.getIncomeElements().get(0).getTaxPercent().intValue());
        cashBoxContext.clear();
    }
}
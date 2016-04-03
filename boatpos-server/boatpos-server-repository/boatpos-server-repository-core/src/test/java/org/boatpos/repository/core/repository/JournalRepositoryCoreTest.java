package org.boatpos.repository.core.repository;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.repository.JournalRepository;
import org.boatpos.repository.api.values.IncomeResult;
import org.boatpos.repository.api.values.Period;
import org.boatpos.repository.core.DateTimeHelperMock;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class JournalRepositoryCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private JournalRepository journalRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private BoatRepository boatRepository;

    @Test
    @Transactional
    public void testIncomeBoatPeriod() {
        // day
        assertEquals(0, journalRepository.totalIncomeBeforeFor(Period.day(dateTimeHelper.currentDate()), PaymentMethod.Cash).size());

        List<IncomeResult> incomeResultsBeforeCash = journalRepository.totalIncomeBeforeFor(Period.day(dateTimeHelper.currentDate()), PaymentMethod.Card);
        assertEquals("E-Boot", incomeResultsBeforeCash.get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), incomeResultsBeforeCash.get(0).getPricePaid());

        List<IncomeResult> incomeResultsAfterCash = journalRepository.totalIncomeAfterFor(Period.day(dateTimeHelper.currentDate()), PaymentMethod.Cash);
        assertEquals("E-Boot", incomeResultsAfterCash.get(0).getBoatName());
        assertEquals(new BigDecimal("1.60"), incomeResultsAfterCash.get(0).getPricePaid());
        assertEquals("Tretboot groß", incomeResultsAfterCash.get(1).getBoatName());
        assertEquals(new BigDecimal("12.60"), incomeResultsAfterCash.get(1).getPricePaid());

        assertEquals(0, journalRepository.totalIncomeAfterFor(Period.day(dateTimeHelper.currentDate()), PaymentMethod.Card).size());

        // year
        assertEquals(0, journalRepository.totalIncomeBeforeFor(Period.year(dateTimeHelper.currentDate()), PaymentMethod.Cash).size());

        incomeResultsBeforeCash = journalRepository.totalIncomeBeforeFor(Period.year(dateTimeHelper.currentDate()), PaymentMethod.Card);
        assertEquals("E-Boot", incomeResultsBeforeCash.get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), incomeResultsBeforeCash.get(0).getPricePaid());

        incomeResultsAfterCash = journalRepository.totalIncomeAfterFor(Period.year(dateTimeHelper.currentDate()), PaymentMethod.Cash);
        assertEquals("E-Boot", incomeResultsAfterCash.get(0).getBoatName());
        assertEquals(new BigDecimal("81.60"), incomeResultsAfterCash.get(0).getPricePaid());
        assertEquals("Tretboot groß", incomeResultsAfterCash.get(1).getBoatName());
        assertEquals(new BigDecimal("12.60"), incomeResultsAfterCash.get(1).getPricePaid());

        assertEquals(0, journalRepository.totalIncomeAfterFor(Period.year(dateTimeHelper.currentDate()), PaymentMethod.Card).size());
    }
}

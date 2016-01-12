package org.boatpos.repository.core.repository;

import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.repository.JournalRepository;
import org.boatpos.repository.api.values.Period;
import org.boatpos.repository.api.values.SumPaid;
import org.boatpos.repository.core.DateTimeHelperMock;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
    public void testSumBoatPeriod() {
        Map<String, SumPaid> result = new HashMap<>();
        for (Boat boat : boatRepository.loadAll()) {
            result.put(boat.getShortName().get(), journalRepository.sum(boat, Period.day(dateTimeHelper.currentDate())));
        }
        assertEquals(new BigDecimal("33.60"), result.get("E").get());
        assertEquals(new BigDecimal("0.00"), result.get("T2").get());
        assertEquals(new BigDecimal("12.60"), result.get("T4").get());

        for (Boat boat : boatRepository.loadAll()) {
            result.put(boat.getShortName().get(), journalRepository.sum(boat, Period.month(dateTimeHelper.currentDate())));
        }
        assertEquals(new BigDecimal("73.60"), result.get("E").get());
        assertEquals(new BigDecimal("0.00"), result.get("T2").get());
        assertEquals(new BigDecimal("12.60"), result.get("T4").get());

        for (Boat boat : boatRepository.loadAll()) {
            result.put(boat.getShortName().get(), journalRepository.sum(boat, Period.year(dateTimeHelper.currentDate())));
        }
        assertEquals(new BigDecimal("113.60"), result.get("E").get());
        assertEquals(new BigDecimal("0.00"), result.get("T2").get());
        assertEquals(new BigDecimal("12.60"), result.get("T4").get());
    }
}

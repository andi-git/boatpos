package org.boatpos.service.core;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.model.PaymentMethod;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.repository.JournalRepository;
import org.boatpos.repository.api.values.BoatCountResult;
import org.boatpos.repository.api.values.IncomeResult;
import org.boatpos.repository.api.values.Period;
import org.boatpos.service.api.JournalService;
import org.boatpos.service.api.bean.JournalReportBean;
import org.boatpos.service.api.bean.JournalReportItemBean;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@RequestScoped
public class JournalServiceCore implements JournalService {

    @Inject
    private JournalRepository journalRepository;

    @Inject
    private BoatRepository boatRepository;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public JournalReportBean totalIncomeFor(Integer year) {
        return totalIncomeFor(Period.year(LocalDate.of(year, 1, 1)));
    }

    @Override
    public JournalReportBean totalIncomeFor(Integer year, Integer month) {
        return totalIncomeFor(Period.month(LocalDate.of(year, month, 1)));
    }

    @Override
    public JournalReportBean totalIncomeFor(Integer year, Integer month, Integer dayOfMonth) {
        return totalIncomeFor(Period.day(LocalDate.of(year, month, dayOfMonth)));
    }

    private JournalReportBean totalIncomeFor(Period period) {
        checkNotNull(period, "'period' must not be null");
        log.info("calculate total income for {} - {}", period.getStart(), period.getEnd());
        JournalReportBean journalReportBean = new JournalReportBean();
        journalReportBean.setStart(period.getStart());
        journalReportBean.setEnd(period.getEnd());
        List<BoatCountResult> boatCountResult = journalRepository.countBoatFor(period);
        for (Boat boat : boatRepository.loadAll(Enabled.TRUE)) {
            journalReportBean.addJournalReportItemBean(new JournalReportItemBean(
                    boat.getName().get(),
                    getIncomeResultFor(boat, journalRepository.totalIncomeBeforeFor(period, PaymentMethod.CASH)),
                    getIncomeResultFor(boat, journalRepository.totalIncomeBeforeFor(period, PaymentMethod.CARD)),
                    getIncomeResultFor(boat, journalRepository.totalIncomeAfterFor(period, PaymentMethod.CASH)),
                    getIncomeResultFor(boat, journalRepository.totalIncomeAfterFor(period, PaymentMethod.CARD)),
                    getBoatCountFor(boat, boatCountResult).intValue()));
        }
        return journalReportBean;
    }

    private BigDecimal getIncomeResultFor(Boat boat, List<IncomeResult> incomeResult) {
        checkNotNull(boat, "'boat' must not be null");
        checkNotNull(incomeResult, "'incomeCountResult' must not be null");
        return incomeResult.stream().filter((ir) -> boat.getName().get().equals(ir.getBoatName())).findFirst().orElse(new IncomeResult("", BigDecimal.ZERO)).getPricePaid();
    }

    private Long getBoatCountFor(Boat boat, List<BoatCountResult> boatCountResult) {
        checkNotNull(boat, "'boat' must not be null");
        checkNotNull(boatCountResult, "'boatCountResult' must not be null");
        return boatCountResult.stream().filter(bc -> bc.getBoatName().equals(boat.getName().get())).findFirst().orElse(new BoatCountResult("", 0L)).getCount();
    }
}

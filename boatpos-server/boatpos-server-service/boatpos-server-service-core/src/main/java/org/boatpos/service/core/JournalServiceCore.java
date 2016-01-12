package org.boatpos.service.core;

import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.repository.JournalRepository;
import org.boatpos.repository.api.values.Period;
import org.boatpos.service.api.JournalService;
import org.boatpos.service.api.bean.JournalReportBean;
import org.boatpos.service.api.bean.JournalReportItemBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;

@RequestScoped
public class JournalServiceCore implements JournalService {

    @Inject
    private JournalRepository journalRepository;

    @Inject
    private BoatRepository boatRepository;

    @Override
    public JournalReportBean totalIncomeForYear(Integer year) {
        return totalIncomeFor(Period.year(LocalDate.of(year, 1, 1)));
    }

    @Override
    public JournalReportBean totalIncomeForMonth(Integer year, Integer month) {
        return totalIncomeFor(Period.month(LocalDate.of(year, month, 1)));
    }

    @Override
    public JournalReportBean totalIncomeForDay(Integer year, Integer month, Integer dayOfMonth) {
        return totalIncomeFor(Period.day(LocalDate.of(year, month, dayOfMonth)));
    }

    private JournalReportBean totalIncomeFor(Period period) {
        JournalReportBean journalReportBean = new JournalReportBean();
        for (Boat boat : boatRepository.loadAll()) {
            journalReportBean.addJournalReportItemBean(new JournalReportItemBean(boat.asDto(), journalRepository.sum(boat, period).get()));
        }
        return journalReportBean;
    }
}

package org.boatpos.service.core;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.repository.BoatRepository;
import org.boatpos.domain.api.repository.JournalRepository;
import org.boatpos.domain.api.values.BoatCountResult;
import org.boatpos.domain.api.values.IncomeResult;
import org.boatpos.domain.api.values.Period;
import org.boatpos.domain.api.values.ReceiptId;
import org.boatpos.service.api.JournalService;
import org.boatpos.service.api.bean.JournalReportBean;
import org.boatpos.service.api.bean.JournalReportItemBean;
import org.boatpos.service.core.mail.SendMailEvent;
import org.boatpos.service.core.util.RegkasService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.IncomeBean;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.File;
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

    @Inject
    private RegkasService regkasService;

    @Inject
    private Event<SendMailEvent> sendMailEvent;

    @Override
    public JournalReportBean totalIncomeFor(Integer year) {
        Period period = Period.year(LocalDate.of(year, 1, 1));
        JournalReportBean journalReportBean = totalIncomeFor(period);
        IncomeBean incomeBean = regkasService.totalIncome(year);
        checkTotalIncomeBetweenBoatposAndRegkas(journalReportBean, incomeBean, period);
        return journalReportBean;
    }

    @Override
    public JournalReportBean totalIncomeFor(Integer year, Integer month) {
        Period period = Period.month(LocalDate.of(year, month, 1));
        JournalReportBean journalReportBean = totalIncomeFor(period);
        IncomeBean incomeBean = regkasService.totalIncome(year, month);
        checkTotalIncomeBetweenBoatposAndRegkas(journalReportBean, incomeBean, period);
        return journalReportBean;
    }

    @Override
    public JournalReportBean totalIncomeFor(Integer year, Integer month, Integer dayOfMonth) {
        Period period = Period.day(LocalDate.of(year, month, dayOfMonth));
        JournalReportBean journalReportBean = totalIncomeFor(period);
        IncomeBean incomeBean = regkasService.totalIncome(year, month, dayOfMonth);
        checkTotalIncomeBetweenBoatposAndRegkas(journalReportBean, incomeBean, period);
        return journalReportBean;
    }

    private void checkTotalIncomeBetweenBoatposAndRegkas(JournalReportBean incomeBoatpos, IncomeBean incomeRegkas, Period period) {
        BigDecimal incomeBoatposSum = incomeBoatpos.getJournalReportItemBeans().stream()
                .map(item -> item.getPricePaidBeforeCash()
                        .add(item.getPricePaidAfterCash())
                        .add(item.getPricePaidBeforeCard())
                        .add(item.getPricePaidAfterCard()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (incomeBoatposSum == null) {
            incomeBoatposSum = BigDecimal.ZERO;
        }
        BigDecimal incomeRegkasSum = BigDecimal.ZERO;
        if (incomeRegkas != null && incomeRegkas.getTotalIncome() != null) {
            incomeRegkasSum = incomeRegkas.getTotalIncome();
        }
        if (incomeBoatposSum.compareTo(incomeRegkasSum) != 0) {
            String message = "Difference between boatpos-income " + incomeBoatposSum + " and regkas-income " + incomeRegkasSum + " for " + period + "!";
            log.error(message);
            sendMailEvent.fire(new SendMailEvent("different income", message));
        }
    }

    @Override
    public File datenErfassungsProtokoll(Integer year) {
        return regkasService.getDEP(year);
    }

    @Override
    public File datenErfassungsProtokoll(Integer year, Integer month) {
        return regkasService.getDEP(year, month);
    }

    @Override
    public File datenErfassungsProtokoll(Integer year, Integer month, Integer dayOfMonth) {
        return regkasService.getDEP(year, month, dayOfMonth);
    }

    @Override
    public File latestDatenErfassungsProtokollRKV2012() {
        return regkasService.getLatestDEPRKSV2012();
    }

    @Override
    public File datenErfassungsProtokollRKSV() {
        return regkasService.getDEPRKSV();
    }

    @Override
    public File latestDatenErfassungsProtokollRKSV() {
        return regkasService.getLatestDEPRKSV();
    }

    @Override
    public BillBean getReceiptById(String receiptId) {
        return regkasService.receiptById(new ReceiptId(receiptId));
    }

    private JournalReportBean totalIncomeFor(Period period) {
        checkNotNull(period, "'period' must not be null");
        log.info("calculate total income for {} - {}", period.getStart(), period.getEnd());
        JournalReportBean journalReportBean = new JournalReportBean();
        journalReportBean.setStart(period.getStart());
        journalReportBean.setEnd(period.getEnd());
        List<BoatCountResult> boatCountResult = journalRepository.countBoatFor(period);
        for (Boat boat : boatRepository.loadAll(Enabled.TRUE)) {
            BigDecimal incomeCashBefore = getIncomeResultFor(boat, journalRepository.totalIncomeBeforeFor(period, PaymentMethod.CASH));
            BigDecimal incomeCardBefore = getIncomeResultFor(boat, journalRepository.totalIncomeBeforeFor(period, PaymentMethod.CARD));
            BigDecimal incomeCashAfter = getIncomeResultFor(boat, journalRepository.totalIncomeAfterFor(period, PaymentMethod.CASH));
            BigDecimal incomeCardAfter = getIncomeResultFor(boat, journalRepository.totalIncomeAfterFor(period, PaymentMethod.CARD));
            journalReportBean.addJournalReportItemBean(new JournalReportItemBean(
                    boat.getName().get(),
                    incomeCashBefore,
                    getBeforeTax(incomeCashBefore),
                    getTax(incomeCashBefore),
                    incomeCardBefore,
                    getBeforeTax(incomeCardBefore),
                    getTax(incomeCardBefore),
                    incomeCashAfter,
                    getBeforeTax(incomeCashAfter),
                    getTax(incomeCashAfter),
                    incomeCardAfter,
                    getBeforeTax(incomeCardAfter),
                    getTax(incomeCardAfter),
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

    private BigDecimal getTax(BigDecimal price) {
        return price.subtract(getBeforeTax(price));
    }

    private BigDecimal getBeforeTax(BigDecimal price) {
        return price.divide(new BigDecimal("1.20"), 2, BigDecimal.ROUND_HALF_UP);
    }

}

package org.boatpos.service.api;

import org.boatpos.service.api.bean.JournalReportBean;

import java.math.BigDecimal;

/**
 * Service for journals and / or reports.
 */
public interface JournalService {

    /**
     * Get the total income for a year.
     *
     * @param year the year
     * @return the total income for a year
     */
    JournalReportBean totalIncomeFor(Integer year);

    /**
     * Get the total income for a month.
     *
     * @param year  the year
     * @param month the month (1-based: 1...12)
     * @return the total income for a month
     */
    JournalReportBean totalIncomeFor(Integer year, Integer month);

    /**
     * Get the total income for a day.
     *
     * @param year       the year
     * @param month      the month (1-based: 1...12)
     * @param dayOfMonth the day-of-month (1-based: 1...28/31)
     * @return the total income for a day
     */
    JournalReportBean totalIncomeFor(Integer year, Integer month, Integer dayOfMonth);
}

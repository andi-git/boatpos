package org.regkas.service.api;

import org.regkas.service.api.bean.IncomeBean;

import java.io.File;

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
    IncomeBean totalIncomeFor(Integer year);

    /**
     * Get the total income for a month.
     *
     * @param year  the year
     * @param month the month (1-based: 1...12)
     * @return the total income for a month
     */
    IncomeBean totalIncomeFor(Integer year, Integer month);

    /**
     * Get the total income for a day.
     *
     * @param year       the year
     * @param month      the month (1-based: 1...12)
     * @param dayOfMonth the day-of-month (1-based: 1...28/31)
     * @return the total income for a day
     */
    IncomeBean totalIncomeFor(Integer year, Integer month, Integer dayOfMonth);

    /**
     * Get the data-export. The returned {@link File} is zipped and contains the data.
     *
     * @param year the year
     * @return the zipped {@link File} of the data-export
     */
    File datenErfassungsProtokoll(Integer year);

    /**
     * Get the data-export. The returned {@link File} is zipped and contains the data.
     *
     * @param year  the year
     * @param month the month (1-based: 1...12)
     * @return the zipped {@link File} of the data-export
     */
    File datenErfassungsProtokoll(Integer year, Integer month);

    /**
     * Get the data-export. The returned {@link File} is zipped and contains the data.
     *
     * @param year       the year
     * @param month      the month (1-based: 1...12)
     * @param dayOfMonth the day-of-month (1-based: 1...28/31)
     * @return the zipped {@link File} of the data-export
     */
    File datenErfassungsProtokoll(Integer year, Integer month, Integer dayOfMonth);
}

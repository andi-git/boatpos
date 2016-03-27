package org.regkas.service.api.bean;

import org.junit.Test;

import java.time.LocalDate;

public class PeriodTest {

    @Test
    public void test() {
        Period period = new Period(LocalDate.now(), LocalDate.now());
        period.getStart();
        period.getEnd();
        Period.day(LocalDate.now());
        Period.month(LocalDate.now());
        Period.year(LocalDate.now());
    }
}
package org.regkas.service.api.bean;

import org.junit.Test;

import java.time.LocalDateTime;

public class PeriodTest {

    @Test
    public void test() {
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now());
        period.getStartDay();
        period.getEndDay();
        Period.day(LocalDateTime.now());
        Period.month(LocalDateTime.now());
        Period.year(LocalDateTime.now());
    }
}
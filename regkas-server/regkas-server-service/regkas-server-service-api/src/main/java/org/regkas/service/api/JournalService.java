package org.regkas.service.api;

import org.regkas.service.api.bean.IncomeBean;

import java.io.File;

public interface JournalService {

    IncomeBean totalIncomeFor(Integer year);

    IncomeBean totalIncomeFor(Integer year, Integer month);

    IncomeBean totalIncomeFor(Integer year, Integer month, Integer dayOfMonth);

    File datenErfassungsProtokollRKV2012(Integer year);

    File datenErfassungsProtokollRKV2012(Integer year, Integer month);

    File datenErfassungsProtokollRKV2012(Integer year, Integer month, Integer dayOfMonth);

    File datenErfassungsProtokollRKSV();

    File latestDatenErfassungsProtokollRKSV();
}

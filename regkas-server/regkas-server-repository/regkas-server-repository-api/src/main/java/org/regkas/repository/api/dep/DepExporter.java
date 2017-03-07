package org.regkas.repository.api.dep;

import java.io.File;

import org.regkas.service.api.bean.Period;

public interface DepExporter {

    File exportBasedOnRKV2012(Period period);

    File exportBasedOnRKSV(Period period);
}

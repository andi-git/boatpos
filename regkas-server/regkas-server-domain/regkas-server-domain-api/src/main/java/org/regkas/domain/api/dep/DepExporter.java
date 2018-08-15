package org.regkas.domain.api.dep;

import java.io.File;
import java.util.Optional;

import org.regkas.service.api.bean.Period;

public interface DepExporter {

    File exportBasedOnRKV2012(Period period);

    Optional<File> getLatestExportBasedOnRKV2012(File folder);

    File exportBasedOnRKSV(Period period);

    Optional<File> getLatestExportBasedOnRKSV(File folder);
}

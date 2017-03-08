package org.regkas.service.core.dep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.context.CompanyContext;
import org.regkas.repository.api.dep.DepExporter;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.service.api.bean.Period;

@Singleton
@Startup
public class DepExporterTimer {

    @Inject
    private DepExporter depExporter;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private CompanyContext companyContext;

    @Schedule(hour = "1", minute = "30", persistent = false)
    public List<File> exportDep() {
        List<File> deps = new ArrayList<>();
        for (CashBox cashBox : cashBoxRepository.loadAll()) {
            cashBoxContext.set(cashBox);
            companyContext.set(cashBox.getCompany());
            String folder = System.getProperty("boatpos.data.folder", System.getProperty("java.io.tmpdir")) + "/dep/rksv/";
            File file = depExporter.exportBasedOnRKSV(Period.untilNow());
            try {
                File destFile = new File(folder, file.getName());
                log.debug("create DEP-RKSV for " + cashBox.getName().get() + " in " + destFile);
                FileUtils.copyFile(file, destFile);
                deps.add(destFile);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return deps;
    }
}

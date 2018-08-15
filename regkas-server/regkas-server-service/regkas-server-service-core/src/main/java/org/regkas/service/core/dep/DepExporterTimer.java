package org.regkas.service.core.dep;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.jboss.ejb3.annotation.TransactionTimeout;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.context.CompanyContext;
import org.regkas.domain.api.dep.DepExporter;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;
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

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Schedule(hour = "1", minute = "30", persistent = false)
    @TransactionTimeout(value = 30, unit = TimeUnit.MINUTES)
    public List<File> exportDep() {
        List<File> deps = new ArrayList<>();
        for (CashBox cashBox : cashBoxRepository.loadAll()) {
            cashBoxContext.set(cashBox);
            companyContext.set(cashBox.getCompany());
            String folder = System.getProperty("boatpos.data.folder", System.getProperty("java.io.tmpdir") + "/dep/rksv/");
            DepType.RSV2012.export(depExporter, dateTimeHelper, log, folder, deps, cashBox);
            DepType.RKSV2017.export(depExporter, dateTimeHelper, log, folder, deps, cashBox);
        }
        return deps;
    }

    private enum DepType {
        RSV2012{
            @Override
            public File doExport(DepExporter depExporter, DateTimeHelper dateTimeHelper) {
                return depExporter.exportBasedOnRKV2012(getPeriod(dateTimeHelper));
            }
        },
        RKSV2017 {
            @Override
            public File doExport(DepExporter depExporter, DateTimeHelper dateTimeHelper) {
                return depExporter.exportBasedOnRKSV(getPeriod(dateTimeHelper));
            }
        };

        public abstract File doExport(DepExporter depExporter, DateTimeHelper dateTimeHelper);

        public void export(DepExporter depExporter, DateTimeHelper dateTimeHelper, LogWrapper log, String folder, List<File> deps, CashBox cashBox) {
            File file = doExport(depExporter, dateTimeHelper);
            try {
                File destFile = new File(folder, file.getName());
                log.debug("create DEP-" + name() + " for " + cashBox.getName().get() + " in " + destFile);
                FileUtils.copyFile(file, destFile);
                deps.add(destFile);
            } catch (IOException e) {
                log.error(e);
            }
        }

        private static Period getPeriod(DateTimeHelper dateTimeHelper) {
            return new Period(LocalDateTime.of(2017, 1, 1, 0, 0, 0), dateTimeHelper.currentTime());
        }
    }
}

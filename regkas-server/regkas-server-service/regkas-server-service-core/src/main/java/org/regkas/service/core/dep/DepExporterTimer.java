package org.regkas.service.core.dep;

import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
@Startup
public class DepExporterTimer {

    @Inject
    private DepFullExportCore depFullExport;

    @Schedule(hour = "1", minute = "30", persistent = false)
    @TransactionTimeout(value = 2, unit = TimeUnit.HOURS)
    public List<File> exportDep() {
        return depFullExport.exportDep();
    }
}

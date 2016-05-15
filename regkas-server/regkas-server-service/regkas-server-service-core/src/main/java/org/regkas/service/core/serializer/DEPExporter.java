package org.regkas.service.core.serializer;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.service.api.bean.Period;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Component to export the DatenErfassungsProtokoll.
 */
@RequestScoped
public class DEPExporter {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    @Current
    private CashBox cashBox;

    @Inject
    @Current
    private Company company;

    @Inject
    private DateTimeHelper dateTimeHelper;

    public File export(Period period) {
        String fileName = "dep_" + company.getName().get().replaceAll(" ", "") + "_" + cashBox.getName().get() + "_" + period.getStartDay().format(DateTimeFormatter.ISO_DATE) + "_" + period.getEndDay().format(DateTimeFormatter.ISO_DATE);
        File zipFile = new File(System.getProperty("java.io.tmpdir"), fileName + ".zip");
        log.info("create DEP: " + zipFile);

        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
            zos.putNextEntry(new ZipEntry(fileName + ".json"));

            writeLine(zos, "{");
            writeLine(zos, "  \"company\": \"" + company.getName().get() + "\",");
            writeLine(zos, "  \"atu\": \"" + company.getATU().get() + "\",");
            writeLine(zos, "  \"cashBoxId\": \"" + cashBox.getName().get() + "\",");
            writeLine(zos, "  \"created\": \"" + dateTimeHelper.currentTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\",");
            writeLine(zos, "  \"from\": \"" + period.getStartDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\",");
            writeLine(zos, "  \"to\": \"" + period.getEndDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\",");
            writeLine(zos, "  \"cashBoxInstructionList\": [");

            LocalDate currentDay = period.getStartDay().toLocalDate();
            while (currentDay.isBefore(period.getEndDay().toLocalDate()) || currentDay.isEqual(period.getEndDay().toLocalDate())) {
                List<String> deps = receiptRepository.loadDEPFor(Period.day(currentDay), cashBox);
                for (int i = 0; i < deps.size(); i++) {
                    writeLine(zos, "    " + deps.get(i) + (i < (deps.size() - 1) ? "," : ""));
                }
                currentDay = currentDay.plus(1, ChronoUnit.DAYS);
            }

            writeLine(zos, "  ]");
            writeLine(zos, "}");
            zos.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    //noinspection ThrowFromFinallyBlock
                    throw new RuntimeException(e);
                }
            }
        }
        return zipFile;
    }

    private void writeLine(ZipOutputStream zos, String string) throws IOException {
        zos.write((string + "\n").getBytes());
    }
}

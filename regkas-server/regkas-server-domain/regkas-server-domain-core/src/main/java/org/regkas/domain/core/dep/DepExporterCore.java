package org.regkas.domain.core.dep;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.context.CompanyContext;
import org.regkas.domain.api.dep.DepExporter;
import org.regkas.domain.api.model.CashboxJournal;
import org.regkas.domain.api.model.SystemJournal;
import org.regkas.domain.api.repository.CashboxJournalRepository;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.repository.SystemJournalRepository;
import org.regkas.domain.api.signature.RkOnlineResourceCertificate;
import org.regkas.domain.api.values.Certificate;
import org.regkas.domain.api.values.JournalDate;
import org.regkas.domain.api.values.JournalMessage;
import org.regkas.service.api.bean.Period;

@Dependent
public class DepExporterCore implements DepExporter {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private RkOnlineResourceCertificate rkOnlineResourceCertificate;

    // use the context because of the call from the timer-ejb
    @Inject
    private CashBoxContext cashBoxContext;

    // use the context because of the call from the timer-ejb
    @Inject
    private CompanyContext companyContext;

    @Inject
    private CashboxJournalRepository cashboxJournalRepository;

    @Inject
    private SystemJournalRepository systemJournalRepository;

    @Override
    public File exportBasedOnRKV2012(Period period) {
        String fileName = createFileName("depRKV2012", period);
        File zipFile = createZipFile(fileName);
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
            zos.putNextEntry(new ZipEntry(fileName + ".json"));

            writeLine(zos, "{");
            writeLine(zos, "  \"company\": \"" + companyContext.get().getName().get() + "\",");
            writeLine(zos, "  \"atu\": \"" + companyContext.get().getATU().get() + "\",");
            writeLine(zos, "  \"cashBoxId\": \"" + cashBoxContext.get().getName().get() + "\",");
            writeLine(zos, "  \"created\": \"" + dateTimeHelper.currentTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\",");
            writeLine(zos, "  \"from\": \"" + period.getStartDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\",");
            writeLine(zos, "  \"to\": \"" + period.getEndDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\",");

            writeLine(zos, "  \"cashBoxInstructionList\": [");
            LocalDate currentDay = period.getStartDay().toLocalDate();
            while (currentDay.isBefore(period.getEndDay().toLocalDate()) || currentDay.isEqual(period.getEndDay().toLocalDate())) {
                List<String> deps = receiptRepository.loadDEPFor(Period.day(currentDay), cashBoxContext.get());
                for (int i = 0; i < deps.size(); i++ ) {
                    writeLine(zos, "    " + deps.get(i) + (hasNextElement(deps, i) ? "," : ""));
                }
                currentDay = currentDay.plus(1, ChronoUnit.DAYS);
            }
            writeLine(zos, "  ],");

            writeLine(zos, "  \"cashboxEvents\": [");
            List<CashboxJournal> cashboxJournals = cashboxJournalRepository.loadBy(cashBoxContext.get(), period);
            for (int i = 0; i < cashboxJournals.size(); i++ ) {
                writeJournalElement(
                        zos,
                        cashboxJournals.get(i).getJournalDate(),
                        cashboxJournals.get(i).getJournalMessage(),
                        hasNextElement(cashboxJournals, i));
            }
            writeLine(zos, "  ],");

            writeLine(zos, "  \"systemEvents\": [");
            List<SystemJournal> systemJournals = systemJournalRepository.loadBy(period);
            for (int i = 0; i < systemJournals.size(); i++ ) {
                writeJournalElement(
                    zos,
                    systemJournals.get(i).getJournalDate(),
                    systemJournals.get(i).getJournalMessage(),
                    hasNextElement(systemJournals, i));
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
                    // noinspection ThrowFromFinallyBlock
                    throw new RuntimeException(e);
                }
            }
        }
        return zipFile;
    }

    private void writeJournalElement(ZipOutputStream zos, JournalDate journalDate, JournalMessage journalMessage, boolean nextElement)
            throws IOException {
        writeLine(zos, "    {");
        writeLine(zos, "      \"date\": \"" + journalDate.asStringToBeSigned() + "\",");
        writeLine(zos, "      \"message\": \"" + journalMessage.get() + "\"");
        writeLine(zos, "    }" + (nextElement ? "," : ""));
    }

    private boolean hasNextElement(List<?> list, int currentPosition) {
        return currentPosition < (list.size() - 1);
    }

    @Override
    public File exportBasedOnRKSV(Period period) {
        String fileName = createFileName("depRKSV", period);
        File zipFile = createZipFile(fileName);
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
            zos.putNextEntry(new ZipEntry(fileName + ".json"));
            Certificate certificate = rkOnlineResourceCertificate.loadCertificate();
            writeLine(zos, "{");
            writeLine(zos, "  \"Belege-Gruppe\": [");
            writeLine(zos, "    {");
            writeLine(zos, "      \"Signaturzertifikat\": " + "\"" + certificate.getSignaturzertifikat() + "\",");
            writeLine(zos, "      \"Zertifizierungsstellen\": [");
            for (int i = 0; i < certificate.getZertifizierungsstellen().size(); i++ ) {
                String certificateString = "        \"" + certificate.getZertifizierungsstellen().get(i) + "\"";
                if (i != (certificate.getZertifizierungsstellen().size() - 1)) {
                    certificateString += ",";
                }
                writeLine(zos, certificateString);
            }
            writeLine(zos, "      ],");
            addBelegeKompakt(
                period,
                zos,
                (currentDay) -> receiptRepository.loadCompactJWSRepresentations(Period.day(currentDay), cashBoxContext.get()));
            writeLine(zos, "    }");
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
                    // noinspection ThrowFromFinallyBlock
                    throw new RuntimeException(e);
                }
            }
        }
        return zipFile;
    }

    private void addBelegeKompakt(Period period, ZipOutputStream zos, Function<LocalDate, List<String>> loadCompactJwsRepresentationsForDay)
            throws IOException {
        writeLine(zos, "      \"Belege-kompakt\": [");
        LocalDate currentDay = period.getStartDay().toLocalDate();
        boolean first = true;
        while (currentDay.isBefore(period.getEndDay().toLocalDate()) || currentDay.isEqual(period.getEndDay().toLocalDate())) {
            List<String> compactJwsRepresentations = loadCompactJwsRepresentationsForDay.apply(currentDay);
            for (int i = 0; i < compactJwsRepresentations.size(); i++ ) {
                if ( !first) {
                    writeLine(zos, ",");
                }
                write(zos, "          \"" + compactJwsRepresentations.get(i) + "\"");
                first = false;
            }
            currentDay = currentDay.plus(1, ChronoUnit.DAYS);
        }
        writeLine(zos, "");
        writeLine(zos, "      ]");
    }

    private String createFileName(String prefix, Period period) {
        return prefix +
            "_" +
            companyContext.get().getName().get().replaceAll(" ", "") +
            "_" +
            cashBoxContext.get().getName().get() +
            "_" +
            period.getStartDay().format(DateTimeFormatter.ISO_DATE) +
            "_" +
            period.getEndDay().format(DateTimeFormatter.ISO_DATE);
    }

    private File createZipFile(String fileName) {
        File zipFile = new File(System.getProperty("java.io.tmpdir"), fileName + ".zip");
        log.info("create DEP: " + zipFile);
        return zipFile;
    }

    private void writeLine(ZipOutputStream zos, String string) throws IOException {
        zos.write((string + "\n").getBytes());
    }

    private void write(ZipOutputStream zos, String string) throws IOException {
        zos.write(string.getBytes());
    }
}

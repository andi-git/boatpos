package org.regkas.repository.core.dep;

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
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.dep.DepExporter;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.signature.RkOnlineResourceCertificate;
import org.regkas.repository.api.values.Certificate;
import org.regkas.service.api.bean.Period;

@Dependent
public class DepExporterCore implements DepExporter {

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

    @Inject
    private RkOnlineResourceCertificate rkOnlineResourceCertificate;

    @Override
    public File exportBasedOnRKV2012(Period period) {
        String fileName = createFileName("depRKV2012", period);
        File zipFile = createZipFile(fileName);
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
                for (int i = 0; i < deps.size(); i++ ) {
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
                    // noinspection ThrowFromFinallyBlock
                    throw new RuntimeException(e);
                }
            }
        }
        return zipFile;
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
                (currentDay) -> receiptRepository.loadCompactJWSRepresentationsWithSignatureDeviceAvailable(Period.day(currentDay), cashBox));
            if (receiptRepository.loadLastWithSignatureDeviceNotAvailable(cashBox).isPresent()) {
                writeLine(zos, "    },");
                writeLine(zos, "    {");
                writeLine(zos, "      \"Signaturzertifikat\": " + "\"\",");
                writeLine(zos, "      \"Zertifizierungsstellen\": [],");
                addBelegeKompakt(
                    period,
                    zos,
                    (currentDay) -> receiptRepository.loadCompactJWSRepresentationsWithSignatureDeviceNotAvailable(Period.day(currentDay), cashBox));
            }
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

    private void addBelegeKompakt(Period period, ZipOutputStream zos, Function<LocalDate, List<String>> loadCompactKwsRepresentationsForDay)
            throws IOException {
        writeLine(zos, "      \"Belege-kompakt\": [");
        LocalDate currentDay = period.getStartDay().toLocalDate();
        while (currentDay.isBefore(period.getEndDay().toLocalDate()) || currentDay.isEqual(period.getEndDay().toLocalDate())) {
            List<String> compactJwsRepresentations = loadCompactKwsRepresentationsForDay.apply(currentDay);
            for (int i = 0; i < compactJwsRepresentations.size(); i++ ) {
                String jwsCompactRepresentation = "          \"" + compactJwsRepresentations.get(i) + "\"";
                if (i != (compactJwsRepresentations.size() - 1)) {
                    jwsCompactRepresentation += ",";
                }
                writeLine(zos, jwsCompactRepresentation);
            }
            currentDay = currentDay.plus(1, ChronoUnit.DAYS);
        }
        writeLine(zos, "      ]");
    }

    private String createFileName(String prefix, Period period) {
        return prefix +
            "_" +
            company.getName().get().replaceAll(" ", "") +
            "_" +
            cashBox.getName().get() +
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
}

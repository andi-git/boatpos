package org.regkas.service.core.integration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.context.CompanyContext;

@Dependent
public class QRCodesCreator {

    @Inject
    private CompanyContext companyContext;

    @Inject
    private CashBoxContext cashBoxContext;

    public File exportQRs(List<String> qrs) {
        File qrFile = createFileForQRs();
        try {
            FileOutputStream fos = new FileOutputStream(qrFile);
            writeLine(fos, "[");
            for (int i = 0; i < qrs.size(); i++ ) {
                String comma = "";
                if (i < (qrs.size() - 1)) {
                    comma = ",";
                }
                writeLine(fos, "\"" + qrs.get(i) + "\"" + comma);
            }
            writeLine(fos, "]");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return qrFile;
    }

    private File createFileForQRs() {
        return new File(
            System.getProperty("java.io.tmpdir"),
            "qrRKSV_" +
                companyContext.get().getName().get().replaceAll(" ", "") +
                "_" +
                cashBoxContext.get().getName().get() +
                "_" +
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) +
                ".json");
    }

    private void writeLine(FileOutputStream zos, String string) throws IOException {
        zos.write((string + "\n").getBytes());
    }
}

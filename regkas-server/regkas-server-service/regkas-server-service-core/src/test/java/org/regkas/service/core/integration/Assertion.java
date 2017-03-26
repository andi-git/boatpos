package org.regkas.service.core.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.values.IVToEncryptTurnoverCounter;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.domain.core.crypto.AES;
import org.regkas.domain.core.crypto.Crypto;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.Period;

@SuppressWarnings({"ConstantConditions", "WeakerAccess"})
@Dependent
public class Assertion {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private Crypto crypto;

    public void assertReceiptCount(int exptectedReceiptCounts) {
        assertEquals(exptectedReceiptCounts, receiptRepository.loadBy(Period.untilNow(), cashBoxContext.get()).size());
    }

    public void assertTurnover(long exptectedTurnover) {
        assertEquals(exptectedTurnover, cashBoxRepository.loadBy(new Name("CASHBOX-DEMO-1")).get().getTurnoverCountCent().get().longValue());
    }

    public void assertBmfVerification(ZipFile depExportZipFile, File qrExportFile) throws Exception {
        File regkassenVerificationDepformatJar = new File(System.getProperty("java.io.tmpdir") + "/regkassen-verification-depformat-1.0.0.jar");
        File regkassenVerificationReceiptsJar = new File(System.getProperty("java.io.tmpdir") + "/regkassen-verification-receipts-1.0.0.jar");
        assertVerification(regkassenVerificationReceiptsJar, qrExportFile);
        Enumeration<? extends ZipEntry> entries = depExportZipFile.entries();
        while (entries.hasMoreElements()) {
            InputStream inputStream = depExportZipFile.getInputStream(entries.nextElement());
            File unzippedFile = new File(System.getProperty("java.io.tmpdir") + "/dep.json");
            FileUtils.copyInputStreamToFile(inputStream, unzippedFile);
            assertVerification(regkassenVerificationDepformatJar, unzippedFile);
        }
    }

    private void assertVerification(File jarForVerification, File fileToVerify) throws Exception {
        File cryptographicMaterialContainer = new File(System.getProperty("java.io.tmpdir") + "/cryptographicMaterialContainer.json");
        File outputDir = new File(System.getProperty("java.io.tmpdir") + "/output/" + fileToVerify.getName());
        String command = "java -jar " +
            jarForVerification +
            " -v -f -i " +
            fileToVerify +
            " -c " +
            cryptographicMaterialContainer +
            " -o " +
            outputDir;
        log.info("command: " + command);
        Process proc = Runtime.getRuntime().exec(command);
        InputStream errorStream = proc.getErrorStream();
        InputStream inputStream = proc.getInputStream();
        StringWriter inputWriter = new StringWriter();
        StringWriter errorWriter = new StringWriter();
        IOUtils.copy(inputStream, inputWriter, "UTF-8");
        IOUtils.copy(errorStream, errorWriter, "UTF-8");
        String outputConsole = inputWriter.toString();
        String errorConsole = errorWriter.toString();
        assertFalse("there is a 'failure' in the output of the 'Prüftool'",
            StringUtils.containsIgnoreCase(outputConsole, "failure") ||
                StringUtils.containsIgnoreCase(errorConsole, "failure") ||
                StringUtils.containsIgnoreCase(outputConsole, "fail") ||
                StringUtils.containsIgnoreCase(errorConsole, "fail"));
    }

    public void assertBillBean(BillBean billBeanExpected, BillBean billBeanActual, CashBox cashBox) {
        System.out.println("assert bill-beans:");
        System.out.println("  expected: " + billBeanExpected.getJwsCompact());
        System.out.println("  actual:   " + billBeanActual.getJwsCompact());
        assertEquals(billBeanExpected.getCashBoxID(), billBeanActual.getCashBoxID());
        assertEquals(billBeanExpected.getReceiptIdentifier(), billBeanActual.getReceiptIdentifier());
        assertEquals(billBeanExpected.getReceiptDateAndTime(), billBeanActual.getReceiptDateAndTime());
        assertEquals(billBeanExpected.getSumTaxSetNormal(), billBeanActual.getSumTaxSetNormal());
        assertEquals(billBeanExpected.getSumTaxSetErmaessigt1(), billBeanActual.getSumTaxSetErmaessigt1());
        assertEquals(billBeanExpected.getSumTaxSetErmaessigt2(), billBeanActual.getSumTaxSetErmaessigt2());
        assertEquals(billBeanExpected.getSumTaxSetNull(), billBeanActual.getSumTaxSetNull());
        assertEquals(billBeanExpected.getSumTaxSetBesonders(), billBeanActual.getSumTaxSetBesonders());
        assertEquals(
            "turnover doesn't match: expected: " +
                decryptTurnover(billBeanExpected, cashBox) +
                ", actual: " +
                decryptTurnover(billBeanActual, cashBox),
            billBeanExpected.getEncryptedTurnoverValue(),
            billBeanActual.getEncryptedTurnoverValue());
    }

    private long decryptTurnover(BillBean billBean, CashBox cashBox) {
        return crypto
            .decryptCTR(getIVToEncryptTurnoverCounter(billBean), billBean.getEncryptedTurnoverValue(), new AES.AESKey(cashBox.getAesKeyBase64()))
            .get();
    }

    private IVToEncryptTurnoverCounter getIVToEncryptTurnoverCounter(BillBean billBean) {
        return new IVToEncryptTurnoverCounter(new Name(billBean.getCashBoxID()), new ReceiptId(billBean.getReceiptIdentifier()));
    }
}

package org.regkas.service.core.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.context.CompanyContext;
import org.regkas.domain.api.context.UserContext;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.ProductRepository;
import org.regkas.domain.api.serializer.NonPrettyPrintingGson;
import org.regkas.domain.api.serializer.Serializer;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.values.IVToEncryptTurnoverCounter;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.domain.core.crypto.AES;
import org.regkas.domain.core.crypto.Crypto;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.DateTimeHelperMock;
import org.regkas.service.core.email.MailSenderFactory;
import org.regkas.service.core.email.MailSenderMock;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderFactory;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderMock;
import org.regkas.service.core.receipt.ReceiptIdCalculatorFactory;
import org.regkas.service.core.receipt.RkOnlineResourceSessionThrowingException;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import com.google.common.collect.Lists;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class SzenarioBmfTest extends EntityManagerProviderForRegkas {

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private UserContext userContext;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ReceiptIdCalculatorFactory receiptIdCalculatorFactory;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private SaleService saleService;

    private ReceiptIdCalculatorMock receiptIdCalculator;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private JournalService journalService;

    @Inject
    private MailSenderFactory mailSenderFactory;

    @Inject
    private FinancialOfficeSenderFactory financialOfficeSenderFactory;

    private MailSenderMock mailSenderMock = new MailSenderMock();

    private FinancialOfficeSenderMock financialOfficeSenderMock = new FinancialOfficeSenderMock();

    @Inject
    private Crypto crypto;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Before
    public void before() {
        System.setProperty("boatpos.ignore.handling.of.signature.device.availability", "true");
        System.setProperty("boatpos.crypto.complement.rep.bytes", "6");
        rkOnlineContext.setEnvironment(Environment.TEST);
        receiptIdCalculator = new ReceiptIdCalculatorMock();
        receiptIdCalculatorFactory.setReceiptIdCalculator(receiptIdCalculator);
        mailSenderMock.reset();
        mailSenderFactory.setMailSender(mailSenderMock);
        financialOfficeSenderMock.reset();
        financialOfficeSenderFactory.setFinancialOfficeSender(financialOfficeSenderMock);
    }

    @After
    public void after() {
        rkOnlineContext.resetSessions();
        receiptIdCalculatorFactory.reset();
        dateTimeHelper.resetTime();
        mailSenderFactory.reset();
        financialOfficeSenderFactory.reset();
        System.clearProperty("boatpos.ignore.handling.of.signature.device.availability");
        System.clearProperty("boatpos.crypto.complement.rep.bytes");
    }

    @Test
    @Transactional
    public void testSzenario01() throws Exception {
        testSzenario("testdata/szenario01/TESTSUITE_TEST_SZENARIO_1.json", "testdata/szenario01/qr-code-rep.json");
    }

    @Test
    @Transactional
    public void testSzenario02() throws Exception {
        testSzenario("testdata/szenario02/TESTSUITE_TEST_SZENARIO_2.json", "testdata/szenario02/qr-code-rep.json");
    }

    @Test
    @Transactional
    public void testSzenario03() throws Exception {
        testSzenario("testdata/szenario03/TESTSUITE_TEST_SZENARIO_3.json", "testdata/szenario03/qr-code-rep.json");
    }

    @Test
    @Transactional
    public void testSzenario04() throws Exception {
        testSzenario("testdata/szenario04/TESTSUITE_TEST_SZENARIO_4.json", "testdata/szenario04/qr-code-rep.json");
    }

    @Test
    @Transactional
    public void testSzenario05() throws Exception {
        testSzenario("testdata/szenario05/TESTSUITE_TEST_SZENARIO_5.json", "testdata/szenario05/qr-code-rep.json");
    }

    @Test
    @Transactional
    public void testSzenario06() throws Exception {
        testSzenario("testdata/szenario06/TESTSUITE_TEST_SZENARIO_6.json", "testdata/szenario06/qr-code-rep.json");
    }

    @Test
    @Transactional
    public void testSzenario07() throws Exception {
        testSzenario("testdata/szenario07/TESTSUITE_TEST_SZENARIO_7.json", "testdata/szenario07/qr-code-rep.json");
    }

    @Test
    @Transactional
    public void testSzenario08() throws Exception {
        testSzenario("testdata/szenario08/TESTSUITE_TEST_SZENARIO_8.json", "testdata/szenario08/qr-code-rep.json");
    }

    private void testSzenario(String fileNameTestSuite, String fileNameQrCodeRep) throws Exception {
        TestSuiteSzenario testSuiteSzenario = serializer.deserialize(loadContentFromFile(fileNameTestSuite), TestSuiteSzenario.class);
        List<BillBean> billBeansExpected = Arrays
            .stream(serializer.deserialize(loadContentFromFile(fileNameQrCodeRep), String[].class))
            .map(BillBean::fromJwsCompact)
            .collect(Collectors.toList());

        CashBox cashBox = initContext(testSuiteSzenario);
        List<String> qrs = new ArrayList<>();
        for (int i = 0; i < testSuiteSzenario.getCashBoxInstructionList().size(); i++ ) {
            TestSuiteSzenario.CashBoxInstruction cashBoxInstruction = testSuiteSzenario.getCashBoxInstructionList().get(i);
            BillBean billBean = createNextSale(cashBoxInstruction);
            qrs.add(billBean.getJwsCompact());
            BillBean billBeanExpected = billBeansExpected.get(i);
            if (i == 0) {
                assertEquals(billBeanExpected.getSignatureValuePreviousReceipt(), billBean.getSignatureValuePreviousReceipt());
            }
            assertBillBean(billBeanExpected, billBean, cashBox);
        }
        ZipFile depExportZipFile = new ZipFile(journalService.datenErfassungsProtokollRKSV());
        File qrExportFile = exportQRs(qrs);
        assertBmfVerification(depExportZipFile, qrExportFile);
    }

    private void assertBmfVerification(ZipFile depExportZipFile, File qrExportFile) throws Exception {
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
        File outputDir = new File(System.getProperty("java.io.tmpdir") + "/output");
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
        assertFalse(StringUtils.containsIgnoreCase(outputConsole, "failure") || StringUtils.containsIgnoreCase(errorConsole, "failure"));
    }

    private File exportQRs(List<String> qrs) {
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

    private void assertBillBean(BillBean billBeanExpected, BillBean billBeanActual, CashBox cashBox) {
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

    private BillBean createNextSale(TestSuiteSzenario.CashBoxInstruction cashBoxInstruction) {
        log.info("create next sale for: " + cashBoxInstruction.getReceiptIdentifier());
        setNextReceiptId(cashBoxInstruction);
        setNextReceiptTime(cashBoxInstruction);
        setSignatureDeviceAvailableOrDamaged(cashBoxInstruction);

        SaleBean sale = new SaleBean();
        addPaymentMethod(sale);
        addReceiptType(sale, cashBoxInstruction);
        addReceiptElements(sale, cashBoxInstruction);
        BillBean bill = saleService.sale(sale);

        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        return bill;
    }

    private void setSignatureDeviceAvailableOrDamaged(TestSuiteSzenario.CashBoxInstruction cashBoxInstruction) {
        if (Boolean.valueOf(cashBoxInstruction.getSignatureDeviceDamaged())) {
            rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());
        }
    }

    private void addPaymentMethod(SaleBean sale) {
        sale.setPaymentMethod("cash");
    }

    private void addReceiptType(SaleBean sale, TestSuiteSzenario.CashBoxInstruction cashBoxInstruction) {
        sale.setReceiptType(convertReceiptType(cashBoxInstruction.getTypeOfReceipt()));
    }

    private void addReceiptElements(SaleBean sale, TestSuiteSzenario.CashBoxInstruction cashBoxInstruction) {
        ProductBean productTaxSetStandard = productRepository.loadBy(new Name("Standard"), cashBoxContext.get()).get().asDto();
        ProductBean productTaxSetErmaessigt1 = productRepository.loadBy(new Name("Ermaessigt1"), cashBoxContext.get()).get().asDto();
        ProductBean productTaxSetErmaessigt2 = productRepository.loadBy(new Name("Ermaessigt2"), cashBoxContext.get()).get().asDto();
        ProductBean productTaxSetNull = productRepository.loadBy(new Name("Null"), cashBoxContext.get()).get().asDto();
        ProductBean productTaxSetBesonders = productRepository.loadBy(new Name("Besonders"), cashBoxContext.get()).get().asDto();
        ReceiptElementBean sumTaxSetNormal = new ReceiptElementBean(
            productTaxSetStandard,
            1,
            new BigDecimal(cashBoxInstruction.getSimplifiedReceipt().getTaxSetNormal()));
        ReceiptElementBean sumTaxSetErmaessigt1 = new ReceiptElementBean(
            productTaxSetErmaessigt1,
            1,
            new BigDecimal(cashBoxInstruction.getSimplifiedReceipt().getTaxSetErmaessigt1()));
        ReceiptElementBean sumTaxSetErmaessigt2 = new ReceiptElementBean(
            productTaxSetErmaessigt2,
            1,
            new BigDecimal(cashBoxInstruction.getSimplifiedReceipt().getTaxSetErmaessigt2()));
        ReceiptElementBean sumTaxSetNull = new ReceiptElementBean(
            productTaxSetNull,
            1,
            new BigDecimal(cashBoxInstruction.getSimplifiedReceipt().getTaxSetNull()));
        ReceiptElementBean sumTaxSetBesonders = new ReceiptElementBean(
            productTaxSetBesonders,
            1,
            new BigDecimal(cashBoxInstruction.getSimplifiedReceipt().getTaxSetBesonders()));
        sale.setSaleElements(Lists.newArrayList(sumTaxSetNormal, sumTaxSetErmaessigt1, sumTaxSetErmaessigt2, sumTaxSetNull, sumTaxSetBesonders));
    }

    private String convertReceiptType(String receiptType) {
        if ("START_BELEG".equalsIgnoreCase(receiptType)) {
            return "Start-Beleg";
        } else if ("STANDARD_BELEG".equalsIgnoreCase(receiptType)) {
            return "Standard-Beleg";
        } else if ("NULL_BELEG".equalsIgnoreCase(receiptType)) {
            return "Null-Beleg";
        } else if ("START_BELEG".equalsIgnoreCase(receiptType)) {
            return "Start-Beleg";
        } else if ("TRAINING_BELEG".equalsIgnoreCase(receiptType)) {
            return "Training-Beleg";
        } else if ("STORNO_BELEG".equalsIgnoreCase(receiptType)) {
            return "Storno-Beleg";
        } else {
            throw new RuntimeException("unknown receipt-type: " + receiptType);
        }
    }

    private void setNextReceiptId(TestSuiteSzenario.CashBoxInstruction cashBoxInstruction) {
        receiptIdCalculator.setNextReceiptId(new ReceiptId(cashBoxInstruction.getReceiptIdentifier()));
    }

    private void setNextReceiptTime(TestSuiteSzenario.CashBoxInstruction cashBoxInstruction) {
        dateTimeHelper.setTime(LocalDateTime.parse(cashBoxInstruction.getDateToUse()));
    }

    private CashBox initContext(TestSuiteSzenario testSuiteSzenario) {
        CashBox cashBox = cashBoxRepository.loadBy(new Name(testSuiteSzenario.getCashBoxId())).get();
        cashBoxContext.set(cashBox);
        companyContext.set(cashBox.getCompany());
        userContext.set(cashBox.getCompany().getUsers().iterator().next());
        return cashBox;
    }

    private InputStream streamFile(String relativeFileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(relativeFileName);
    }

    private String loadContentFromFile(String relativeFileName) throws Exception {
        StringWriter writer = new StringWriter();
        IOUtils.copy(streamFile(relativeFileName), writer, "UTF-8");
        return writer.toString();
    }
}

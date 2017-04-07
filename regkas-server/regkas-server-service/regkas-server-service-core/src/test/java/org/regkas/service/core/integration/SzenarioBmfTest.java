package org.regkas.service.core.integration;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
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
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
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

@SuppressWarnings({"OptionalGetWithoutIsPresent", "ConstantConditions"})
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

    @Inject
    private Assertion assertion;

    @Inject
    private QRCodesCreator qrCodesCreator;

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
    @Ignore
    public void testSzenario02() throws Exception {
        testSzenario("testdata/szenario02/TESTSUITE_TEST_SZENARIO_2.json", "testdata/szenario02/qr-code-rep.json");
    }

    @Test
    @Transactional
    @Ignore
    public void testSzenario03() throws Exception {
        testSzenario("testdata/szenario03/TESTSUITE_TEST_SZENARIO_3.json", "testdata/szenario03/qr-code-rep.json");
    }

    @Test
    @Transactional
    @Ignore
    public void testSzenario04() throws Exception {
        testSzenario("testdata/szenario04/TESTSUITE_TEST_SZENARIO_4.json", "testdata/szenario04/qr-code-rep.json");
    }

    @Test
    @Transactional
    @Ignore
    public void testSzenario05() throws Exception {
        testSzenario("testdata/szenario05/TESTSUITE_TEST_SZENARIO_5.json", "testdata/szenario05/qr-code-rep.json");
    }

    @Test
    @Transactional
    @Ignore
    public void testSzenario06() throws Exception {
        testSzenario("testdata/szenario06/TESTSUITE_TEST_SZENARIO_6.json", "testdata/szenario06/qr-code-rep.json");
    }

    @Test
    @Transactional
    @Ignore
    public void testSzenario07() throws Exception {
        testSzenario("testdata/szenario07/TESTSUITE_TEST_SZENARIO_7.json", "testdata/szenario07/qr-code-rep.json");
    }

    @Test
    @Transactional
    @Ignore
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
            assertion.assertBillBean(billBeanExpected, billBean, cashBox);
        }
        ZipFile depExportZipFile = new ZipFile(journalService.datenErfassungsProtokollRKSV());
        File qrExportFile = qrCodesCreator.exportQRs(qrs);
        assertion.assertBmfVerification(depExportZipFile, qrExportFile);
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

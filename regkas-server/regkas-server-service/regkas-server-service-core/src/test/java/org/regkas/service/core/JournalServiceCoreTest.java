package org.regkas.service.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.context.CompanyContext;
import org.regkas.domain.api.context.UserContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.CompanyRepository;
import org.regkas.domain.api.repository.UserRepository;
import org.regkas.domain.api.serializer.NonPrettyPrintingGson;
import org.regkas.domain.api.serializer.Serializer;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.IncomeBean;
import org.regkas.service.api.bean.ProductGroupIncomeBean;
import org.regkas.service.api.bean.TaxElementBean;
import org.regkas.service.core.dep.DepExportRKSV;
import org.regkas.service.core.dep.DepExportRKV2012;
import org.regkas.service.core.email.MailSenderFactory;
import org.regkas.service.core.email.MailSenderMock;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderFactory;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderMock;
import org.regkas.service.core.receipt.FirstSale;
import org.regkas.service.core.receipt.RkOnlineResourceSessionThrowingException;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import com.google.common.io.ByteStreams;

@RunWith(Arquillian.class)
public class JournalServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private JournalService journalService;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserContext userContext;

    @Inject
    private DateTimeHelperMock dateTimeHelperMock;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private MailSenderFactory mailSenderFactory;

    @Inject
    private FinancialOfficeSenderFactory financialOfficeSenderFactory;

    protected MailSenderMock mailSenderMock = new MailSenderMock();

    protected FinancialOfficeSenderMock financialOfficeSenderMock = new FinancialOfficeSenderMock();

    @Inject
    private FirstSale firstSale;

    @Inject
    private SaleService saleService;

    @Before
    public void before() {
        mailSenderMock.reset();
        mailSenderFactory.setMailSender(mailSenderMock);
        financialOfficeSenderMock.reset();
        financialOfficeSenderFactory.setFinancialOfficeSender(financialOfficeSenderMock);
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
        rkOnlineContext.setEnvironment(Environment.TEST);
        rkOnlineContext.resetSessions();
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
    }

    @After
    public void after() {
        mailSenderFactory.reset();
        financialOfficeSenderFactory.reset();
        companyContext.clear();
        cashBoxContext.clear();
        companyContext.clear();
        userContext.clear();
    }

    @Test
    @Transactional
    public void testIncome() throws Exception {
        IncomeBean income = journalService.totalIncomeFor(2015);
        assertEquals(new BigDecimal("22.00"), income.getTotalIncome());
        assertEquals(7, income.getIncomeElements().size());
        assertIncomeElement(income.getIncomeElements().get(0), "Snack", 10, new BigDecimal("11.00"));
        assertIncomeElement(income.getIncomeElements().get(1), "Eskimoeis", 10, new BigDecimal("6.00"));
        assertIncomeElement(income.getIncomeElements().get(2), "Eismischgetränk", 10, new BigDecimal("0.00"));
        assertIncomeElement(income.getIncomeElements().get(3), "Bier", 20, new BigDecimal("0.00"));
        assertIncomeElement(income.getIncomeElements().get(4), "Wein", 20, new BigDecimal("0.00"));
        assertIncomeElement(income.getIncomeElements().get(5), "alkoholfreies Getränk", 20, new BigDecimal("5.00"));
        assertIncomeElement(income.getIncomeElements().get(6), "Kaffee", 20, new BigDecimal("0.00"));
        assertEquals(5, income.getTaxElements().size());
        assertTaxElement(income.getTaxElements().get(0), 20, new BigDecimal("5.00"), new BigDecimal("4.17"), new BigDecimal("0.83"));
        assertTaxElement(income.getTaxElements().get(1), 10, new BigDecimal("17.00"), new BigDecimal("15.45"), new BigDecimal("1.55"));
        assertTaxElement(income.getTaxElements().get(2), 13, new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));
        assertTaxElement(income.getTaxElements().get(3), 0, new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));
        assertTaxElement(income.getTaxElements().get(4), 19, new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));

        income = journalService.totalIncomeFor(2015, 7);
        assertEquals(7, income.getIncomeElements().size());
        assertEquals(new BigDecimal("11.00"), income.getIncomeElements().get(0).getIncome());
        assertEquals(10, income.getIncomeElements().get(0).getTaxPercent().intValue());

        income = journalService.totalIncomeFor(2015, 7, 1);
        assertEquals(7, income.getIncomeElements().size());
        assertEquals(new BigDecimal("11.00"), income.getIncomeElements().get(0).getIncome());
        assertEquals(10, income.getIncomeElements().get(0).getTaxPercent().intValue());
    }

    private void assertIncomeElement(ProductGroupIncomeBean incomeElement, String expectedName, int expectedTaxPercent, BigDecimal expectedIncome) {
        assertEquals(expectedName, incomeElement.getName());
        assertEquals(expectedTaxPercent, incomeElement.getTaxPercent().intValue());
        assertEquals(expectedIncome, incomeElement.getIncome());
    }

    private void assertTaxElement(
            TaxElementBean taxElement,
            int expectedTaxPercent,
            BigDecimal expectedPrice,
            BigDecimal expectedPriceBeforeTax,
            BigDecimal expectedPriceTax) {
        assertEquals(expectedTaxPercent, taxElement.getTaxPercent().intValue());
        assertEquals(expectedPrice, taxElement.getPrice());
        assertEquals(expectedPriceBeforeTax, taxElement.getPriceBeforeTax());
        assertEquals(expectedPriceTax, taxElement.getPriceTax());
    }

    @Test
    @Transactional
    public void testDatenErfassungsProtokollRKV2012() throws Exception {
        assertDEP(
            journalService.datenErfassungsProtokollRKV2012(2015),
            LocalDateTime.of(2015, 1, 1, 0, 0, 0),
            LocalDateTime.of(2015, 12, 31, 23, 59, 59, 999999999),
            3);
        assertDEP(
            journalService.datenErfassungsProtokollRKV2012(2015, 7),
            LocalDateTime.of(2015, 7, 1, 0, 0, 0),
            LocalDateTime.of(2015, 7, 31, 23, 59, 59, 999999999),
            3);
        assertDEP(
            journalService.datenErfassungsProtokollRKV2012(2015, 7, 1),
            LocalDateTime.of(2015, 7, 1, 0, 0, 0),
            LocalDateTime.of(2015, 7, 1, 23, 59, 59, 999999999),
            3);
        assertDEP(
            journalService.datenErfassungsProtokollRKV2012(2015, 7, 2),
            LocalDateTime.of(2015, 7, 2, 0, 0, 0),
            LocalDateTime.of(2015, 7, 2, 23, 59, 59, 999999999),
            0);
    }

    private void assertDEP(File file, LocalDateTime start, LocalDateTime end, int elements) throws IOException {
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            DepExportRKV2012 depExport = serializer
                .deserialize(new String(ByteStreams.toByteArray(zipFile.getInputStream(entries.nextElement()))), DepExportRKV2012.class);
            assertEquals("company", depExport.getCompany());
            assertEquals("atu123", depExport.getAtu());
            assertEquals("RegKas1", depExport.getCashBoxId());
            assertEquals(dateTimeHelper.currentTime(), depExport.getCreated());
            assertEquals(start, depExport.getFrom());
            assertEquals(end, depExport.getTo());
            assertEquals(elements, depExport.getCashBoxInstructionList().size());
        }
    }

    @Test
    @Transactional
    public void testDatenErfassungsProtokollRKSV() throws Exception {
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());
        saleService.sale(firstSale.createDefaultSale());
        saleService.sale(firstSale.createDefaultSale());
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        saleService.sale(firstSale.createDefaultSale());
        saleService.sale(firstSale.createDefaultSale());

        File file = journalService.datenErfassungsProtokollRKSV();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            String content = new String(ByteStreams.toByteArray(zipFile.getInputStream(entries.nextElement())));
            DepExportRKSV depExport = serializer.deserialize(content, DepExportRKSV.class);
            assertEquals(1, depExport.getBelegeGruppe().size());
            assertEquals(2, depExport.getBelegeGruppe().get(0).getZertifizierungsstellen().size());
            assertEquals(8, depExport.getBelegeGruppe().get(0).getBelegeKompakt().size());
        }
    }
}

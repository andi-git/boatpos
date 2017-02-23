package org.regkas.service.core;

import com.google.common.io.ByteStreams;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.bean.IncomeBean;
import org.regkas.service.api.bean.ProductGroupIncomeBean;
import org.regkas.service.api.bean.TaxElementBean;
import org.regkas.service.core.context.CashBoxContext;
import org.regkas.service.core.context.CompanyContext;
import org.regkas.service.core.serializer.DEPExport;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.Assert.assertEquals;

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
    private DateTimeHelperMock dateTimeHelperMock;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Test
    @Transactional
    public void testIncome() throws Exception {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
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
        cashBoxContext.clear();
    }

    private void assertIncomeElement(ProductGroupIncomeBean incomeElement, String expectedName, int expectedTaxPercent, BigDecimal expectedIncome) {
        assertEquals(expectedName, incomeElement.getName());
        assertEquals(expectedTaxPercent, incomeElement.getTaxPercent().intValue());
        assertEquals(expectedIncome, incomeElement.getIncome());
    }

    private void assertTaxElement(TaxElementBean taxElement, int expectedTaxPercent, BigDecimal expectedPrice, BigDecimal expectedPriceBeforeTax, BigDecimal expectedPriceTax) {
        assertEquals(expectedTaxPercent, taxElement.getTaxPercent().intValue());
        assertEquals(expectedPrice, taxElement.getPrice());
        assertEquals(expectedPriceBeforeTax, taxElement.getPriceBeforeTax());
        assertEquals(expectedPriceTax, taxElement.getPriceTax());
    }

    @Test
    @Transactional
    public void testDatenErfassungsProtokoll() throws Exception {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertDEP(journalService.datenErfassungsProtokoll(2015), LocalDateTime.of(2015, 1, 1, 0, 0, 0), LocalDateTime.of(2015, 12, 31, 23, 59, 59, 999999999), 2);
        assertDEP(journalService.datenErfassungsProtokoll(2015, 7), LocalDateTime.of(2015, 7, 1, 0, 0, 0), LocalDateTime.of(2015, 7, 31, 23, 59, 59, 999999999), 2);
        assertDEP(journalService.datenErfassungsProtokoll(2015, 7, 1), LocalDateTime.of(2015, 7, 1, 0, 0, 0), LocalDateTime.of(2015, 7, 1, 23, 59, 59, 999999999), 2);
        assertDEP(journalService.datenErfassungsProtokoll(2015, 7, 2), LocalDateTime.of(2015, 7, 2, 0, 0, 0), LocalDateTime.of(2015, 7, 2, 23, 59, 59, 999999999), 0);
        cashBoxContext.clear();
        companyContext.clear();
    }

    private void assertDEP(File file, LocalDateTime start, LocalDateTime end, int elements) throws IOException {
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            DEPExport depExport = serializer.deserialize(new String(ByteStreams.toByteArray(zipFile.getInputStream(entries.nextElement()))), DEPExport.class);
            assertEquals("company", depExport.getCompany());
            assertEquals("atu123", depExport.getAtu());
            assertEquals("RegKas1", depExport.getCashBoxId());
            assertEquals(dateTimeHelper.currentTime(), depExport.getCreated());
            assertEquals(start, depExport.getFrom());
            assertEquals(end, depExport.getTo());
            assertEquals(elements, depExport.getCashBoxInstructionList().size());
        }
    }
}
package org.regkas.service.core.serializer;

import com.google.common.io.ByteStreams;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.bean.Period;
import org.regkas.service.core.DateTimeHelperMock;
import org.regkas.service.core.context.CashBoxContext;
import org.regkas.service.core.context.CompanyContext;
import org.regkas.service.core.context.UserContext;
import org.regkas.service.core.util.ReceiptToBillConverter;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class DEPExporterTest extends EntityManagerProviderForRegkas {

    @Inject
    private DEPExporter depExporter;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserContext userContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private ReceiptToBillConverter receiptToBillConverter;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Test
    @Transactional
    public void testExport() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));

        // if there is no dep-string, add it
//        for (Receipt receipt : receiptRepository.loadBy(Period.year(dateTimeHelper.currentTime()), cashBoxContext.get())) {
//            if (receipt.getDEP() == null || receipt.getDEP().get() == null || "".equals(receipt.getDEP().get())) {
//                receipt.setDEP(new DEPString(serializer.serialize(receiptToBillConverter.convert(receipt)))).persist();
//            }
//        }

        Period period = Period.year(dateTimeHelper.currentTime());
        ZipFile zipFile = new ZipFile(depExporter.export(period));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            DEPExport depExport = serializer.deserialize(new String(ByteStreams.toByteArray(zipFile.getInputStream(entries.nextElement()))), DEPExport.class);
            // misc data
            assertEquals("company", depExport.getCompany());
            assertEquals("atu123", depExport.getAtu());
            assertEquals("RegKas1", depExport.getCashBoxId());
            assertEquals(dateTimeHelper.currentTime(), depExport.getCreated());
            assertEquals(period.getStartDay(), depExport.getFrom());
            assertEquals(period.getEndDay(), depExport.getTo());
            // element 0
            assertEquals("RegKas1", depExport.getCashBoxInstructionList().get(0).getCashBoxID());
            assertEquals("2015-0000001", depExport.getCashBoxInstructionList().get(0).getReceiptIdentifier());
            assertEquals(LocalDateTime.of(2015, 7, 1, 12, 0, 13), depExport.getCashBoxInstructionList().get(0).getReceiptDateAndTime());
            assertEquals(new BigDecimal("11.00"), depExport.getCashBoxInstructionList().get(0).getSumTotal());
            assertEquals("Standard-Beleg", depExport.getCashBoxInstructionList().get(0).getReceiptType());
            assertEquals(new BigDecimal("5.00"), depExport.getCashBoxInstructionList().get(0).getSumTaxSetNormal());
            assertEquals(new BigDecimal("6.00"), depExport.getCashBoxInstructionList().get(0).getSumTaxSetErmaessigt1());
            assertEquals(new BigDecimal("0.00"), depExport.getCashBoxInstructionList().get(0).getSumTaxSetErmaessigt2());
            assertEquals(new BigDecimal("0.00"), depExport.getCashBoxInstructionList().get(0).getSumTaxSetNull());
            assertEquals(new BigDecimal("0.00"), depExport.getCashBoxInstructionList().get(0).getSumTaxSetBesonders());
            assertEquals("Cola", depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(0).getName());
            assertEquals(20, depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(0).getTaxPercent().longValue());
            assertEquals(2, depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(0).getAmount().longValue());
            assertEquals(new BigDecimal("4.17"), depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(0).getPricePreTax());
            assertEquals(new BigDecimal("0.83"), depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(0).getPriceTax());
            assertEquals(new BigDecimal("5.00"), depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(0).getPriceAfterTax());
            assertEquals("Cornetto", depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(1).getName());
            assertEquals(10, depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(1).getTaxPercent().longValue());
            assertEquals(3, depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(1).getAmount().longValue());
            assertEquals(new BigDecimal("5.45"), depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(1).getPricePreTax());
            assertEquals(new BigDecimal("0.55"), depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(1).getPriceTax());
            assertEquals(new BigDecimal("6.00"), depExport.getCashBoxInstructionList().get(0).getBillTaxSetElements().get(1).getPriceAfterTax());
            // element 1
            assertEquals("RegKas1", depExport.getCashBoxInstructionList().get(1).getCashBoxID());
            assertEquals("2015-0000002", depExport.getCashBoxInstructionList().get(1).getReceiptIdentifier());
            assertEquals(LocalDateTime.of(2015, 7, 1, 12, 0, 13), depExport.getCashBoxInstructionList().get(1).getReceiptDateAndTime());
            assertEquals(new BigDecimal("11.00"), depExport.getCashBoxInstructionList().get(1).getSumTotal());
            assertEquals("Standard-Beleg", depExport.getCashBoxInstructionList().get(1).getReceiptType());
            assertEquals(new BigDecimal("0.00"), depExport.getCashBoxInstructionList().get(1).getSumTaxSetNormal());
            assertEquals(new BigDecimal("11.00"), depExport.getCashBoxInstructionList().get(1).getSumTaxSetErmaessigt1());
            assertEquals(new BigDecimal("0.00"), depExport.getCashBoxInstructionList().get(1).getSumTaxSetErmaessigt2());
            assertEquals(new BigDecimal("0.00"), depExport.getCashBoxInstructionList().get(1).getSumTaxSetNull());
            assertEquals(new BigDecimal("0.00"), depExport.getCashBoxInstructionList().get(1).getSumTaxSetBesonders());
            assertEquals("Wurstsemmel", depExport.getCashBoxInstructionList().get(1).getBillTaxSetElements().get(0).getName());
            assertEquals(10, depExport.getCashBoxInstructionList().get(1).getBillTaxSetElements().get(0).getTaxPercent().longValue());
            assertEquals(5, depExport.getCashBoxInstructionList().get(1).getBillTaxSetElements().get(0).getAmount().longValue());
            assertEquals(new BigDecimal("10.00"), depExport.getCashBoxInstructionList().get(1).getBillTaxSetElements().get(0).getPricePreTax());
            assertEquals(new BigDecimal("1.00"), depExport.getCashBoxInstructionList().get(1).getBillTaxSetElements().get(0).getPriceTax());
            assertEquals(new BigDecimal("11.00"), depExport.getCashBoxInstructionList().get(1).getBillTaxSetElements().get(0).getPriceAfterTax());
        }

        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
    }
}
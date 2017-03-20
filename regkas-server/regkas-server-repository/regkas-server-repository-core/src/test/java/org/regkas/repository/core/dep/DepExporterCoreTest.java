package org.regkas.repository.core.dep;

import static org.junit.Assert.assertEquals;

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
import org.regkas.repository.api.builder.CashboxJournalBuilder;
import org.regkas.repository.api.builder.SystemJournalBuilder;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.context.CompanyContext;
import org.regkas.repository.api.context.UserContext;
import org.regkas.repository.api.dep.DepExporter;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.Period;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import com.google.common.io.ByteStreams;

@RunWith(Arquillian.class)
public class DepExporterCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private DepExporter depExporter;

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
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private CashboxJournalBuilder cashboxJournalBuilder;

    @Inject
    private SystemJournalBuilder systemJournalBuilder;

    @Before
    public void before() {
        rkOnlineContext.resetSessions();
        rkOnlineContext.setEnvironment(Environment.TEST);
    }

    @After
    public void after() {
        rkOnlineContext.resetSessions();
    }

    @Test
    @Transactional
    public void testExportBasedOnRKV2012() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));

        cashboxJournalBuilder
            .reset()
            .add(new JournalDate(dateTimeHelper.currentTime()))
            .add(new JournalMessage("message1"))
            .add(cashBoxContext.get())
            .build()
            .persist();
        cashboxJournalBuilder
            .reset()
            .add(new JournalDate(dateTimeHelper.currentTime()))
            .add(new JournalMessage("message2"))
            .add(cashBoxContext.get())
            .build()
            .persist();
        systemJournalBuilder
            .reset()
            .add(new JournalDate(dateTimeHelper.currentTime()))
            .add(new JournalMessage("message3"))
            .build()
            .persist();

        // if there is no dep-string, add it
        // for (Receipt receipt : receiptRepository.loadBy(Period.year(dateTimeHelper.currentTime()), cashBoxContext.get())) {
        // if (receipt.getDEP() == null || receipt.getDEP().get() == null || "".equals(receipt.getDEP().get())) {
        // receipt.setDEP(new DEPString(serializer.serialize(receiptToBillConverter.convert(receipt)))).persist();
        // }
        // }

        Period period = Period.year(dateTimeHelper.currentTime());
        ZipFile zipFile = new ZipFile(depExporter.exportBasedOnRKV2012(period));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            DepExportRKV2012 depExport = serializer
                .deserialize(new String(ByteStreams.toByteArray(zipFile.getInputStream(entries.nextElement()))), DepExportRKV2012.class);
            // misc data
            assertEquals("company", depExport.getCompany());
            assertEquals("atu123", depExport.getAtu());
            assertEquals("RegKas1", depExport.getCashBoxId());
            assertEquals(dateTimeHelper.currentTime(), depExport.getCreated());
            assertEquals(period.getStartDay(), depExport.getFrom());
            assertEquals(period.getEndDay(), depExport.getTo());
            // element 1
            BillBean billBean = depExport.getCashBoxInstructionList().get(1);
            assertEquals("RegKas1", billBean.getCashBoxID());
            assertEquals("2015-0000001", billBean.getReceiptIdentifier());
            assertEquals(LocalDateTime.of(2015, 7, 1, 12, 0, 13), billBean.getReceiptDateAndTime());
            assertEquals(new BigDecimal("11.00"), billBean.getSumTotal());
            assertEquals("Standard-Beleg", billBean.getReceiptType());
            assertEquals(new BigDecimal("5.00"), billBean.getSumTaxSetNormal());
            assertEquals(new BigDecimal("6.00"), billBean.getSumTaxSetErmaessigt1());
            assertEquals(new BigDecimal("0.00"), billBean.getSumTaxSetErmaessigt2());
            assertEquals(new BigDecimal("0.00"), billBean.getSumTaxSetNull());
            assertEquals(new BigDecimal("0.00"), billBean.getSumTaxSetBesonders());
            assertEquals("Cola", billBean.getBillTaxSetElements().get(0).getName());
            assertEquals(20, billBean.getBillTaxSetElements().get(0).getTaxPercent().longValue());
            assertEquals(2, billBean.getBillTaxSetElements().get(0).getAmount().longValue());
            assertEquals(new BigDecimal("4.17"), billBean.getBillTaxSetElements().get(0).getPricePreTax());
            assertEquals(new BigDecimal("0.83"), billBean.getBillTaxSetElements().get(0).getPriceTax());
            assertEquals(new BigDecimal("5.00"), billBean.getBillTaxSetElements().get(0).getPriceAfterTax());
            assertEquals("Cornetto", billBean.getBillTaxSetElements().get(1).getName());
            assertEquals(10, billBean.getBillTaxSetElements().get(1).getTaxPercent().longValue());
            assertEquals(3, billBean.getBillTaxSetElements().get(1).getAmount().longValue());
            assertEquals(new BigDecimal("5.45"), billBean.getBillTaxSetElements().get(1).getPricePreTax());
            assertEquals(new BigDecimal("0.55"), billBean.getBillTaxSetElements().get(1).getPriceTax());
            assertEquals(new BigDecimal("6.00"), billBean.getBillTaxSetElements().get(1).getPriceAfterTax());
            // element 2
            billBean = depExport.getCashBoxInstructionList().get(2);
            assertEquals("RegKas1", billBean.getCashBoxID());
            assertEquals("2015-0000002", billBean.getReceiptIdentifier());
            assertEquals(LocalDateTime.of(2015, 7, 1, 12, 0, 13), billBean.getReceiptDateAndTime());
            assertEquals(new BigDecimal("11.00"), billBean.getSumTotal());
            assertEquals("Standard-Beleg", billBean.getReceiptType());
            assertEquals(new BigDecimal("0.00"), billBean.getSumTaxSetNormal());
            assertEquals(new BigDecimal("11.00"), billBean.getSumTaxSetErmaessigt1());
            assertEquals(new BigDecimal("0.00"), billBean.getSumTaxSetErmaessigt2());
            assertEquals(new BigDecimal("0.00"), billBean.getSumTaxSetNull());
            assertEquals(new BigDecimal("0.00"), billBean.getSumTaxSetBesonders());
            assertEquals("Wurstsemmel", billBean.getBillTaxSetElements().get(0).getName());
            assertEquals(10, billBean.getBillTaxSetElements().get(0).getTaxPercent().longValue());
            assertEquals(5, billBean.getBillTaxSetElements().get(0).getAmount().longValue());
            assertEquals(new BigDecimal("10.00"), billBean.getBillTaxSetElements().get(0).getPricePreTax());
            assertEquals(new BigDecimal("1.00"), billBean.getBillTaxSetElements().get(0).getPriceTax());
            assertEquals(new BigDecimal("11.00"), billBean.getBillTaxSetElements().get(0).getPriceAfterTax());

            assertEquals(2, depExport.getCashboxEvents().size());
            assertEquals("message1", depExport.getCashboxEvents().get(0).getMessage());
            assertEquals("message2", depExport.getCashboxEvents().get(1).getMessage());
            assertEquals(1, depExport.getSystemEvents().size());
            assertEquals("message3", depExport.getSystemEvents().get(0).getMessage());
        }

        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
    }

    @Test
    @Transactional
    public void testExportBasedOnRKSV() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));

        Period period = Period.year(dateTimeHelper.currentTime());
        ZipFile zipFile = new ZipFile(depExporter.exportBasedOnRKSV(period));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            String content = new String(ByteStreams.toByteArray(zipFile.getInputStream(entries.nextElement())));
            System.out.println(content);
            DepExportRKSV depExport = serializer.deserialize(content, DepExportRKSV.class);
            assertEquals(
                "MIIEvTCCA6WgAwIBAgIEIVm59jANBgkqhkiG9w0BAQsFADCBoTELMAkGA1UEBgwCQVQxSDBGBgNVBAoMP0EtVHJ1c3QgR2VzLiBmLiBTaWNoZXJoZWl0c3N5c3RlbWUgaW0gZWxla3RyLiBEYXRlbnZlcmtlaHIgR21iSDEjMCEGA1UECwwaYS1zaWduLVByZW1pdW0tVGVzdC1TaWctMDIxIzAhBgNVBAMMGmEtc2lnbi1QcmVtaXVtLVRlc3QtU2lnLTAyMB4XDTE2MDMxMDE0NDczOVoXDTIwMDUxMDE0NDczOVowYzELMAkGA1UEBgwCQVQxGTAXBgNVBAMMEFVJRDogQVRVMTIzNDU2NzgxFDASBgNVBAQMC0FUVTEyMzQ1Njc4MQwwCgYDVQQqDANVSUQxFTATBgNVBAUMDDUyOTkxMjU1MjI4NjBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABDmCCjepLRPT+MvR4CHqycC25UNfFaFcyqCL8lwEVsUVk10bmQn290u20foEaa075geQvJokWbTwTQqLgORYVVejggIDMIIB/zCBhAYIKwYBBQUHAQEEeDB2MEYGCCsGAQUFBzAChjpodHRwOi8vd3d3LmEtdHJ1c3QuYXQvY2VydHMvYS1zaWduLVByZW1pdW0tVGVzdC1TaWctMDIuY3J0MCwGCCsGAQUFBzABhiBodHRwOi8vb2NzcC10ZXN0LmEtdHJ1c3QuYXQvb2NzcDAOBgNVHQ8BAf8EBAMCBsAwJwYIKwYBBQUHAQMBAf8EGDAWMAgGBgQAjkYBATAKBggrBgEFBQcLATCBrgYDVR0fBIGmMIGjMIGgoIGdoIGahoGXbGRhcDovL2xkYXAtdGVzdC5hLXRydXN0LmF0L291PWEtc2lnbi1QcmVtaXVtLVRlc3QtU2lnLTAyIChTSEEtMjU2KSxvPUEtVHJ1c3QsYz1BVD9jZXJ0aWZpY2F0ZXJldm9jYXRpb25saXN0P2Jhc2U/b2JqZWN0Y2xhc3M9ZWlkQ2VydGlmaWNhdGlvbkF1dGhvcml0eTAJBgNVHRMEAjAAMFkGA1UdIARSMFAwCAYGBACLMAEBMEQGBiooABEBCzA6MDgGCCsGAQUFBwIBFixodHRwOi8vd3d3LmEtdHJ1c3QuYXQvZG9jcy9jcC9hLXNpZ24tUHJlbWl1bTATBgNVHSMEDDAKgAhGBp+OQY4VvTARBgNVHQ4ECgQITEStzGF8AcEwDQYJKoZIhvcNAQELBQADggEBADKLJSnaG7sMsX0USrzLHRXuWPKCrOXbLfXmnmlAN2+2Pzd/wAXXL1G2dpRpyspoC4QxLVQrUQE+WQae94lXdI7yFwVk8Y5bR+K/bY/EfNSPiwSyxXtYOnMrH+s9szH5nCG4aRGxys5hrbxfyS8GHXqv8V6cUlddCYkwXL4XXBpSxh9UofVOA1+mtKEtESg9ifcayV6TwBOHmzg7GZe0IY32VIpjaJQA8LQU1YDARx+yfuxtc4KBk3o5ZaVmZ4xyX/elUTLQX8aMnT7sIbhA3+FFvaD70iYp+9kZL9LO9bzfS/yv0xmPjFLVQ817DwTHM1ZFGk5d6WRGBIm7fjq8QuM=",
                depExport.getBelegeGruppe().get(0).getSignaturzertifikat());
            assertEquals(
                "MIIEATCCAumgAwIBAgIEOWntwTANBgkqhkiG9w0BAQUFADCBlTELMAkGA1UEBhMCQVQxSDBGBgNVBAoMP0EtVHJ1c3QgR2VzLiBmLiBTaWNoZXJoZWl0c3N5c3RlbWUgaW0gZWxla3RyLiBEYXRlbnZlcmtlaHIgR21iSDEdMBsGA1UECwwUQS1UcnVzdC1UZXN0LVF1YWwtMDIxHTAbBgNVBAMMFEEtVHJ1c3QtVGVzdC1RdWFsLTAyMB4XDTE0MTEyNDE0NDkxN1oXDTI0MTExODEzNDkxN1owgaExCzAJBgNVBAYTAkFUMUgwRgYDVQQKDD9BLVRydXN0IEdlcy4gZi4gU2ljaGVyaGVpdHNzeXN0ZW1lIGltIGVsZWt0ci4gRGF0ZW52ZXJrZWhyIEdtYkgxIzAhBgNVBAsMGmEtc2lnbi1QcmVtaXVtLVRlc3QtU2lnLTAyMSMwIQYDVQQDDBphLXNpZ24tUHJlbWl1bS1UZXN0LVNpZy0wMjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBANwJSfWpRaziThddTTup72CltlXl8oc7HQoK2SWsYQwZGAd5nJZbwbI4K8VFKlNnK72Zl8UhmQ2FxhzS6u+Q+qEzJOM2xTfA2NB6A9/KFpTJXUjvCHgRvW16EEF9YpYXxKTSK+QrYCXAC5rL6SuYOzgA7Q1ivq+zLbyXxroux2zVEBIiaBGpZhOHGDFJk6h/4QelIqzd2TIDCRzvhmLDVmhqX2C1NQb5kZuMgrxxOhG5Cr1F8solkwyu43JiM+apY4bZJVQBwi9ATBMz5tfdoLRslQy1BCQ4X+b6u/2856gucU+1e/wa5pB9Ff0eP+xy+j2DZOXLNd8m/IQvnshjNusCAwEAAaNLMEkwDwYDVR0TAQH/BAUwAwEB/zARBgNVHQ4ECgQIRgafjkGOFb0wEwYDVR0jBAwwCoAIQg8xWXA9iecwDgYDVR0PAQH/BAQDAgEGMA0GCSqGSIb3DQEBBQUAA4IBAQBq/owq5eGvhxegchLvnMjPnE9gTYIHEvMq8DN6h2J7pTEhKG2o09LLn/pNHWRjKENU/LqZBIAJ5zebm5XqzB631BYcuu1abyPFfpMdAL9X4zFuDvg9EGaTir2c81XaBYzVSLN7fxmNLKSmMwUt0JQQyqpe3V9iyoBE/WcQyKmKaEp7mCZsGFBm6KmJgqD6TPb7X9bWUr3yx6Z5gek71f3vQi69m1x811azXlxu1i/XFnVpzxkrKRXJWC+wnQRxXmU7YnMzYPOA7UOpUG6J+7tYi29hY3EpMgyXM/T/BL5MdyzBefbPVzLHng5zVaXNpO0ENCrlUyi1m3Yd/7QPDdJM",
                depExport.getBelegeGruppe().get(0).getZertifizierungsstellen().get(0));
            assertEquals(
                "MIID4DCCAsigAwIBAgIEOWntvzANBgkqhkiG9w0BAQUFADCBlTELMAkGA1UEBhMCQVQxSDBGBgNVBAoMP0EtVHJ1c3QgR2VzLiBmLiBTaWNoZXJoZWl0c3N5c3RlbWUgaW0gZWxla3RyLiBEYXRlbnZlcmtlaHIgR21iSDEdMBsGA1UECwwUQS1UcnVzdC1UZXN0LVF1YWwtMDIxHTAbBgNVBAMMFEEtVHJ1c3QtVGVzdC1RdWFsLTAyMB4XDTE0MTEyNDE0NDc0N1oXDTI0MTExODEzNDc0N1owgZUxCzAJBgNVBAYTAkFUMUgwRgYDVQQKDD9BLVRydXN0IEdlcy4gZi4gU2ljaGVyaGVpdHNzeXN0ZW1lIGltIGVsZWt0ci4gRGF0ZW52ZXJrZWhyIEdtYkgxHTAbBgNVBAsMFEEtVHJ1c3QtVGVzdC1RdWFsLTAyMR0wGwYDVQQDDBRBLVRydXN0LVRlc3QtUXVhbC0wMjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBANMBok2fNNtIEcf7Sw47vprkUeti6Y64Rc5rrAjh7cGwo4Jp5LyfvEVdv9AMNiuOX7ywd1xW99UZWtZ8MzXvWM5M6trLkeBYnCukwc9DqawXcuXXCYwgTuisFTmYO6GVJNr1iE/LJdSKbu5AVDS3FwXixqyJkjv/xWIwU4q86oATW8++8wb6Lu+fQlhBbn3Kqpavt6K+lwWSCb+8vIhB47IlKhJZwGqXfGV9l9dDgKYUbZiv3BBa+MRBUTvIcahEKz8hG2E8W4EgCwzISMpeStJtRHo/tJnA90KfSBTcz0txrxpHwqFgKwJvgW6nIjY1Sv5MfY5YJiEWv0d7UUkvlScCAwEAAaM2MDQwDwYDVR0TAQH/BAUwAwEB/zARBgNVHQ4ECgQIQg8xWXA9iecwDgYDVR0PAQH/BAQDAgEGMA0GCSqGSIb3DQEBBQUAA4IBAQApqSvkQyfbO2yDWewHwo1Zl32uGz41KMP5FYtA3BIcqh89paHwrW9KfcrybdUIneVz4iSnpyrDrS4LavfP8h/Hl1kRmVZRUBsOJRvqc1fiC2B6IJRHrmayb/DbXuyoOsk7Sr8M9xtAD3SzJCRkBrtjz/U/xQdU9TfV9SQyPN3qI+SR25/LRZDhOKcIFJduVpTYzbnKTIkl3OUrHXVq5xddxX6XP8bUjT+SqGiDf15H6N5flNBsvolMSo0OoQXFiDuY33frQSrSbHbA2p/MptwxA8JgGh4lrbgZZxjTvpO1wATBLDc3wGZkNuy+tNrrHAmE08B7fiExULHxzfaZEWSF",
                depExport.getBelegeGruppe().get(0).getZertifizierungsstellen().get(1));
            assertEquals("xxx.jws123.sss", depExport.getBelegeGruppe().get(0).getBelegeKompakt().get(0));
            assertEquals("xxx.jws123.sss", depExport.getBelegeGruppe().get(0).getBelegeKompakt().get(1));
            assertEquals("xxx.jws456.sss", depExport.getBelegeGruppe().get(0).getBelegeKompakt().get(2));
        }

        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
    }
}

package org.regkas.service.core.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

import javax.enterprise.context.Dependent;
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
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.CashboxJournalRepository;
import org.regkas.domain.api.repository.ProductRepository;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.repository.SystemJournalRepository;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.Period;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.DateTimeHelperMock;
import org.regkas.service.core.email.MailSenderFactory;
import org.regkas.service.core.email.MailSenderMock;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderFactory;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderMock;
import org.regkas.service.core.receipt.RkOnlineResourceSessionThrowingException;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
@RunWith(Arquillian.class)
public class SzenarioOwn01Test extends EntityManagerProviderForRegkas {

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private UserContext userContext;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private MailSenderFactory mailSenderFactory;

    @Inject
    private FinancialOfficeSenderFactory financialOfficeSenderFactory;

    private MailSenderMock mailSenderMock = new MailSenderMock();

    private FinancialOfficeSenderMock financialOfficeSenderMock = new FinancialOfficeSenderMock();

    @Inject
    private Assertion assertion;

    @Inject
    private SalePerformer salePerformer;

    @Inject
    private CashboxJournalRepository cashboxJournalRepository;

    @Inject
    private SystemJournalRepository systemJournalRepository;

    @Inject
    private JournalService journalService;

    @Inject
    private QRCodesCreator qrCodesCreator;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Before
    public void before() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("CASHBOX-DEMO-1")).get();
        cashBoxContext.set(cashBox);
        companyContext.set(cashBox.getCompany());
        userContext.set(cashBox.getCompany().getUsers().iterator().next());
        rkOnlineContext.setEnvironment(Environment.TEST);
        mailSenderMock.reset();
        mailSenderFactory.setMailSender(mailSenderMock);
        financialOfficeSenderMock.reset();
        financialOfficeSenderFactory.setFinancialOfficeSender(financialOfficeSenderMock);
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
        rkOnlineContext.setEnvironment(Environment.TEST);
        rkOnlineContext.resetSessions();
    }

    @After
    public void after() {
        dateTimeHelper.reset();
        rkOnlineContext.resetSessions();
        mailSenderMock.reset();
        financialOfficeSenderMock.reset();
    }

    @Test
    @Transactional
    public void testSzenarioOwn01() throws Exception {
        assertEquals(0, systemJournalRepository.loadAll().size());
        assertEquals(0, cashboxJournalRepository.loadBy(cashBoxContext.get()).size());
        List<BillBean> billBeans = new ArrayList<>();
        assertion.assertReceiptCount(0);
        assertion.assertTurnover(0);

        salePerformer.sale001Standard();
        salePerformer.sale002Training();
        billBeans.add(salePerformer.sale003Start());
        billBeans.add(salePerformer.sale004Standard());
        billBeans.add(salePerformer.sale005Standard());
        billBeans.add(salePerformer.sale006Null());
        billBeans.add(salePerformer.sale007Tag());
        billBeans.add(salePerformer.sale010Standard());
        billBeans.add(salePerformer.sale011StandardSSEDamaged());
        billBeans.add(salePerformer.sale012Standard());
        billBeans.add(salePerformer.sale013StandardSSEAvailable());
        billBeans.add(salePerformer.sale014Standard());
        billBeans.add(salePerformer.sale015Storno());
        billBeans.add(salePerformer.sale016Training());
        billBeans.add(salePerformer.sale017Tag());
        billBeans.add(salePerformer.sale020Standard());
        billBeans.add(salePerformer.sale021Training());
        billBeans.add(salePerformer.sale022Storno());
        billBeans.add(salePerformer.sale023StandardSSEAvailable());
        billBeans.add(salePerformer.sale024Tag());
        billBeans.add(salePerformer.sale025Monat());

        assertion.assertReceiptCount(21);
        assertion.assertTurnover(11100);
        assertEquals(19, billBeans.size());
        assertEquals(11, mailSenderMock.getMailSendList().size());
        assertEquals(0, systemJournalRepository.loadAll().size());
        assertEquals(6, cashboxJournalRepository.loadBy(cashBoxContext.get()).size());
        billBeans.forEach(b -> System.out.println(b.getJwsCompact()));
        mailSenderMock.getMailSendList().forEach(System.out::println);
        cashboxJournalRepository.loadBy(cashBoxContext.get()).forEach(System.out::println);

        ZipFile datenErfassungsProtokollRKSV = new ZipFile(journalService.datenErfassungsProtokollRKSV());
        File exportedQRs = qrCodesCreator.exportQRs(
            receiptRepository
                .loadBy(Period.untilNow(), cashBoxContext.get())
                .stream()
                .map(r -> r.getCompactJwsRepresentation().getMachineReadableRepresentation())
                .collect(Collectors.toList()));
        assertion.assertBmfVerification(datenErfassungsProtokollRKSV, exportedQRs);
    }

    @Dependent
    private static class SalePerformer {

        @Inject
        private SaleService saleService;

        @Inject
        private SaleCreator saleCreator;

        @Inject
        private ProductCreator productCreator;

        @Inject
        private Assertion assertion;

        @Inject
        private DateTimeHelperMock dateTimeHelper;

        @Inject
        private RkOnlineContext rkOnlineContext;

        @Inject
        private RkOnlineResourceFactory rkOnlineResourceFactory;

        // @Inject
        // private ReceiptRepository receiptRepository;
        //
        // @Inject
        // private CashBoxContext cashBoxContext;

        private void sale001Standard() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 1, 12, 0, 0));
            try {
                saleService.sale(saleCreator.createStandardSale(productCreator.newStandardProduct(1, new BigDecimal("10.00"))));
                fail("start-receipt must be created first!");
            } catch (RuntimeException e) {
                assertion.assertReceiptCount(0);
                assertion.assertTurnover(0);
            }
        }

        private void sale002Training() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 1, 12, 1, 0));
            try {
                saleService.sale(saleCreator.createTrainingSale(productCreator.newErmeaessigt1Product(2, new BigDecimal("5.00"))));
                fail("start-receipt must be created first!");
            } catch (RuntimeException e) {
                assertion.assertReceiptCount(0);
                assertion.assertTurnover(0);
            }
        }

        private BillBean sale003Start() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 1, 12, 2, 0));
            BillBean bill = saleService.sale(saleCreator.createStartSale());
            assertion.assertReceiptCount(1);
            assertion.assertTurnover(0);
            return bill;
        }

        private BillBean sale004Standard() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 1, 12, 3, 0));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(productCreator.newStandardProduct(1, new BigDecimal("10.00"))));
            assertion.assertReceiptCount(2);
            assertion.assertTurnover(1000);
            return bill;
        }

        private BillBean sale005Standard() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 1, 12, 4, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(1, new BigDecimal("10.00"));
            ReceiptElementBean receiptElementBean2 = productCreator.newStandardProduct(1, new BigDecimal("5.00"));
            ReceiptElementBean receiptElementBean3 = productCreator.newErmeaessigt1Product(3, new BigDecimal("6.00"));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(receiptElementBean1, receiptElementBean2, receiptElementBean3));
            assertion.assertReceiptCount(3);
            assertion.assertTurnover(3100);
            return bill;
        }

        private BillBean sale006Null() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 1, 12, 10, 0));
            BillBean bill = saleService.sale(saleCreator.createNullSale());
            assertion.assertReceiptCount(4);
            assertion.assertTurnover(3100);
            return bill;
        }

        private BillBean sale007Tag() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 1, 19, 0, 0));
            BillBean bill = saleService.sale(saleCreator.createTagSale());
            assertion.assertReceiptCount(5);
            assertion.assertTurnover(3100);
            return bill;
        }

        private BillBean sale010Standard() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 2, 12, 0, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(2, new BigDecimal("20.00"));
            ReceiptElementBean receiptElementBean2 = productCreator.newStandardProduct(5, new BigDecimal("5.00"));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(receiptElementBean1, receiptElementBean2));
            assertion.assertReceiptCount(6);
            assertion.assertTurnover(5600);
            return bill;
        }

        private BillBean sale011StandardSSEDamaged() {
            sseDamaged();
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 2, 12, 5, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(1, new BigDecimal("10.00"));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(receiptElementBean1));
            assertion.assertReceiptCount(7);
            assertion.assertTurnover(6600);
            return bill;
        }

        private BillBean sale012Standard() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 2, 12, 7, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(1, new BigDecimal("10.00"));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(receiptElementBean1));
            assertion.assertReceiptCount(8);
            assertion.assertTurnover(7600);
            return bill;
        }

        private BillBean sale013StandardSSEAvailable() {
            sseAvailableAgain();
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 2, 12, 10, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(4, new BigDecimal("4.00"));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(receiptElementBean1));
            assertion.assertReceiptCount(10);
            assertion.assertTurnover(8000);
            return bill;
        }

        private BillBean sale014Standard() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 2, 12, 15, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(4, new BigDecimal("20.00"));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(receiptElementBean1));
            assertion.assertReceiptCount(11);
            assertion.assertTurnover(10000);
            return bill;
        }

        private BillBean sale015Storno() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 2, 12, 20, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(2, new BigDecimal("-10.00"));
            BillBean bill = saleService.sale(saleCreator.createStornoSale(receiptElementBean1));
            assertion.assertReceiptCount(12);
            assertion.assertTurnover(9000);
            return bill;
        }

        private BillBean sale016Training() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 2, 12, 25, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(2, new BigDecimal("50.00"));
            BillBean bill = saleService.sale(saleCreator.createTrainingSale(receiptElementBean1));
            assertion.assertReceiptCount(13);
            assertion.assertTurnover(9000);
            return bill;
        }

        private BillBean sale017Tag() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 2, 12, 30, 0));
            BillBean bill = saleService.sale(saleCreator.createTagSale());
            assertion.assertReceiptCount(14);
            assertion.assertTurnover(9000);
            return bill;
        }

        private BillBean sale020Standard() {
            sseDamaged();
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 3, 12, 0, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(5, new BigDecimal("10.00"));
            ReceiptElementBean receiptElementBean2 = productCreator.newStandardProduct(4, new BigDecimal("2.00"));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(receiptElementBean1, receiptElementBean2));
            assertion.assertReceiptCount(15);
            assertion.assertTurnover(10200);
            return bill;
        }

        private BillBean sale021Training() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 3, 12, 5, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(2, new BigDecimal("50.00"));
            BillBean bill = saleService.sale(saleCreator.createTrainingSale(receiptElementBean1));
            assertion.assertReceiptCount(16);
            assertion.assertTurnover(10200);
            return bill;
        }

        private BillBean sale022Storno() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 3, 12, 10, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(2, new BigDecimal("-2.00"));
            BillBean bill = saleService.sale(saleCreator.createStornoSale(receiptElementBean1));
            assertion.assertReceiptCount(17);
            assertion.assertTurnover(10000);
            return bill;
        }

        private BillBean sale023StandardSSEAvailable() {
            sseAvailableAgain();
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 3, 12, 15, 0));
            ReceiptElementBean receiptElementBean1 = productCreator.newStandardProduct(2, new BigDecimal("11.00"));
            BillBean bill = saleService.sale(saleCreator.createStandardSale(receiptElementBean1));
            assertion.assertReceiptCount(19);
            assertion.assertTurnover(11100);
            return bill;
        }

        private BillBean sale024Tag() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 3, 12, 20, 0));
            BillBean bill = saleService.sale(saleCreator.createTagSale());
            assertion.assertReceiptCount(20);
            assertion.assertTurnover(11100);
            return bill;
        }

        private BillBean sale025Monat() {
            dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 3, 12, 25, 0));
            BillBean bill = saleService.sale(saleCreator.createMonatSale());
            assertion.assertReceiptCount(21);
            assertion.assertTurnover(11100);
            return bill;
        }

        private void sseDamaged() {
            rkOnlineContext.resetSessions();
            rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());
        }

        private void sseAvailableAgain() {
            rkOnlineContext.resetSessions();
            rkOnlineResourceFactory.resetRkOnlineResourceSession();
        }
    }

    @Dependent
    private static class SaleCreator {

        private SaleBean createStandardSale(ReceiptElementBean... receiptElementBeans) {
            return createSale("Standard-Beleg", receiptElementBeans);
        }

        private SaleBean createTrainingSale(ReceiptElementBean... receiptElementBeans) {
            return createSale("Training-Beleg", receiptElementBeans);
        }

        private SaleBean createStartSale() {
            return createSale("Start-Beleg");
        }

        private SaleBean createNullSale() {
            return createSale("Null-Beleg");
        }

        private SaleBean createTagSale() {
            return createSale("Tages-Beleg");
        }

        private SaleBean createMonatSale() {
            return createSale("Monats-Beleg");
        }

        private SaleBean createStornoSale(ReceiptElementBean... receiptElementBeans) {
            return createSale("Storno-Beleg", receiptElementBeans);
        }

        private SaleBean createSale(String receiptType, ReceiptElementBean... receiptElementBeans) {
            SaleBean saleBean = new SaleBean();
            saleBean.setPaymentMethod("cash");
            saleBean.setReceiptType(receiptType);
            saleBean.getSaleElements().addAll(Arrays.asList(receiptElementBeans));
            return saleBean;
        }
    }

    @Dependent
    private static class ProductCreator {

        @Inject
        private ProductRepository productRepository;

        @Inject
        private CashBoxContext cashBoxContext;

        private ReceiptElementBean newStandardProduct(int amount, BigDecimal totalPrice) {
            return newProduct(amount, totalPrice, this::getProductStandard);
        }

        private ReceiptElementBean newErmeaessigt1Product(int amount, BigDecimal totalPrice) {
            return newProduct(amount, totalPrice, this::getProductErmaessigt1);
        }

        private ReceiptElementBean newProduct(int amount, BigDecimal totalPrice, Supplier<ProductBean> productBeanSupplier) {
            ReceiptElementBean receiptElementBean = new ReceiptElementBean();
            receiptElementBean.setProduct(productBeanSupplier.get());
            receiptElementBean.setAmount(amount);
            receiptElementBean.setTotalPrice(totalPrice);
            return receiptElementBean;
        }

        private ProductBean getProductStandard() {
            return getProduct("Standard");
        }

        private ProductBean getProductErmaessigt1() {
            return getProduct("Ermaessigt1");
        }

        private ProductBean getProduct(String productName) {
            return productRepository.loadBy(new Name(productName), cashBoxContext.get()).get().asDto();
        }
    }
}

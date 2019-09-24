package org.regkas.service.core;

import org.boatpos.common.model.PaymentMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.builder.ReceiptBuilder;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.context.CompanyContext;
import org.regkas.domain.api.context.UserContext;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.CompanyRepository;
import org.regkas.domain.api.repository.ReceiptTypeRepository;
import org.regkas.domain.api.repository.UserRepository;
import org.regkas.domain.api.values.DEPString;
import org.regkas.domain.api.values.EncryptedTurnoverValue;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptDate;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.domain.api.values.SignatureValuePreviousReceipt;
import org.regkas.domain.api.values.SuiteId;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.service.api.ReceiptService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "ConstantConditions"})
@RunWith(Arquillian.class)
public class ReceiptServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptService receiptService;

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
    private ReceiptBuilder receiptBuilder;

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    @Transactional
    public void testIsStartReceiptCreated() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertTrue(receiptService.isStartReceiptCreated());
    }

    @Test
    @Transactional
    public void testIsSchlussReceiptCreated() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertFalse(receiptService.isSchlussReceiptCreated());
    }

    @Test
    @Transactional
    public void testShouldCreateMonthReceipt() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        ReceiptType receiptTypeMonat = receiptTypeRepository.loadBy(new Name("Monats-Beleg")).get();

        assertFalse(receiptService.shouldCreateMonthReceipt());

        insertReceipt(receiptTypeMonat, new ReceiptId("2015-0000003"), new ReceiptDate(LocalDateTime.of(2015, 7, 31, 12, 0, 0)));
        dateTimeHelper.setTime(LocalDateTime.of(2015, 8, 1, 12, 0, 0));
        assertTrue(receiptService.shouldCreateMonthReceipt());
        dateTimeHelper.setTime(LocalDateTime.of(2015, 8, 31, 12, 0, 0));
        assertTrue(receiptService.shouldCreateMonthReceipt());

        insertReceipt(receiptTypeMonat, new ReceiptId("2015-0000004"), new ReceiptDate(LocalDateTime.of(2015, 8, 1, 12, 0, 0)));
        dateTimeHelper.setTime(LocalDateTime.of(2015, 8, 31, 12, 0, 0));
        assertFalse(receiptService.shouldCreateMonthReceipt());
        dateTimeHelper.setTime(LocalDateTime.of(2015, 9, 1, 12, 0, 0));
        assertTrue(receiptService.shouldCreateMonthReceipt());
        dateTimeHelper.resetTime();
    }

    @Test
    @Transactional
    public void testShouldCreateDayReceipt() throws Exception {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        ReceiptType receiptTypeTag = receiptTypeRepository.loadBy(new Name("Tages-Beleg")).get();

        assertFalse(receiptService.shouldCreateDayReceipt());

        insertReceipt(receiptTypeTag, new ReceiptId("2015-0000003"), new ReceiptDate(LocalDateTime.of(2015, 7, 2, 12, 0, 0)));
        dateTimeHelper.setTime(LocalDateTime.of(2015, 7, 3, 12, 0, 0));
        assertTrue(receiptService.shouldCreateDayReceipt());
        dateTimeHelper.setTime(LocalDateTime.of(2015, 7, 4, 12, 0, 0));
        assertTrue(receiptService.shouldCreateDayReceipt());

        insertReceipt(receiptTypeTag, new ReceiptId("2015-0000004"), new ReceiptDate(LocalDateTime.of(2015, 7, 5, 12, 0, 0)));
        dateTimeHelper.setTime(LocalDateTime.of(2015, 7, 5, 12, 0, 0));
        assertFalse(receiptService.shouldCreateDayReceipt());
        dateTimeHelper.setTime(LocalDateTime.of(2015, 7, 6, 12, 0, 0));
        assertTrue(receiptService.shouldCreateDayReceipt());
        dateTimeHelper.resetTime();
    }

    @Test
    @Transactional
    public void testGetSetEnvironment() {
        receiptService.setRkOnlineEnvironment("prod");
        assertEquals("prod", receiptService.getCurrentRkOnlineEnvironment());
        receiptService.setRkOnlineEnvironment("test");
        assertEquals("test", receiptService.getCurrentRkOnlineEnvironment());
    }

    private void insertReceipt(ReceiptType receiptType, ReceiptId receiptId, ReceiptDate receiptDate) {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Company company = companyRepository.loadBy(new Name("company")).get();
        User user = userRepository.loadBy(new Name("Maria Musterfrau")).get();
        receiptBuilder
                .add(receiptId)
                .add(company)
                .add(cashBox)
                .add(user)
                .add(receiptType)
                .add(receiptDate)
                .add(new TotalPrice("11.00"))
                .add(new DEPString("dep"))
                .add(PaymentMethod.CASH)
                .add(new EncryptedTurnoverValue(""))
                .add(new SignatureValuePreviousReceipt(""))
                .add(new SuiteId("R1-AT0"))
                .build()
                .persist();
    }

    @Test
    @Transactional
    public void testGetReceiptById() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        BillBean billBean = receiptService.getReceiptById("2015-0000002");
        assertEquals("2015-0000002", billBean.getReceiptIdentifier());
        assertEquals("RegKas1", billBean.getCashBoxID());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testGetReceiptByIdNotAvailable() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        receiptService.getReceiptById("2999-0000000");
    }
}

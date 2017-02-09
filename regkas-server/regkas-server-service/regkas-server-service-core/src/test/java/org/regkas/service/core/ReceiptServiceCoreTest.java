package org.regkas.service.core;

import org.boatpos.common.model.PaymentMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.*;
import org.regkas.service.api.ReceiptService;
import org.regkas.service.core.context.CashBoxContext;
import org.regkas.service.core.context.CompanyContext;
import org.regkas.service.core.context.UserContext;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("OptionalGetWithoutIsPresent")
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
        ReceiptType receiptTypeStart = receiptTypeRepository.loadBy(new Name("Start-Beleg")).get();

        assertFalse(receiptService.isStartReceiptCreated());

        insertReceipt(receiptTypeStart, new ReceiptId("2015-0000000"), new ReceiptDate(LocalDateTime.of(2015, 6, 1, 12, 0, 0)));
        assertTrue(receiptService.isStartReceiptCreated());
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
                .build()
                .persist();
    }
}
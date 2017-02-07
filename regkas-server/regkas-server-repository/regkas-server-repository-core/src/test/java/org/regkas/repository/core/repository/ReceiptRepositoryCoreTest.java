package org.regkas.repository.core.repository;

import org.boatpos.common.model.PaymentMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.model.TimeType;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.repository.*;
import org.regkas.repository.api.values.*;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.service.api.bean.Period;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class ReceiptRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private ReceiptBuilder receiptBuilder;

    @Test
    @Transactional
    public void testLoadByPeriod() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(2, receiptRepository.loadBy(Period.day(dateTimeHelper.currentTime()), cashBox).size());
        assertEquals(2, receiptRepository.loadBy(Period.month(dateTimeHelper.currentTime()), cashBox).size());
        assertEquals(0, receiptRepository.loadBy(Period.day(LocalDateTime.of(1970, 1, 1, 0, 0)), cashBox).size());
    }

    @Test
    @Transactional
    public void testLoadLastReceipt() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("2015-0000002", receiptRepository.loadLastReceipt(cashBox).get().getReceiptId().get());
    }

    @Test
    @Transactional
    public void tesLoadByReceiptId() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("2015-0000002", receiptRepository.loadBy(new ReceiptId("2015-0000002"), cashBox).get().getReceiptId().get());
    }

    @Test
    @Transactional
    public void tesLoadDEPFor() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(2, receiptRepository.loadDEPFor(Period.day(dateTimeHelper.currentTime()), cashBox).size());
    }

    @Test
    @Transactional
    public void testLoadAllWithoutDEP() {
        assertEquals(0, receiptRepository.loadAllWithoutDEP().size());

        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Optional<Receipt> receipt = receiptRepository.loadBy(new ReceiptId("2015-0000002"), cashBox);
        receipt.get().setDEP(new DEPString("")).persist();
        assertEquals(1, receiptRepository.loadAllWithoutDEP().size());

        receipt.get().setDEP(null).persist();
        assertEquals(1, receiptRepository.loadAllWithoutDEP().size());
    }

    @Test
    @Transactional
    public void testLoadLatestByReceiptTypeStart() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertFalse(receiptRepository.loadLatestWithReceiptTypeStart(cashBox).isPresent());

        ReceiptType receiptTypeStart = receiptTypeRepository.loadBy(new Name("Start-Beleg")).get();
        insertReceipt(receiptTypeStart, new ReceiptId("2015-0000000"), new ReceiptDate(LocalDateTime.of(2015, 6, 1, 12, 0, 0)));
        assertTrue(receiptRepository.loadLatestWithReceiptTypeStart(cashBox).isPresent());
    }

    @Test
    @Transactional
    public void testLoadLatestByReceiptTypeJahr() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertFalse(receiptRepository.loadLatestWithReceiptTypeJahr(cashBox).isPresent());

        ReceiptType receiptTypeJahr = receiptTypeRepository.loadBy(new Name("Jahres-Beleg")).get();
        insertReceipt(receiptTypeJahr, new ReceiptId("2015-0000003"), new ReceiptDate(LocalDateTime.of(2015, 12, 31, 12, 0, 0)));
        assertTrue(receiptRepository.loadLatestWithReceiptTypeJahr(cashBox).isPresent());
    }

    @Test
    @Transactional
    public void testLoadLatestByReceiptTypeMonat() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertFalse(receiptRepository.loadLatestWithReceiptTypeMonat(cashBox).isPresent());

        ReceiptType receiptTypeMonat = receiptTypeRepository.loadBy(new Name("Monats-Beleg")).get();
        insertReceipt(receiptTypeMonat, new ReceiptId("2015-0000003"), new ReceiptDate(LocalDateTime.of(2015, 8, 31, 12, 0, 0)));
        insertReceipt(receiptTypeMonat, new ReceiptId("2015-0000004"), new ReceiptDate(LocalDateTime.of(2015, 9, 30, 12, 0, 0)));
        Optional<Receipt> latestReceiptMonat = receiptRepository.loadLatestWithReceiptTypeMonat(cashBox);
        assertTrue(latestReceiptMonat.isPresent());
        assertEquals("2015-0000004", latestReceiptMonat.get().getReceiptId().get());
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
                .add(TimeType.Current)
                .build()
                .persist();
    }
}

package org.regkas.domain.core.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.boatpos.common.model.PaymentMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.builder.ReceiptBuilder;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.CompanyRepository;
import org.regkas.domain.api.repository.ReceiptRepository;
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
import org.regkas.domain.core.DateTimeHelperMock;
import org.regkas.service.api.bean.Period;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "ConstantConditions"})
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
        assertEquals(3, receiptRepository.loadBy(Period.day(dateTimeHelper.currentTime()), cashBox).size());
        assertEquals(3, receiptRepository.loadBy(Period.month(dateTimeHelper.currentTime()), cashBox).size());
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
        assertEquals(3, receiptRepository.loadDEPFor(Period.day(dateTimeHelper.currentTime()), cashBox).size());
    }

    @Test
    @Transactional
    public void testLoadAllWithoutDEP() {
        assertEquals(2, receiptRepository.loadAllWithoutDEP().size());

        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Optional<Receipt> receipt = receiptRepository.loadBy(new ReceiptId("2015-0000002"), cashBox);
        receipt.get().setDEP(new DEPString("")).persistWithoutCreatingDEP();
        assertEquals(3, receiptRepository.loadAllWithoutDEP().size());

        receipt.get().setDEP(null).persistWithoutCreatingDEP();
        assertEquals(3, receiptRepository.loadAllWithoutDEP().size());
    }

    @Test
    @Transactional
    public void testLoadLatestByReceiptTypeStart() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertTrue(receiptRepository.loadLatestWithReceiptTypeStart(cashBox).isPresent());
    }

    @Test
    @Transactional
    public void testLoadLatestByReceiptTypeSchluss() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertFalse(receiptRepository.loadLatestWithReceiptTypeSchluss(cashBox).isPresent());
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
            .add(new SuiteId("R1-AT0"))
            .build()
            .persist();
    }

    @Test
    @Transactional
    public void testLoadCompactJWSRepresentationsWithSignatureDeviceAvailable() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        List<String> compactJwsRepresentations = receiptRepository
            .loadCompactJWSRepresentationsWithSignatureDeviceAvailable(Period.untilNow(), cashBox);
        assertEquals(3, compactJwsRepresentations.size());
        assertEquals("xxx.jws123.sss", compactJwsRepresentations.get(1));
        assertEquals("xxx.jws456.sss", compactJwsRepresentations.get(2));
    }

    @Test
    @Transactional
    public void testLoadCompactJWSRepresentationsWithSignatureDeviceNotAvailable() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        List<String> compactJwsRepresentations = receiptRepository
            .loadCompactJWSRepresentationsWithSignatureDeviceNotAvailable(Period.untilNow(), cashBox);
        assertEquals(0, compactJwsRepresentations.size());
    }

    @Test
    @Transactional
    public void testLoadLastWithSignatureDeviceNotAvailable() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertFalse(receiptRepository.loadLastWithSignatureDeviceNotAvailable(cashBox).isPresent());
    }

    @Test
    @Transactional
    public void testLoadLastWithSignatureDeviceAvailable() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(
            new ReceiptId("2015-0000002"),
            receiptRepository.loadLastWithSignatureDeviceAvailableBefore(dateTimeHelper.currentTime(), cashBox).get().getReceiptId());
    }

    @Test
    @Transactional
    public void testLoadFirstWhereSignatureDeviceIsNotAvailableAfter() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertFalse(receiptRepository.loadFirstWhereSignatureDeviceIsNotAvailableAfter(dateTimeHelper.currentTime(), cashBox).isPresent());
    }

    @Test
    @Transactional
    public void testLoadCompactJWSRepresentations() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        List<String> compactJwsRepresentations = receiptRepository.loadCompactJWSRepresentations(Period.untilNow(), cashBox);
        assertEquals(3, compactJwsRepresentations.size());
    }
}

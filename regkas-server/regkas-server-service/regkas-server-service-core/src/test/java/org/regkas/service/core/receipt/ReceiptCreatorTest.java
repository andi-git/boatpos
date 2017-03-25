package org.regkas.service.core.receipt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptTypeJahr;
import org.regkas.domain.api.model.ReceiptTypeMonat;
import org.regkas.domain.api.model.ReceiptTypeSammel;
import org.regkas.domain.api.model.ReceiptTypeTag;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.CompanyRepository;
import org.regkas.domain.api.repository.UserRepository;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "FieldCanBeLocal", "ConstantConditions"})
@RunWith(Arquillian.class)
public class ReceiptCreatorTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptCreator receiptCreator;

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
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private FirstSale firstSale;

    @SuppressWarnings("Duplicates")
    @Before
    public void before() {
        System.setProperty("boatpos.crypto.complement.rep.bytes", "8");
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
        rkOnlineContext.setEnvironment(Environment.TEST);
        rkOnlineContext.resetSessions();
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
    }

    @After
    public void after() {
        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
        System.clearProperty("boatpos.crypto.complement.rep.bytes");
    }

    @Test
    @Transactional
    public void testCreateReceipt() throws Exception {
        assertEquals(1300, cashBoxContext.get().getTurnoverCountCent().get().intValue());

        Receipt receipt = receiptCreator.createReceipt(firstSale.createDefaultSale());
        firstSale.assertEqualsWhenSignatureDeviceIsAvailable(receipt);
        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());

        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
    }

    @Test
    @Transactional
    public void testSaleWhenSignatureDeviceIsNotAvailable() throws Exception {
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());

        Receipt receipt = receiptCreator.createReceipt(firstSale.createDefaultSale());
        firstSale.assertEqualsWhenSignatureDeviceIsNotAvailable(receipt);
        assertEquals(2750, cashBoxRepository.loadBy(new Name("RegKas1")).get().getTurnoverCountCent().get().intValue());

        rkOnlineResourceFactory.resetRkOnlineResourceSession();
    }

    @Test
    @Transactional
    public void testCreateSammelSale() {
        assertTrue(receiptCreator.createSammelReceipt().getReceiptType() instanceof ReceiptTypeSammel);
    }

    @Test
    @Transactional
    public void testCreateDaySale() {
        assertTrue(receiptCreator.createDayReceipt().getReceiptType() instanceof ReceiptTypeTag);
    }

    @Test
    @Transactional
    public void testCreateMonthSale() {
        assertTrue(receiptCreator.createMonthReceipt().getReceiptType() instanceof ReceiptTypeMonat);
    }

    @Test
    @Transactional
    public void testCreateYearSale() {
        assertTrue(receiptCreator.createYearReceipt().getReceiptType() instanceof ReceiptTypeJahr);
    }

}

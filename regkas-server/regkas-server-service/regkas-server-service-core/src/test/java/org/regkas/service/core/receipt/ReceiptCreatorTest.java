package org.regkas.service.core.receipt;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.context.CompanyContext;
import org.regkas.repository.api.context.UserContext;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.signature.RkOnlineResourceFactory;
import org.regkas.repository.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "FieldCanBeLocal"})
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
}

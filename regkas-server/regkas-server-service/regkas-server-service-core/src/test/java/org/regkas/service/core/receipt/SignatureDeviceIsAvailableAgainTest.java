package org.regkas.service.core.receipt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.core.DateTimeHelperMock;

import java.time.LocalDateTime;

@RunWith(Arquillian.class)
public class SignatureDeviceIsAvailableAgainTest extends HandleSignatureDeviceAvailabilityTest {

    @Inject
    private SignatureDeviceIsAvailableAgain signatureDeviceIsAvailableAgain;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private SaleService saleService;

    @Inject
    private FirstSale firstSale;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Override
    @Before
    public void before() {
        super.before();
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
    }

    @Override
    @After
    public void after() {
        super.after();
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
        dateTimeHelper.resetTime();
    }

    @Test
    @Transactional
    public void testCanHandle() throws Exception {
        assertTrue(signatureDeviceIsAvailableAgain.canHandle(receiptSignatureDeviceAvailable, receiptSignatureDeviceNotAvailable));
        assertFalse(signatureDeviceIsAvailableAgain.canHandle(receiptSignatureDeviceNotAvailable, receiptSignatureDeviceAvailable));
        assertFalse(signatureDeviceIsAvailableAgain.canHandle(receiptSignatureDeviceAvailable, receiptSignatureDeviceAvailable));
        assertFalse(signatureDeviceIsAvailableAgain.canHandle(receiptSignatureDeviceNotAvailable, receiptSignatureDeviceNotAvailable));
    }

    @Test
    @Transactional
    public void testHandle() throws Exception {
        dateTimeHelper.setTime(LocalDateTime::now);
        rkOnlineResourceFactory.setRkOnlineResourceSession(new RkOnlineResourceSessionThrowingException());
        saleService.sale(firstSale.createDefaultSale());
        financialOfficeSenderMock.reset();

        BillBean billBean = new BillBean();
        billBean = signatureDeviceIsAvailableAgain.handle(billBean);
        assertNotNull(billBean.getSammelBeleg());
        assertTrue(mailSenderMock.isSendCalled());
        assertTrue(financialOfficeSenderMock.isSignatureDeviceIsAvailableAgainCalled());
        assertFalse(financialOfficeSenderMock.isSignatureDeviceIsNotLongerAvailableCalled());
    }
}

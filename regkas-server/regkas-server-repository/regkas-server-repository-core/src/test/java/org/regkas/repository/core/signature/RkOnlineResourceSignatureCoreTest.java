package org.regkas.repository.core.signature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.signature.RkOnlineResourceFactory;
import org.regkas.repository.api.signature.RkOnlineResourceSignature;
import org.regkas.repository.api.signature.SignatureDeviceNotAvailableException;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.RkOnlineSession;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.repository.core.crypto.Encoding;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class RkOnlineResourceSignatureCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private RkOnlineResourceSignature rkOnlineResourceSignature;

    @Inject
    private Encoding encoding;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Before
    public void before() {
        rkOnlineContext.resetSessions();
        rkOnlineContext.setEnvironment(Environment.TEST);
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("DEMO-CASH-BOX817")));
        dateTimeHelper.setTime(LocalDateTime::now);
    }

    @After
    public void after() {
        dateTimeHelper.resetTime();
        rkOnlineContext.resetSessions();
        rkOnlineResourceFactory.resetRkOnlineResourceSession();
        rkOnlineResourceFactory.resetRkOnlineResourceSignature();
    }

    @Test
    @Transactional
    public void testSign() throws Exception {
        ReceiptType receiptType = receiptTypeRepository.loadBy(new Name("Standard-Beleg")).get();

        assertFalse(rkOnlineContext.getRkOnlineSessionId().isPresent());

        CompactJWSRepresentation compactJWSRepresentation = rkOnlineResourceSignature.sign(
            new JWSPayload(
                "_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto="),
            receiptType);
        assertEquals("eyJhbGciOiJFUzI1NiJ9", compactJWSRepresentation.getProtectedHeader());
        assertEquals(
            "X1IxLUFUMTAwX0NBU0hCT1gtREVNTy0xX0NBU0hCT1gtREVNTy0xLVJlY2VpcHQtSUQtODJfMjAxNi0wMy0xMVQwNDoyNDo0Nl8wLDAwXzAsMDBfMCwwMF8wLDAwXzAsMDBfTkxvaVNITDNic009X2VlZTI1NzU3OWIwMzMwMmZfY2c4aE5VNWlodG89",
            compactJWSRepresentation.getPayloadOriginal());
        assertEquals(
            "_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto=",
            compactJWSRepresentation.getPayload());
        assertEquals(86, compactJWSRepresentation.getSignatureOriginal().length());
        assertEquals(88, compactJWSRepresentation.getSignature().length());
        assertTrue(rkOnlineContext.getRkOnlineSessionId().isPresent());
        RkOnlineSession.Id sessionIdFromFirstCall = rkOnlineContext.getRkOnlineSessionId().get();

        compactJWSRepresentation = rkOnlineResourceSignature.sign(
            new JWSPayload(
                "_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto="),
            receiptType);
        RkOnlineSession.Id sessionIdFromSecondCall = rkOnlineContext.getRkOnlineSessionId().get();
        assertEquals(sessionIdFromFirstCall, sessionIdFromSecondCall);
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testSignWithSignatureDeviceDamagedAndSignatureIsMandatory() throws Exception {
        ReceiptType receiptType = receiptTypeRepository.loadBy(new Name("Start-Beleg")).get();
        MockRkOnlineResourceSession rkOnlineResourceSession = new MockRkOnlineResourceSession();
        rkOnlineResourceSession.setRunInLoginSession(() -> {
            throw new SignatureDeviceNotAvailableException("");
        });
        rkOnlineResourceFactory.setRkOnlineResourceSession(rkOnlineResourceSession);

        rkOnlineResourceSignature.sign(
            new JWSPayload(
                "_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto="),
            receiptType);
    }
}

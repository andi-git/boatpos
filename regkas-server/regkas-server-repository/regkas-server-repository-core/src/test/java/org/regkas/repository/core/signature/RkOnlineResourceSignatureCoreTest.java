package org.regkas.repository.core.signature;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.signature.RkOnlineResourceSignature;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.RkOnlineSession;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.repository.core.crypto.Encoding;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    }

    @Test
    @Transactional
    public void testSign() throws Exception {
        assertFalse(rkOnlineContext.getRkOnlineSessionId().isPresent());

        CompactJWSRepresentation compactJWSRepresentation = rkOnlineResourceSignature.sign(new JWSPayload("_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto="));
        assertEquals("eyJhbGciOiJFUzI1NiJ9", compactJWSRepresentation.getProtectedHeader());
        assertEquals("X1IxLUFUMTAwX0NBU0hCT1gtREVNTy0xX0NBU0hCT1gtREVNTy0xLVJlY2VpcHQtSUQtODJfMjAxNi0wMy0xMVQwNDoyNDo0Nl8wLDAwXzAsMDBfMCwwMF8wLDAwXzAsMDBfTkxvaVNITDNic009X2VlZTI1NzU3OWIwMzMwMmZfY2c4aE5VNWlodG89", compactJWSRepresentation.getPayloadOriginal());
        assertEquals("_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto=", compactJWSRepresentation.getPayload());
        assertEquals(86, compactJWSRepresentation.getSignatureOriginal().length());
        assertEquals(88, compactJWSRepresentation.getSignature().length());
        assertTrue(rkOnlineContext.getRkOnlineSessionId().isPresent());
        RkOnlineSession.Id sessionIdFromFirstCall = rkOnlineContext.getRkOnlineSessionId().get();

        compactJWSRepresentation = rkOnlineResourceSignature.sign(new JWSPayload("_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-82_2016-03-11T04:24:46_0,00_0,00_0,00_0,00_0,00_NLoiSHL3bsM=_eee257579b03302f_cg8hNU5ihto="));
        RkOnlineSession.Id sessionIdFromSecondCall = rkOnlineContext.getRkOnlineSessionId().get();
        assertEquals(sessionIdFromFirstCall, sessionIdFromSecondCall);
    }
}

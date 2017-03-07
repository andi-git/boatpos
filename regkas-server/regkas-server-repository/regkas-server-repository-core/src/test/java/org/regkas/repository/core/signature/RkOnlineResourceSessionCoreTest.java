package org.regkas.repository.core.signature;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.signature.RkOnlineResourceFactory;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class RkOnlineResourceSessionCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Before
    public void before() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        rkOnlineContext.resetSessions();
        rkOnlineContext.setEnvironment(Environment.TEST);
    }

    @After
    public void after() {
        rkOnlineContext.resetSessions();
    }

    @Test
    @Transactional
    public void testLoginSession() throws Exception {
        assertEquals("u123456789", rkOnlineContext.getRkOnlineUsername().get());
        assertEquals("123456789", rkOnlineContext.getRkOnlinePassword().get());
        assertFalse(rkOnlineContext.getRkOnlineSessionId().isPresent());
        assertFalse(rkOnlineContext.getRkOnlineSessionKey().isPresent());
        assertFalse(rkOnlineContext.getLastAction().isPresent());

        rkOnlineResourceFactory.getRkOnlineResourceSession().loginSession();

        assertEquals("u123456789", rkOnlineContext.getRkOnlineUsername().get());
        assertEquals("123456789", rkOnlineContext.getRkOnlinePassword().get());
        assertTrue(rkOnlineContext.getRkOnlineSessionId().isPresent());
        assertEquals(32, rkOnlineContext.getRkOnlineSessionId().get().get().length());
        assertEquals(44, rkOnlineContext.getRkOnlineSessionKey().get().get().length());
        assertEquals(DateTimeHelperMock.DEFAULT_TIME, rkOnlineContext.getLastAction().get().getDateTime());
    }

}
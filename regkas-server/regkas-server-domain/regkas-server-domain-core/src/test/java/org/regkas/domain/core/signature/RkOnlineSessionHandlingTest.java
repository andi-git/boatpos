package org.regkas.domain.core.signature;

import com.google.common.collect.Lists;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.signature.RkOnlineResourceSession;
import org.regkas.domain.api.signature.SignatureDeviceNotAvailableException;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.RkOnlineSession;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RkOnlineSessionHandlingTest extends EntityManagerProviderForRegkas {

    @Inject
    private RkOnlineSessionHandling rkOnlineSessionHandling;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Before
    public void before() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        rkOnlineContext.resetSessions();
    }

    @Test
    @Transactional
    public void testWithinActiveSessionWhenSessionIsActive() throws Exception {
        rkOnlineContext.setSession(new RkOnlineSession(new RkOnlineSession.Id("id"), new RkOnlineSession.Key("Key"), new RkOnlineSession.LastAction(LocalDateTime.now())));
        MockRkOnlineResourceSession rkOnlineResourceSession = new MockRkOnlineResourceSession();
        rkOnlineResourceFactory.setRkOnlineResourceSession(rkOnlineResourceSession);
        MockResponse response = new MockResponse();
        response.setStatus(Response.Status.OK);
        rkOnlineSessionHandling.withinActiveSession(() -> response);
        assertFalse(rkOnlineResourceSession.isLoginSessionCalled());
    }

    @Test
    @Transactional
    public void testWithinActiveSessionWhenSessionIsNotActive() throws Exception {
        MockRkOnlineResourceSession rkOnlineResourceSession = new MockRkOnlineResourceSession();
        rkOnlineResourceFactory.setRkOnlineResourceSession(rkOnlineResourceSession);
        MockResponse response = new MockResponse();
        response.setStatus(Response.Status.OK);
        rkOnlineSessionHandling.withinActiveSession(() -> response);
        assertTrue(rkOnlineResourceSession.isLoginSessionCalled());
        assertEquals(1, rkOnlineResourceSession.getCount());
    }

    @Test
    @Transactional
    public void testWithinActiveSessionWithUnauthorizedResponseFirstTime() throws Exception {
        rkOnlineContext.setSession(new RkOnlineSession(new RkOnlineSession.Id("id"), new RkOnlineSession.Key("Key"), new RkOnlineSession.LastAction(LocalDateTime.now())));
        MockRkOnlineResourceSession rkOnlineResourceSession = new MockRkOnlineResourceSession();
        rkOnlineResourceFactory.setRkOnlineResourceSession(rkOnlineResourceSession);
        MockResponse response = new MockResponse();
        response.setStatusList(Lists.newArrayList(Response.Status.UNAUTHORIZED, Response.Status.OK));
        rkOnlineSessionHandling.withinActiveSession(() -> response);
        assertTrue(rkOnlineResourceSession.isLoginSessionCalled());
        assertEquals(1, rkOnlineResourceSession.getCount());
    }


    @Test(expected = SignatureDeviceNotAvailableException.class)
    @Transactional
    public void testWithinActiveSessionWithUnauthorizedResponseBothTimes() throws Exception {
        rkOnlineContext.setSession(new RkOnlineSession(new RkOnlineSession.Id("id"), new RkOnlineSession.Key("Key"), new RkOnlineSession.LastAction(LocalDateTime.now())));
        MockRkOnlineResourceSession rkOnlineResourceSession = new MockRkOnlineResourceSession();
        rkOnlineResourceFactory.setRkOnlineResourceSession(rkOnlineResourceSession);
        MockResponse response = new MockResponse();
        response.setStatus(Response.Status.UNAUTHORIZED);
        rkOnlineSessionHandling.withinActiveSession(() -> response);
        assertTrue(rkOnlineResourceSession.isLoginSessionCalled());
        assertEquals(1, rkOnlineResourceSession.getCount());
    }

    private static class MockRkOnlineResourceSession implements RkOnlineResourceSession {

        private boolean loginSessionCalled = false;

        private int count = 0;

        @Override
        public void loginSession() {
            loginSessionCalled = true;
            count++;
        }

        public boolean isLoginSessionCalled() {
            return loginSessionCalled;
        }

        public int getCount() {
            return count;
        }
    }
}
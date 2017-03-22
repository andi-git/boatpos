package org.regkas.domain.core.signature;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.RkOnlineSession;
import org.regkas.domain.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class SessionActiveCheckerTest extends EntityManagerProviderForRegkas {

    @Inject
    private RkOnlineSessionHandling.SessionActiveChecker sessionActiveChecker;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Before
    public void before() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        System.out.println(cashBoxContext.get());
    }

    @Test
    @Transactional
    public void sessionActive() throws Exception {
        assertFalse(sessionActiveChecker.isSessionActive());

        rkOnlineContext.setSession(new RkOnlineSession(new RkOnlineSession.Id("abc"), new RkOnlineSession.Key("xyz"), new RkOnlineSession.LastAction(dateTimeHelper.currentTime())));
        assertTrue(sessionActiveChecker.isSessionActive());

        dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 23, 21, 0));
        rkOnlineContext.setSession(new RkOnlineSession(new RkOnlineSession.Id("abc"), new RkOnlineSession.Key("xyz"), new RkOnlineSession.LastAction(DateTimeHelperMock.DEFAULT_TIME)));
        assertFalse(sessionActiveChecker.isSessionActive());

        dateTimeHelper.resetTime();
    }

    @Test
    @Transactional
    public void isOlderThan30Minutes() throws Exception {
        dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 23, 20, 40));
        assertFalse(sessionActiveChecker.isOlderThan30Minutes(LocalDateTime.of(2017, 2, 23, 20, 30)));

        dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 23, 21, 0));
        assertFalse(sessionActiveChecker.isOlderThan30Minutes(LocalDateTime.of(2017, 2, 23, 20, 30)));

        dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 23, 21, 1));
        assertTrue(sessionActiveChecker.isOlderThan30Minutes(LocalDateTime.of(2017, 2, 23, 20, 30)));

        dateTimeHelper.resetTime();
    }

    @Test
    @Transactional
    public void isSessionMaybeExpired() throws Exception {
        MockResponse response = new MockResponse();
        response.setStatus(Response.Status.OK);
        assertFalse(sessionActiveChecker.isSessionMaybeExpired(response));

        response.setStatus(Response.Status.UNAUTHORIZED);
        assertTrue(sessionActiveChecker.isSessionMaybeExpired(response));
    }
}
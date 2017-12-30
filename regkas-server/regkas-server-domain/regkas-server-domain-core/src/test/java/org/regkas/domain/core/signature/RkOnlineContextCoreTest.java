package org.regkas.domain.core.signature;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.RkOnlineSession;
import org.regkas.domain.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class RkOnlineContextCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Before
    public void before() {
        rkOnlineContext.resetSessions();
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
    }

    @Test
    @Transactional
    public void testEnvironment() {
        RkOnlineContext rkOnlineContext = new RkOnlineContextCore();
        assertEquals(Environment.PROD, rkOnlineContext.getEnvironment());
        rkOnlineContext.setEnvironment(Environment.TEST);
        assertEquals(Environment.TEST, rkOnlineContext.getEnvironment());

        System.setProperty("boatpos.regkas.environment", "xyz");
        assertEquals(Environment.TEST, rkOnlineContext.getEnvironment());
        System.setProperty("boatpos.regkas.environment", "prod");
        assertEquals(Environment.PROD, rkOnlineContext.getEnvironment());
        System.setProperty("boatpos.regkas.environment", "test");
        assertEquals(Environment.TEST, rkOnlineContext.getEnvironment());
        System.clearProperty("boatpos.regkas.environment");
    }

    @Test
    @Transactional
    public void testGetRkOnlineUsername() {
        assertEquals("u123456789", rkOnlineContext.getRkOnlineUsername().get());
    }

    @Test
    @Transactional
    public void testGetRkOnlinePassword() {
        assertEquals("123456789", rkOnlineContext.getRkOnlinePassword().get());
    }

    @Test
    @Transactional
    public void testRkOnlineSession() {
        assertFalse(rkOnlineContext.getRkOnlineSessionId().isPresent());
        assertFalse(rkOnlineContext.getRkOnlineSessionKey().isPresent());
        assertFalse(rkOnlineContext.getLastAction().isPresent());

        rkOnlineContext.setSession(new RkOnlineSession(new RkOnlineSession.Id("abc"), new RkOnlineSession.Key("xyz"), new RkOnlineSession.LastAction(LocalDateTime.of(2017, 2, 23, 21, 4))));

        assertEquals("abc", rkOnlineContext.getRkOnlineSessionId().get().get());
        assertEquals("xyz", rkOnlineContext.getRkOnlineSessionKey().get().get());
        assertEquals(LocalDateTime.of(2017, 2, 23, 21, 4), rkOnlineContext.getLastAction().get().getDateTime());

        rkOnlineContext.resetSessions();
        assertFalse(rkOnlineContext.getRkOnlineSessionId().isPresent());
        assertFalse(rkOnlineContext.getRkOnlineSessionKey().isPresent());
        assertFalse(rkOnlineContext.getLastAction().isPresent());
    }

    @Test
    @Transactional
    public void testAction() {
        assertFalse(rkOnlineContext.getLastAction().isPresent());
        rkOnlineContext.action();

        rkOnlineContext.setSession(new RkOnlineSession(new RkOnlineSession.Id("abc"), new RkOnlineSession.Key("xyz"), new RkOnlineSession.LastAction(LocalDateTime.of(2017, 2, 23, 21, 4))));

        assertEquals(LocalDateTime.of(2017, 2, 23, 21, 4), rkOnlineContext.getLastAction().get().getDateTime());

        dateTimeHelper.setTime(LocalDateTime.of(2017, 2, 23, 21, 5));
        rkOnlineContext.action();
        assertEquals(LocalDateTime.of(2017, 2, 23, 21, 5), rkOnlineContext.getLastAction().get().getDateTime());

        dateTimeHelper.resetTime();
    }
}

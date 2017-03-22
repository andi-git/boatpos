package org.regkas.domain.core.signature;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.RkOnlineSession;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class RkOnlineRestResourceTest extends EntityManagerProviderForRegkas {

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Test
    @Transactional
    public void testGetURL() throws Exception {
        rkOnlineContext.resetSessions();
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        rkOnlineContext.setEnvironment(Environment.TEST);
        rkOnlineContext.setSession(new RkOnlineSession(new RkOnlineSession.Id("123"), new RkOnlineSession.Key("456"), new RkOnlineSession.LastAction(LocalDateTime.now())));
        assertEquals("https://hs-abnahme.a-trust.at/asignrkonline/v2/Session/123/Sign/JWS", RkOnlineRestResource.SignJWS.getURL(rkOnlineContext));
        assertEquals("https://hs-abnahme.a-trust.at/asignrkonline/v2/Session/u123456789", RkOnlineRestResource.Session.getURL(rkOnlineContext));
        assertEquals("https://hs-abnahme.a-trust.at/asignrkonline/v2/u123456789/Certificate", RkOnlineRestResource.Certificate.getURL(rkOnlineContext));
    }

}
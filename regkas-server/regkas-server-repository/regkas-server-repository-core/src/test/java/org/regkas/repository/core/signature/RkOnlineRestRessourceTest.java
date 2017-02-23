package org.regkas.repository.core.signature;

import org.junit.Test;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.values.RkOnlineUsername;

import static org.junit.Assert.*;

public class RkOnlineRestRessourceTest {

    @Test
    public void testGetURL() throws Exception {
        RkOnlineContext rkOnlineContext = new RkOnlineContextCore();
        rkOnlineContext.setEnvironment(Environment.TEST);
        rkOnlineContext.setRkOnlineUsername(new RkOnlineUsername("u123456789"));
        assertEquals("https://hs-abnahme.a-trust.at/asignrkonline/v2/u123456789/Sign/JWS", RkOnlineRestRessource.SignJWS.getURL(rkOnlineContext));
        assertEquals("https://hs-abnahme.a-trust.at/asignrkonline/v2/Session/u123456789", RkOnlineRestRessource.Session.getURL(rkOnlineContext));
    }

}
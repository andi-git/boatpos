package org.regkas.service.core;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.AuthenticationService;
import org.regkas.service.api.bean.*;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class AuthenticationServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private AuthenticationService authenticationService;

    @Test
    @Transactional
    public void testAuthenticate() throws Exception {
        assertTrue(authenticationService.authenticate(new CredentialsBean("Maria Musterfrau", "abc123", "RegKas1")));
        assertFalse(authenticationService.authenticate(new CredentialsBean("Maria", "abc123", "RegKas1")));
        assertFalse(authenticationService.authenticate(new CredentialsBean("Maria Musterfrau", "abc", "RegKas1")));
        assertFalse(authenticationService.authenticate(new CredentialsBean("Maria Musterfrau", "abc123", "Reg")));
    }
}
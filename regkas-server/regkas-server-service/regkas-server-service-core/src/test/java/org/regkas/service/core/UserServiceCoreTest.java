package org.regkas.service.core;

import com.google.common.collect.Lists;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.PasswordPlain;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.UserService;
import org.regkas.service.api.bean.*;
import org.regkas.service.core.util.CashBoxContext;
import org.regkas.service.core.util.CompanyContext;
import org.regkas.service.core.util.UserContext;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class UserServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private UserService userService;

    @Test
    @Transactional
    public void testAuthenticate() throws Exception {
        assertTrue(userService.authenticate(new CredentialsBean("Maria Musterfrau", "abc123")));
        assertFalse(userService.authenticate(new CredentialsBean("Maria", "abc123")));
        assertFalse(userService.authenticate(new CredentialsBean("Maria Musterfrau", "abc")));
    }
}
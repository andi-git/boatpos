package org.regkas.service.core.context;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.context.CompanyContext;
import org.regkas.domain.api.context.UserContext;
import org.regkas.service.api.context.ContextService;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ContextServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ContextService contextService;

    @Inject
    private UserContext userContext;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private CashBoxContext cashBoxContext;

    @Test
    @Transactional
    public void initContext() throws Exception {
        assertNull(userContext.get());
        assertNull(companyContext.get());
        assertNull(cashBoxContext.get());
        contextService.initContext("Maria Musterfrau", "RegKas1");
        assertEquals("Maria Musterfrau", userContext.get().getName().get());
        assertEquals("company" ,companyContext.get().getName().get());
        assertEquals("RegKas1", cashBoxContext.get().getName().get());
    }
}
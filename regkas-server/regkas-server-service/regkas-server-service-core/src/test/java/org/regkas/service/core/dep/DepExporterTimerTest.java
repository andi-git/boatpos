package org.regkas.service.core.dep;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.context.CompanyContext;
import org.regkas.repository.api.context.UserContext;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class DepExporterTimerTest extends EntityManagerProviderForRegkas {

    @EJB
    private DepExporterTimer depExporterTimer;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserContext userContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @SuppressWarnings("Duplicates")
    @Before
    public void before() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        rkOnlineContext.setEnvironment(Environment.TEST);
    }

    @After
    public void after() {
        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
        rkOnlineContext.resetSessions();
    }

    @Test
    @Transactional
    public void testExportDep() throws Exception {
        assertEquals(6, depExporterTimer.exportDep().size());
    }
}

package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.CashBoxBuilderCore;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CashBoxRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testBuilder() {
        assertEquals(CashBoxBuilderCore.class, cashBoxRepository.builder().getClass());
    }

    @Test
    @Transactional
    public void testLoadByName() {
        assertEquals("123", cashBoxRepository.loadBy(new Name("RegKas1")).get().getSignatureCertificateSerialNumber().get());
    }
}

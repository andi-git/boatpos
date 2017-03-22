package org.regkas.domain.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CashBoxRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testLoadByName() {
        Optional<CashBox> regKas1 = cashBoxRepository.loadBy(new Name("RegKas1"));
        assertEquals("123", regKas1.get().getSignatureCertificateSerialNumber().get());
        assertEquals("ONRcz49yLDIo2FgwNhe9Q5fSiZFEies97uRMzeAAPkI=", regKas1.get().getAesKeyBase64().get());
    }
}

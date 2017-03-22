package org.regkas.domain.core.turnovercounter;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class EncryptTurnoverCounterStartTest extends EntityManagerProviderForRegkas {

    @Inject
    private EncryptTurnoverCounterStart encryptTurnoverCounterStart;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testEncryptTurnoverCounter() throws Exception {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKasStart")).get();
        assertEquals("BrhOvns92T8=", encryptTurnoverCounterStart.encryptTurnoverCounter(new ReceiptId("001"), cashBox).get());
    }
}
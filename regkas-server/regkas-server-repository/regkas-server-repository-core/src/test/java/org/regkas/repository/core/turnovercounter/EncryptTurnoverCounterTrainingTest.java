package org.regkas.repository.core.turnovercounter;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class EncryptTurnoverCounterTrainingTest extends EntityManagerProviderForRegkas {

    @Inject
    private EncryptTurnoverCounterTraining encryptTurnoverCounterTraining;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testEncryptTurnoverCounter() throws Exception {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("VFJB", encryptTurnoverCounterTraining.encryptTurnoverCounter(new ReceiptId("001"), cashBox).get());
    }
}
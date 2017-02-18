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

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class EncryptTurnoverCounterDefaultTest extends EntityManagerProviderForRegkas {

    @Inject
    private EncryptTurnoverCounterDefault encryptTurnoverCounterDefault;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testEncryptTurnoverCounter() throws Exception {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("O1Lw+Xg5xNM=", encryptTurnoverCounterDefault.encryptTurnoverCounter(new ReceiptId("001"), cashBox).get());

        CashBox cashBoxStart = cashBoxRepository.loadBy(new Name("RegKasStart")).get();
        assertEquals("wCP2Vii9+f8=", encryptTurnoverCounterDefault.encryptTurnoverCounter(new ReceiptId(cashBox.getName().get()), cashBoxStart).get());

        // check against Demo-Output of Mustercode V 0.5.
        CashBox demoCashBox817 = cashBoxRepository.loadBy(new Name("DEMO-CASH-BOX817")).get();
        assertEquals("vXvqlWy0zcM=", encryptTurnoverCounterDefault.encryptTurnoverCounter(new ReceiptId("83470"), demoCashBox817).get());
    }
}
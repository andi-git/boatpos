package org.regkas.repository.core.crypto;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.values.AESKeyBase64;
import org.regkas.repository.api.values.IVToEncryptTurnoverCounter;
import org.regkas.repository.api.values.InputForChainCalculation;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.api.values.TotalPriceCent;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class CryptoTest extends EntityManagerProviderForRegkas {

    @Inject
    private Crypto crypto;

    @Inject
    private Encoding encoding;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Test
    @Transactional
    public void testIsUnlimitedStrengthPolicyAvailable() throws Exception {
        assertTrue(crypto.isUnlimitedStrengthPolicyAvailable());
    }

    @Test
    @Transactional
    public void testEncryptCTR() {
        assertEquals("vXvqlWy0zcM=",
                crypto.encryptCTR(
                        new IVToEncryptTurnoverCounter(new Name("DEMO-CASH-BOX817"), new ReceiptId("83470")),
                        new TotalPriceCent(8733L),
                        new AES.AESKey(new AESKeyBase64("RCsRmHn5tkLQrRpiZq2ucwPpwvHJLiMgLvwrwEImddI="))
                ).get());
    }

    @Test
    @Transactional
    public void testGet2ComplementRepForLong() {
        byte[] expected = new byte[] {
                0,
                0,
                0,
                0,
                0,
                0,
                34,
                29
        };
        assertTrue(Arrays.equals(expected, crypto.get2ComplementRepForLong(8733L)));
    }

    @Test
    @Transactional
    public void testCreateConcatenatedHashValue() {
        byte[] expected = new byte[] {
                9,
                57,
                -115,
                91,
                -95,
                44,
                37,
                40,
                -73,
                9,
                90,
                -40,
                55,
                63,
                93,
                20
        };
        assertTrue(Arrays.equals(expected, crypto.createConcatenatedHashValue(new IVToEncryptTurnoverCounter(new Name("DEMO-CASH-BOX817"), new ReceiptId("83470")))));
    }

    @Test
    @Transactional
    public void testCalculateChainValue() {
        assertEquals("WZRHGrsBESo=", crypto.calculateChainValue(new InputForChainCalculation("12345")).get());
        assertEquals("4iF9Pk4SDGo=", crypto.calculateChainValue(new InputForChainCalculation("67890")).get());
    }
}
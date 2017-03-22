package org.regkas.repository.core.turnovercounter;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.IVToEncryptTurnoverCounter;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.core.crypto.AES;
import org.regkas.repository.core.crypto.Crypto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class EncryptTurnoverCounterDefault implements EncryptTurnoverCounter {

    @Inject
    private Crypto crypto;

    @Inject
    private Crypto.MessageDigestSHA256 messageDigestSHA256;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public EncryptedTurnoverValue encryptTurnoverCounter(ReceiptId receiptId, CashBox cashBox) {
        IVToEncryptTurnoverCounter ivToEncryptTurnoverCounter = new IVToEncryptTurnoverCounter(cashBox.getName(), receiptId);
        return crypto.encryptCTR(ivToEncryptTurnoverCounter, cashBox.getTurnoverCountCent(), new AES.AESKey(cashBox.getAesKeyBase64()));
    }
}

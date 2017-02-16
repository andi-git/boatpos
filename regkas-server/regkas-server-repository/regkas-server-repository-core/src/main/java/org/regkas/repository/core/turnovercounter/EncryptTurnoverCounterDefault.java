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
    @SLF4J
    private LogWrapper log;

    @Override
    public EncryptedTurnoverValue encryptTurnoverCounter(ReceiptId receiptId, CashBox cashBox) {
        IVToEncryptTurnoverCounter ivToEncryptTurnoverCounter = new IVToEncryptTurnoverCounter(cashBox.getName(), receiptId);
        byte[] concatenatedHashValue = createConcatenatedHashValue(ivToEncryptTurnoverCounter);
        return crypto.encryptCTR(concatenatedHashValue, cashBox.getTurnoverCountCent(), new AES.AESKey(cashBox.getAesKeyBase64()));
    }

    private byte[] createConcatenatedHashValue(IVToEncryptTurnoverCounter ivToEncryptTurnoverCounter) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        byte[] hashValue = messageDigest.digest(ivToEncryptTurnoverCounter.get().getBytes());
        byte[] concatenatedHashValue = new byte[16];
        System.arraycopy(hashValue, 0, concatenatedHashValue, 0, 16);
        return concatenatedHashValue;
    }

}

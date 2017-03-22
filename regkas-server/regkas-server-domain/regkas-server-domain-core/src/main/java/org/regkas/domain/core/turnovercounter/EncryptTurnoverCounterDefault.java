package org.regkas.domain.core.turnovercounter;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.values.EncryptedTurnoverValue;
import org.regkas.domain.api.values.IVToEncryptTurnoverCounter;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.domain.core.crypto.AES;
import org.regkas.domain.core.crypto.Crypto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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

package org.regkas.repository.core.crypto;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.TotalPriceCent;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import static com.google.common.base.Preconditions.checkArgument;

@ApplicationScoped
public class Crypto {

    private static final int TURNOVER_COUNTER_LENGTH_IN_BYTES = 5;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private Encoding encoding;

    @PostConstruct
    private void init() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public boolean isUnlimitedStrengthPolicyAvailable() {
        try {
            return Cipher.getMaxAllowedKeyLength("AES") >= 256;
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
        }
        return false;
    }

    public EncryptedTurnoverValue encryptCTR(final byte[] concatenatedHashValue, TotalPriceCent turnoverCounter, final AES.AESKey aesKey) {
        // extract bytes 0-15 from hash value
        final ByteBuffer byteBufferIV = ByteBuffer.allocate(16);
        byteBufferIV.put(concatenatedHashValue);
        final byte[] IV = byteBufferIV.array();

        // prepare data
        // block size for AES is 128 bit (16 bytes)
        // thus, the turnover counter needs to be inserted into an array of length 16

        //initialisation of the data which should be encrypted
        final ByteBuffer byteBufferData = ByteBuffer.allocate(16);
        byteBufferData.putLong(turnoverCounter.get());
        final byte[] data = byteBufferData.array();

        //now the turnover counter is represented in two's-complement representation (negative values are possible)
        //length is defined by the respective implementation (min. 5 bytes)
        byte[] turnOverCounterByteRep = get2ComplementRepForLong(turnoverCounter.get(), TURNOVER_COUNTER_LENGTH_IN_BYTES);

        //two's-complement representation is copied to the data array, and inserted at index 0
        System.arraycopy(turnOverCounterByteRep, 0, data, 0, turnOverCounterByteRep.length);

        // prepare AES cipher with CTR/ICM mode, NoPadding is essential for the
        // decryption process. Padding could not be reconstructed due
        // to storing only 8 bytes of the cipher text (not the full 16 bytes)
        // (or 5 bytes if the mininum turnover length is used)
        final IvParameterSpec ivSpec = new IvParameterSpec(IV);

        final Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey.asSecretKey(), ivSpec);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }

        // encrypt the turnover value with the prepared cipher
        final byte[] encryptedTurnOverValueComplete;
        try {
            encryptedTurnOverValueComplete = cipher.doFinal(data);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }

        // extract bytes that will be stored in the receipt (only bytes 0-7)
        // cryptographic NOTE: this is only possible due to the use of the CTR
        // mode, would not work for ECB/CBC etc. modes
        final byte[] encryptedTurnOverValue = new byte[TURNOVER_COUNTER_LENGTH_IN_BYTES]; // or 5 bytes if min.
        // turnover length is
        // used
        System.arraycopy(encryptedTurnOverValueComplete, 0, encryptedTurnOverValue, 0, TURNOVER_COUNTER_LENGTH_IN_BYTES);

        // encode result as BASE64
        return new EncryptedTurnoverValue(encoding.base64Encode(encryptedTurnOverValue, false));
    }

    public byte[] get2ComplementRepForLong(long value, int numberOfBytesFor2ComplementRepresentation) {
        checkArgument(numberOfBytesFor2ComplementRepresentation >= 1 && (numberOfBytesFor2ComplementRepresentation <= 8),
                "numberOfBytesFor2ComplementRepresentation should be >= 1 and <= 8: " + numberOfBytesFor2ComplementRepresentation);

        //create byte buffer, max length 8 bytes (equal to long representation)
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putLong(value);
        byte[] longRep = byteBuffer.array();

        //if given length for encoding is equal to 8, we are done
        if (numberOfBytesFor2ComplementRepresentation == 8) {
            return longRep;
        }

        //if given length of encoding is less than 8 bytes, we truncate the representation (of course one needs to be sure
        //that the given long value is not larger than the created byte array
        byte[] byteRep = new byte[numberOfBytesFor2ComplementRepresentation];

        //truncating the 8-bytes long representation
        System.arraycopy(longRep, 8 - numberOfBytesFor2ComplementRepresentation, byteRep, 0, numberOfBytesFor2ComplementRepresentation);
        return byteRep;
    }
}

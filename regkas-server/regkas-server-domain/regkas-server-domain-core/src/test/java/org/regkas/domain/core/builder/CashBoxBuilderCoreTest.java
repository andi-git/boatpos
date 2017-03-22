package org.regkas.domain.core.builder;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.junit.Test;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.values.AESKeyBase64;
import org.regkas.domain.api.values.CertificationServiceProvider;
import org.regkas.domain.api.values.IpAddress;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.RkOnlinePassword;
import org.regkas.domain.api.values.RkOnlineUsername;
import org.regkas.domain.api.values.SignatureCertificateSerialNumber;
import org.regkas.domain.api.values.TotalPriceCent;

import static org.junit.Assert.assertEquals;

public class CashBoxBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("cashbox-id", build().getName().get());
    }

    public static CashBox build() {
        return new CashBoxBuilderCore()
                .add(Enabled.TRUE)
                .add(new KeyBinding(' '))
                .add(new PictureUrl(""))
                .add(new PictureUrlThumb(""))
                .add(new Priority(1))
                .add(new Name("cashbox-id"))
                .add(new SignatureCertificateSerialNumber("xyz"))
                .add(new IpAddress("192.168.0.11"))
                .add(new AESKeyBase64("AnnGNHy7KpYchyMR3GrtCCdXDmxiZp3ucA7gdWnSOVM="))
                .add(new TotalPriceCent(123L))
                .add(new CertificationServiceProvider("AT0"))
                .add(new RkOnlineUsername("username"))
                .add(new RkOnlinePassword("password"))
                .build();
    }
}
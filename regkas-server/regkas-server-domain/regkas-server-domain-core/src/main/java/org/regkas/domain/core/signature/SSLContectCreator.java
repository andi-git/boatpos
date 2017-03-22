package org.regkas.domain.core.signature;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@ApplicationScoped
public class SSLContectCreator {

    @Inject
    @SLF4J
    private LogWrapper log;

    public SSLContext createSSLContext() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts(), new SecureRandom());
            return sslContext;
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    private TrustManager[] trustAllCerts() {
        return new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
    }

}

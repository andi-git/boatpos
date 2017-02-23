package org.regkas.repository.core.signature;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.signature.RkOnlineSignature;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.core.crypto.Encoding;
import org.regkas.repository.core.signature.entity.SignJWSPostRequest;
import org.regkas.repository.core.signature.entity.SignJWSPostResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import static com.google.common.base.Preconditions.checkNotNull;

@ApplicationScoped
public class RkOnlineSignatureCore implements RkOnlineSignature {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private RkOnlineContextCore rkOnlineContextCore;

    @Inject
    private Encoding encoding;

    @Override
    public CompactJWSRepresentation sign(JWSPayload jwsPayload) {
        checkNotNull(SimpleValueObject.notNull(rkOnlineContextCore.getRkOnlineUsername()), "'username' must not be null");
        checkNotNull(SimpleValueObject.notNull(rkOnlineContextCore.getRkOnlinePassword()), "'password' must not be null");
        checkNotNull(SimpleValueObject.notNull(jwsPayload), "'jwsPayload' must not be null");

        // TODO add session-handling

        SignJWSPostRequest signJWSPostRequest = new SignJWSPostRequest(rkOnlineContextCore.getRkOnlinePassword().get(), jwsPayload.get());
        log.debug("call {}", RkOnlineRestRessource.SignJWS.getURL(rkOnlineContextCore));
        log.debug("body {}", () -> serializer.serialize(signJWSPostRequest));
        Response response = ClientBuilder
                .newBuilder()
                .sslContext(createSSLContext())
                .build()
                .target(RkOnlineRestRessource.SignJWS.getURL(rkOnlineContextCore))
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(signJWSPostRequest));
        if (200 != response.getStatus()) {
            throw new RuntimeException("rest-call of signature returns status " + response.getStatus() + " (" + response.getStatusInfo() + ")");
        }
        SignJWSPostResponse signJWSPostResponse = response.readEntity(SignJWSPostResponse.class);
        return new CompactJWSRepresentationCore(signJWSPostResponse.getCompactJwsRepresentation(), encoding);
    }

    private SSLContext createSSLContext() {
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

package org.regkas.repository.core.signature;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.signature.RkOnlineResourceSignature;
import org.regkas.repository.api.signature.SignatureDeviceNotAvailableException;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.api.values.RkOnlineSession;
import org.regkas.repository.core.crypto.Encoding;
import org.regkas.repository.core.signature.entity.SignJWSPostRequest;
import org.regkas.repository.core.signature.entity.SignJWSPostResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@ApplicationScoped
public class RkOnlineResourceSignatureCore implements RkOnlineResourceSignature {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private Encoding encoding;

    @Inject
    private RkOnlineSessionHandling rkOnlineSessionHandling;

    @Inject
    private SSLContectCreator sslContectCreator;

    @Inject
    private ResponseStateChecker responseStateChecker;

    @Override
    public CompactJWSRepresentation sign(JWSPayload jwsPayload) throws SignatureDeviceNotAvailableException {
        checkNotNull(SimpleValueObject.notNull(jwsPayload), "'jwsPayload' must not be null");
        Response response = rkOnlineSessionHandling.withinActiveSession(() -> {
            Optional<RkOnlineSession.Key> key = rkOnlineContext.getRkOnlineSessionKey();
            if (!key.isPresent()) {
                SignatureDeviceNotAvailableException signatureDeviceNotAvailableException = new SignatureDeviceNotAvailableException("no rk-online-session active");
                log.error(signatureDeviceNotAvailableException);
                throw signatureDeviceNotAvailableException;
            }
            SignJWSPostRequest signJWSPostRequest = new SignJWSPostRequest(key.get().get(), jwsPayload.get());
            log.info("call {}", RkOnlineRestResource.SignJWS.getURL(rkOnlineContext));
            log.info("body {}", () -> serializer.serialize(signJWSPostRequest));
            return ClientBuilder
                    .newBuilder()
                    .sslContext(sslContectCreator.createSSLContext())
                    .build()
                    .target(RkOnlineRestResource.SignJWS.getURL(rkOnlineContext))
                    .request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(signJWSPostRequest));
        });
        responseStateChecker.checkResponseState(response);

        SignJWSPostResponse signJWSPostResponse = response.readEntity(SignJWSPostResponse.class);
        log.info("response from rk-online: {}", () -> serializer.serialize(signJWSPostResponse));
        return new CompactJWSRepresentationCore(signJWSPostResponse.getResult(), encoding);
    }
}

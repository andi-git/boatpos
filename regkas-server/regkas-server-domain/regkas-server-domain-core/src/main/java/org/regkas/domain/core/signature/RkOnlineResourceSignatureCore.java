package org.regkas.domain.core.signature;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.serializer.NonPrettyPrintingGson;
import org.regkas.domain.api.serializer.Serializer;
import org.regkas.domain.api.signature.CompactJWSRepresentation;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceSignature;
import org.regkas.domain.api.signature.SignatureDeviceMandatoryException;
import org.regkas.domain.api.signature.SignatureDeviceNotAvailableException;
import org.regkas.domain.api.values.JWSPayload;
import org.regkas.domain.api.values.RkOnlineSession;
import org.regkas.domain.core.crypto.Encoding;
import org.regkas.domain.core.signature.entity.SignJWSPostRequest;
import org.regkas.domain.core.signature.entity.SignJWSPostResponse;

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
    public CompactJWSRepresentation sign(JWSPayload jwsPayload, ReceiptType receiptType) {
        checkNotNull(SimpleValueObject.notNull(jwsPayload), "'jwsPayload' must not be null");
        try {
            Response response = rkOnlineSessionHandling.withinActiveSession(() -> {
                Optional<RkOnlineSession.Key> key = rkOnlineContext.getRkOnlineSessionKey();
                if ( !key.isPresent()) {
                    SignatureDeviceNotAvailableException signatureDeviceNotAvailableException = new SignatureDeviceNotAvailableException(
                            "no rk-online-session active");
                    log.error(signatureDeviceNotAvailableException);
                    throw signatureDeviceNotAvailableException;
                }
                SignJWSPostRequest signJWSPostRequest = new SignJWSPostRequest(key.get().get(), jwsPayload.get());
                log.debug("call {}", RkOnlineRestResource.SignJWS.getURL(rkOnlineContext));
                log.debug("body {}", () -> serializer.serialize(signJWSPostRequest));
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
            log.debug("response from rk-online: {}", () -> serializer.serialize(signJWSPostResponse));
            return CompactJWSRepresentationCore.fromRealCompactJwsRepresentation(signJWSPostResponse.getResult(), encoding);

        } catch (SignatureDeviceNotAvailableException e) {
            log.error(e);
            rkOnlineContext.resetSessions();
            if (SimpleValueObject.nullSafe(receiptType.getSignatureMandatory())) {
                throw new SignatureDeviceMandatoryException("signature is mandatory for " + receiptType.getName().get(), e);
            } else {
                return signWhenSignatureIsNotAvailable(jwsPayload, receiptType);
            }
        }
    }

    @Override
    public CompactJWSRepresentation signWhenSignatureIsNotAvailable(JWSPayload jwsPayload, ReceiptType receiptType) {
        if (SimpleValueObject.nullSafe(receiptType.getSignatureMandatory())) {
            throw new SignatureDeviceMandatoryException("signature is mandatory for " + receiptType.getName().get());
        } else {
            return CompactJWSRepresentationCore.whenSignatureDeviceIsNotAvailable(jwsPayload, encoding);
        }
    }
}

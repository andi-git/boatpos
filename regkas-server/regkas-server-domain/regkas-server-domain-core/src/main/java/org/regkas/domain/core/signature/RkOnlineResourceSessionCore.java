package org.regkas.domain.core.signature;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.domain.api.serializer.NonPrettyPrintingGson;
import org.regkas.domain.api.serializer.Serializer;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.signature.RkOnlineResourceSession;
import org.regkas.domain.api.signature.SignatureDeviceNotAvailableException;
import org.regkas.domain.api.values.RkOnlineSession;
import org.regkas.domain.core.signature.entity.SessionPutRequest;
import org.regkas.domain.core.signature.entity.SessionPutResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class RkOnlineResourceSessionCore implements RkOnlineResourceSession {

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private SSLContectCreator sslContectCreator;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private ResponseStateChecker responseStateChecker;

    @Override
    public void loginSession() throws SignatureDeviceNotAvailableException {
        throw new SignatureDeviceNotAvailableException("a-trust is not available because of data-center move");
        /*
        String url = RkOnlineRestResource.Session.getURL(rkOnlineContext);
        SessionPutRequest request = new SessionPutRequest(rkOnlineContext.getRkOnlinePassword().get());
        log.debug("call {}", url);
        log.debug("body {}", () -> serializer.serialize(request));
        Response response = ClientBuilder
                .newBuilder()
                .sslContext(sslContectCreator.createSSLContext())
                .build()
                .target(url)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(request));
        responseStateChecker.checkResponseState(response);
        SessionPutResponse sessionPutResponse = response.readEntity(SessionPutResponse.class);
        log.debug("response from rk-online: {}", () -> serializer.serialize(sessionPutResponse));
        rkOnlineContext.setSession(
                new RkOnlineSession(
                        new RkOnlineSession.Id(sessionPutResponse.getSessionid()),
                        new RkOnlineSession.Key(sessionPutResponse.getSessionkey()),
                        new RkOnlineSession.LastAction(dateTimeHelper.currentTime())));
         */
    }
}

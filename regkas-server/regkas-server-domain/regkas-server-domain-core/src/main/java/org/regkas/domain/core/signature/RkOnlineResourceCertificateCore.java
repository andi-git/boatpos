package org.regkas.domain.core.signature;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceCertificate;
import org.regkas.domain.api.values.Certificate;
import org.regkas.domain.core.signature.entity.CertificateGetResponse;

@ApplicationScoped
public class RkOnlineResourceCertificateCore implements RkOnlineResourceCertificate {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private SSLContectCreator sslContectCreator;

    @Override
    public Certificate loadCertificate() {
        log.debug("load certificate from: " + RkOnlineRestResource.Certificate.getURL(rkOnlineContext));
        Response response = ClientBuilder
            .newBuilder()
            .sslContext(sslContectCreator.createSSLContext())
            .build()
            .target(RkOnlineRestResource.Certificate.getURL(rkOnlineContext))
            .request()
            .accept(MediaType.APPLICATION_JSON_TYPE)
            .get();
        if (response.getStatusInfo() != Response.Status.OK) {
            String message = "load certificate returns with status: " + response.getStatusInfo();
            log.error(message);
            throw new RuntimeException(message);
        }
        CertificateGetResponse certificateGetResponse = response.readEntity(CertificateGetResponse.class);
        return new Certificate(certificateGetResponse.getSignaturzertifikat(), certificateGetResponse.getZertifizierungsstellen());
    }
}

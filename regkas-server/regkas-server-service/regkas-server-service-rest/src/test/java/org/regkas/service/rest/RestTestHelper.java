package org.regkas.service.rest;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Specializes;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.util.function.Function;

@Specializes
@Dependent
public class RestTestHelper extends org.boatpos.common.test.rest.RestTestHelper {

    public Invocation.Builder createRestCallWithHeaderCredentialsForTestUser(URL url, Function<WebTarget, WebTarget> addPath) throws Exception {
        return createRestCallWithHeaderCredentialsForTestUser(url, addPath, MediaType.APPLICATION_JSON_TYPE);
    }

    public Invocation.Builder createRestCallWithHeaderCredentialsForTestUser(URL url, Function<WebTarget, WebTarget> addPath, MediaType mediaType) throws Exception {
        return super.createRestCall(url, addPath, mediaType)
                .header("username", "Maria Musterfrau")
                .header("password", "abc123")
                .header("cashbox", "RegKas1");
    }
}

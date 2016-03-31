package org.regkas.service.rest;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Specializes;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import java.net.URL;
import java.util.function.Function;

@Specializes
@Dependent
public class RestTestHelper extends org.boatpos.common.test.rest.RestTestHelper {

    public Invocation.Builder createRestCallWithCredentialsForTestUser(URL url, Function<WebTarget, WebTarget> addPath) throws Exception {
        return super.createRestCall(url, addPath)
                .header("username", "Maria Musterfrau")
                .header("password", "abc123")
                .header("cashbox", "RegKas1");
    }
}

package org.boatpos.service.rest;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Specializes;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import java.net.URL;
import java.util.function.Function;

@Specializes
@Dependent
public class RestTestHelper extends org.boatpos.common.test.rest.RestTestHelper {

    @Override
    public Invocation.Builder createRestCall(URL url, Function<WebTarget, WebTarget> addPath) throws Exception {
        return super.createRestCall(url, addPath)
                .header("username", "boatpos")
                .header("password", "test123");

    }
}

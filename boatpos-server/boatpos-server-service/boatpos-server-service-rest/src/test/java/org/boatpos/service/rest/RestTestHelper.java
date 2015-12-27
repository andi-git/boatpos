package org.boatpos.service.rest;

import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.AbstractMasterDataBean;

import javax.enterprise.context.Dependent;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@Dependent
public class RestTestHelper {

    public Invocation.Builder createRestCall(URL url, Function<WebTarget, WebTarget> addPath) throws Exception {
        WebTarget webTarget = ClientBuilder.newClient().target(url.toURI()).path("rest");
        webTarget = addPath.apply(webTarget);
        return webTarget.request().accept(MediaType.APPLICATION_JSON);
    }

    public <T extends AbstractMasterDataBean> void assertCount(URL url, String subPath, int count) throws Exception {
        assertCount(url, subPath, count, EnabledState.All);
    }

    public <T extends AbstractMasterDataBean> void assertCount(URL url, String subPath, int count, EnabledState enabledState) throws Exception {
        String state = "";
        if (EnabledState.Enabled == enabledState) {
            state = "/enabled";
        } else if (EnabledState.Disabled == enabledState) {
            state = "/disabled";
        }
        final String finalState = state;
        Response response = createRestCall(url, (wt) -> wt.path(subPath).path(finalState)).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(count, response.readEntity(new GenericType<List<T>>() {
        }).size());
    }

}

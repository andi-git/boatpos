package org.regkas.domain.core.signature;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.signature.SignatureDeviceNotAvailableException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

@RunWith(Arquillian.class)
public class ResponseStateCheckerTest {

    @Inject
    private ResponseStateChecker responseStateChecker;

    @Test
    public void testCheckResponseStateOk() throws Exception {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setStatus(Response.Status.OK);
        responseStateChecker.checkResponseState(mockResponse);
    }

    @Test(expected = SignatureDeviceNotAvailableException.class)
    public void testCheckResponseStateNotOk() throws Exception {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setStatus(Response.Status.UNAUTHORIZED);
        responseStateChecker.checkResponseState(mockResponse);
    }

}
package org.boatpos.common.service.rest;

import org.junit.Test;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CORSFilterTest {

    @SuppressWarnings("unchecked")
    @Test
    public void testFilter() throws Exception {
        final Map<String, String> result = new HashMap<>();
        ContainerRequestContext requestContext = mock(ContainerRequestContext.class);
        ContainerResponseContext responseContext = mock(ContainerResponseContext.class);
        MultivaluedMap headers = mock(MultivaluedMap.class);
        when(responseContext.getHeaders()).thenReturn(headers);
        doAnswer((invocationOnMock) -> {
            String key = (String) invocationOnMock.getArguments()[0];
            String value = (String) invocationOnMock.getArguments()[1];
            result.put(key, value);
            return null;
        }).when(headers).putSingle(any(), any());

        new CORSFilter().filter(requestContext, responseContext);

        assertEquals("*", result.get("Access-Control-Allow-Origin"));
        assertEquals("GET, POST, DELETE, PUT, OPTIONS, HEAD", result.get("Access-Control-Allow-Methods"));
        assertEquals("Content-Type, Accept, X-Requested-With, username, password, cashbox", result.get("Access-Control-Allow-Headers"));
    }
}
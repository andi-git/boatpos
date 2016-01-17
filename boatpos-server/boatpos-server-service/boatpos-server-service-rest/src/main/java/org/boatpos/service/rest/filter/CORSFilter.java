package org.boatpos.service.rest.filter;

import org.boatpos.util.log.LogWrapper;
import org.boatpos.util.log.SLF4J;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Filter to add attributes for Cross Origin Resource Sharing  to the response.
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
    }
}

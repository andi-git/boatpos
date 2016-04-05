package org.boatpos.service.rest.filter;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Filter to authenticate the user.
 */
@Authenticated
@Provider
public class AuthenticateUser implements ContainerRequestFilter {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!(System.getProperty("boatpos.username").equals(getUsername(requestContext)) &&
                System.getProperty("boatpos.password").equals(getPassword(requestContext)))) {
            String message = "authorization failure for user '" + getUsername(requestContext) + "', password '" + getPassword(requestContext) + "'";
            log.warn(message);
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity(message).build());
        }
    }

    private String getUsername(ContainerRequestContext requestContext) {
        return String.valueOf(requestContext.getHeaderString("username"));
    }

    private String getPassword(ContainerRequestContext requestContext) {
        return String.valueOf(requestContext.getHeaderString("password"));
    }
}

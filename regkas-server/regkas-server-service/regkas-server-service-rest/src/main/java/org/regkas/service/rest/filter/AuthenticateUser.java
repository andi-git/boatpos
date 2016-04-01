package org.regkas.service.rest.filter;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.service.api.AuthenticationService;
import org.regkas.service.api.bean.CredentialsBean;
import org.regkas.service.api.context.ContextService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;

/**
 * Filter to authenticate the user.
 */
@Authenticated
@Provider
public class AuthenticateUser implements ContainerRequestFilter {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private ContextService contextService;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (authenticationService.authenticate(new CredentialsBean(getUsername(requestContext), getPassword(requestContext), getCashBox(requestContext)))) {
            contextService.initContext(getUsername(requestContext), getCashBox(requestContext));
        } else {
            String message = "Authorization failure for user " + getUsername(requestContext) + " and cash-box " + getCashBox(requestContext);
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

    private String getCashBox(ContainerRequestContext requestContext) {
        return String.valueOf(requestContext.getHeaderString("cashbox"));
    }
}

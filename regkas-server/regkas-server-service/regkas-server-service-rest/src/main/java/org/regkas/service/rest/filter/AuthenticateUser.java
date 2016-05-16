package org.regkas.service.rest.filter;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.service.api.AuthenticationService;
import org.regkas.service.api.bean.CredentialsBean;
import org.regkas.service.api.context.ContextService;

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
public abstract class AuthenticateUser implements ContainerRequestFilter {

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
            String message = "authorization failure for user '" + getUsername(requestContext) + "', password '" + getPassword(requestContext) + "', cash-box '" + getCashBox(requestContext) + "'";
            log.warn(message);
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity(message).build());
        }
    }

    protected abstract String getUsername(ContainerRequestContext requestContext);

    protected abstract String getPassword(ContainerRequestContext requestContext);

    protected abstract String getCashBox(ContainerRequestContext requestContext);
}

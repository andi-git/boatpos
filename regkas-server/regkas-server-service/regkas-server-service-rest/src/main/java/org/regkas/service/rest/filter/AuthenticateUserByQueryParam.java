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
@QueryParamAuthenticated
@Provider
public class AuthenticateUserByQueryParam extends AuthenticateUser {

    @Override
    protected String getUsername(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getQueryParameters().get("username").get(0);
    }

    @Override
    protected String getPassword(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getQueryParameters().get("password").get(0);
    }

    @Override
    protected String getCashBox(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getQueryParameters().get("cashbox").get(0);
    }
}

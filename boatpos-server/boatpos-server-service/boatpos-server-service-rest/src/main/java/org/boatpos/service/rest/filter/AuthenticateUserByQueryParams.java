package org.boatpos.service.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;

@QueryParamAuthenticated
@Provider
public class AuthenticateUserByQueryParams extends AuthenticateUser {

    @Override
    protected String getUsername(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getQueryParameters().get("username").get(0);
    }

    @Override
    protected String getPassword(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getQueryParameters().get("password").get(0);
    }
}

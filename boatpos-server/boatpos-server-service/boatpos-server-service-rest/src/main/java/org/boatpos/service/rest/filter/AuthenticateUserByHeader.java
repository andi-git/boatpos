package org.boatpos.service.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;

@HeaderAuthenticated
@Provider
public class AuthenticateUserByHeader extends AuthenticateUser {

    @Override
    protected String getUsername(ContainerRequestContext requestContext) {
        return String.valueOf(requestContext.getHeaderString("username"));
    }

    @Override
    protected String getPassword(ContainerRequestContext requestContext) {
        return String.valueOf(requestContext.getHeaderString("password"));
    }
}

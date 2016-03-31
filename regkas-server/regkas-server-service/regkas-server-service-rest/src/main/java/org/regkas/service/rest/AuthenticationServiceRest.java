package org.regkas.service.rest;

import org.boatpos.common.service.rest.RestHelper;
import org.regkas.service.api.AuthenticationService;
import org.regkas.service.api.bean.CredentialsBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/authenticate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationServiceRest {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private RestHelper restHelper;

    @GET
    @Path("/{username}/{password}/{cashbox}")
    public Response authenticate(@PathParam("username") String username, @PathParam("password") String password, @PathParam("cashbox") String cashbox) {
        if (authenticationService.authenticate(new CredentialsBean(username, password, cashbox))) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}

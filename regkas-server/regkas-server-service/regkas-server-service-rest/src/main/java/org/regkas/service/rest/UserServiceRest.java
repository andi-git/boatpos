package org.regkas.service.rest;

import org.boatpos.common.service.rest.RestHelper;
import org.regkas.service.api.UserService;
import org.regkas.service.api.bean.CredentialsBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserServiceRest {

    @Inject
    private UserService userService;

    @Inject
    private RestHelper restHelper;

    @GET
    @Path("/authenticate/{username}/{password}")
    public Response authenticate(@PathParam("username") String username, @PathParam("password") String password) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        if (userService.authenticate(new CredentialsBean(username, password))) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}

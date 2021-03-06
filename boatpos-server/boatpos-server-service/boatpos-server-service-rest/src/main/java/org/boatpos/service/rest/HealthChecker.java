package org.boatpos.service.rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Get a ping from the web-app.
 */
@Stateless
@Path("")
@Produces(MediaType.TEXT_PLAIN)
public class HealthChecker {

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok("OK").build();
    }
}
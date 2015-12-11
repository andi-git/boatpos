package org.boatpos.service.rest;

import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.bean.BoatBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Stateless
@Path("/boat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BoatServiceRest {

    @Inject
    private BoatService boatService;

    @GET
    @Path("/name/{name:[a-zA-ZäöüÄÖÜß_\\-]*}")
    public Response getByName(@PathParam("name") String name) {
        Optional<BoatBean> boat = boatService.getByName(name);
        if (!boat.isPresent()) {
            boat = boatService.getByShortName(name);
        }
        if (boat.isPresent()) {
            return Response.ok(boat.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(boatService.getAll()).build();
    }
}

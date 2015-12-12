package org.boatpos.service.rest;

import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.EnabledState;
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
    @Path("id/{id:[0-9]*}")
    public Response getById(@PathParam("id") Long id) {
        Optional<BoatBean> boat = boatService.getById(id);
        if (boat.isPresent()) {
            return Response.ok(boat.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

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

    @GET
    @Path("/enabled")
    public Response getAllEnabled() {
        return Response.ok(boatService.getAll(EnabledState.Enabled)).build();
    }

    @GET
    @Path("/disabled")
    public Response getAllDisabled() {
        return Response.ok(boatService.getAll(EnabledState.Disabled)).build();
    }

    @POST
    @Path("/")
    public Response save(BoatBean boat) {
        BoatBean savedBoat = boatService.save(boat);
        return Response
                .status(Response.Status.CREATED)
                .entity(savedBoat)
                .build();
    }

    @PUT
    @Path("/")
    public Response update(BoatBean boat) {
        BoatBean updatedBoat = boatService.update(boat);
        return Response
                .status(Response.Status.CREATED)
                .entity(updatedBoat)
                .build();
    }

    @PUT
    @Path("/enable/{id:[0-9]*}")
    public Response enable(@PathParam("id") Long id) {
        boatService.enable(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/disable/{id:[0-9]*}")
    public Response disable(@PathParam("id") Long id) {
        boatService.disable(id);
        return Response.ok().build();
    }
}

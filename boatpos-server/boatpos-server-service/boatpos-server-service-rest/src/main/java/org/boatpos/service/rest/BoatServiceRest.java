package org.boatpos.service.rest;

import org.boatpos.common.service.rest.RestHelper;
import org.boatpos.service.api.BoatService;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.rest.filter.Authenticated;

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
@Authenticated
public class BoatServiceRest {

    @Inject
    private BoatService boatService;

    @Inject
    private RestHelper restHelper;

    @GET
    @Path("id/{id:[0-9]*}")
    public Response getById(@PathParam("id") Long id) {
        return restHelper.responseOkOrNotFound(boatService.getById(id));
    }

    @GET
    @Path("/name/{name}")
    public Response getByName(@PathParam("name") String name) {
        Optional<BoatBean> boat = boatService.getByName(name);
        if (!boat.isPresent()) {
            boat = boatService.getByShortName(name);
        }
        return restHelper.responseOkOrNotFound(boat);
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
        return restHelper.responseCreated(boatService.save(boat));
    }

    @PUT
    @Path("/")
    public Response update(BoatBean boat) {
        return restHelper.responseCreated(boatService.update(boat));
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

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(boatService.countBoats()).build();
    }
}

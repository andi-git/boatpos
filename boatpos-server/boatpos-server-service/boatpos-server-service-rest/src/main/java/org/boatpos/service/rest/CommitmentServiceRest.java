package org.boatpos.service.rest;

import org.boatpos.service.api.CommitmentService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.CommitmentBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/commitment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommitmentServiceRest {

    @Inject
    private CommitmentService commitmentService;

    @Inject
    private RestHelper restHelper;

    @GET
    @Path("id/{id:[0-9]*}")
    public Response getById(@PathParam("id") Long id) {
        return restHelper.responseOkOrNotFound(commitmentService.getById(id));
    }

    @GET
    @Path("/name/{name}")
    public Response getByName(@PathParam("name") String name) {
        return restHelper.responseOkOrNotFound(commitmentService.getByName(name));
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(commitmentService.getAll()).build();
    }

    @GET
    @Path("/enabled")
    public Response getAllEnabled() {
        return Response.ok(commitmentService.getAll(EnabledState.Enabled)).build();
    }

    @GET
    @Path("/disabled")
    public Response getAllDisabled() {
        return Response.ok(commitmentService.getAll(EnabledState.Disabled)).build();
    }

    @POST
    @Path("/")
    public Response save(CommitmentBean commitment) {
        return restHelper.responseCreated(commitmentService.save(commitment));
    }

    @PUT
    @Path("/")
    public Response update(CommitmentBean commitment) {
        return restHelper.responseCreated(commitmentService.update(commitment));
    }

    @PUT
    @Path("/enable/{id:[0-9]*}")
    public Response enable(@PathParam("id") Long id) {
        commitmentService.enable(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/disable/{id:[0-9]*}")
    public Response disable(@PathParam("id") Long id) {
        commitmentService.disable(id);
        return Response.ok().build();
    }
}

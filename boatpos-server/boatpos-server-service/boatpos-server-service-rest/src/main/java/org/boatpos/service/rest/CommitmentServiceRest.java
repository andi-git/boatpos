package org.boatpos.service.rest;

import org.boatpos.service.api.CommitmentService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.CommitmentBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Stateless
@Path("/commitment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommitmentServiceRest {

    @Inject
    private CommitmentService commitmentService;

    @GET
    @Path("id/{id:[0-9]*}")
    public Response getById(@PathParam("id") Long id) {
        Optional<CommitmentBean> commitment = commitmentService.getById(id);
        if (commitment.isPresent()) {
            return Response.ok(commitment.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/name/{name:[a-zA-ZäöüÄÖÜß_\\-]*}")
    public Response getByName(@PathParam("name") String name) {
        Optional<CommitmentBean> commitment = commitmentService.getByName(name);
        if (commitment.isPresent()) {
            return Response.ok(commitment.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
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
        CommitmentBean updatedCommitment = commitmentService.save(commitment);
        return Response
                .status(Response.Status.CREATED)
                .entity(updatedCommitment)
                .build();
    }

    @PUT
    @Path("/")
    public Response update(CommitmentBean commitment) {
        CommitmentBean updatedCommitment = commitmentService.update(commitment);
        return Response
                .status(Response.Status.CREATED)
                .entity(updatedCommitment)
                .build();
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

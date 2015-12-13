package org.boatpos.service.rest;

import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.PromotionService;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/promotion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PromotionServiceRest {

    @Inject
    private PromotionService promotionService;

    @Inject
    private RestHelper restHelper;

    @GET
    @Path("id/{id:[0-9]*}")
    public Response getById(@PathParam("id") Long id) {
        return restHelper.responseOkOrNotFound(promotionService.getById(id));
    }

    @GET
    @Path("/name/{name}")
    public Response getByName(@PathParam("name") String name) {
        return restHelper.responseOkOrNotFound(promotionService.getByName(name));
    }

    @GET
    @Path("/")
    public Response getAll() {
        return Response.ok(promotionService.getAll()).build();
    }

    @GET
    @Path("/enabled")
    public Response getAllEnabled() {
        return Response.ok(promotionService.getAll(EnabledState.Enabled)).build();
    }

    @GET
    @Path("/disabled")
    public Response getAllDisabled() {
        return Response.ok(promotionService.getAll(EnabledState.Disabled)).build();
    }

    @GET
    @Path("/before")
    public Response getAllBefore() {
        return Response.ok(promotionService.getAllBeforeRental(EnabledState.All)).build();
    }

    @GET
    @Path("/before/{state:[a-z]*}")
    public Response getAllBefore(@PathParam("state") String state) {
        return Response.ok(promotionService.getAllBeforeRental(mapInputToEnabledState(state))).build();
    }

    @GET
    @Path("/after")
    public Response getAllAfter() {
        return Response.ok(promotionService.getAllAfterRental(EnabledState.All)).build();
    }

    @GET
    @Path("/after/{state:[a-z]*}")
    public Response getAllAfter(@PathParam("state") String state) {
        return Response.ok(promotionService.getAllAfterRental(mapInputToEnabledState(state))).build();
    }

    private EnabledState mapInputToEnabledState(@PathParam("state") String state) {
        EnabledState enabledState = EnabledState.All;
        if ("enabled".equals(state)) {
            enabledState = EnabledState.Enabled;
        } else if ("disabled".equals(state)) {
            enabledState = EnabledState.Disabled;
        }
        return enabledState;
    }

    @POST
    @Path("/after")
    public Response savePromotionAfter(PromotionAfterBean promotion) {
        return restHelper.responseCreated(promotionService.save(promotion));
    }

    @POST
    @Path("/before")
    public Response savePromotionBefore(PromotionBeforeBean promotion) {
        return restHelper.responseCreated(promotionService.save(promotion));
    }

    @PUT
    @Path("/after")
    public Response updatePromotionAfter(PromotionAfterBean promotion) {
        return restHelper.responseCreated(promotionService.update(promotion));
    }

    @PUT
    @Path("/before")
    public Response updatePromotionBefore(PromotionBeforeBean promotion) {
        return restHelper.responseCreated(promotionService.update(promotion));
    }

    @PUT
    @Path("/enable/{id:[0-9]*}")
    public Response enable(@PathParam("id") Long id) {
        promotionService.enable(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/disable/{id:[0-9]*}")
    public Response disable(@PathParam("id") Long id) {
        promotionService.disable(id);
        return Response.ok().build();
    }
}

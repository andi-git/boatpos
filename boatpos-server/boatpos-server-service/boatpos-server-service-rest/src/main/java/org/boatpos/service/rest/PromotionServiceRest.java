package org.boatpos.service.rest;

import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.rest.RestHelper;
import org.boatpos.service.api.PromotionAfterService;
import org.boatpos.service.api.PromotionBeforeService;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.boatpos.service.rest.filter.HeaderAuthenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/promotion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@HeaderAuthenticated
public class PromotionServiceRest {

    @Inject
    private PromotionBeforeService promotionBeforeService;

    @Inject
    private PromotionAfterService promotionAfterService;

    @Inject
    private RestHelper restHelper;

    @GET
    @Path("/before/id/{id:[0-9]*}")
    public Response beforeGetById(@PathParam("id") Long id) {
        return restHelper.responseOkOrNotFound(promotionBeforeService.getById(id));
    }

    @GET
    @Path("/after/id/{id:[0-9]*}")
    public Response afterGetById(@PathParam("id") Long id) {
        return restHelper.responseOkOrNotFound(promotionAfterService.getById(id));
    }

    @GET
    @Path("/before/name/{name}")
    public Response beforeGetByName(@PathParam("name") String name) {
        return restHelper.responseOkOrNotFound(promotionBeforeService.getByName(name));
    }

    @GET
    @Path("/after/name/{name}")
    public Response afterGetByName(@PathParam("name") String name) {
        return restHelper.responseOkOrNotFound(promotionAfterService.getByName(name));
    }

    @GET
    @Path("/before")
    public Response beforeGetAll() {
        return Response.ok(promotionBeforeService.getAll(EnabledState.All)).build();
    }

    @GET
    @Path("/before/{state:[a-z]*}")
    public Response beforeGetAll(@PathParam("state") String state) {
        return Response.ok(promotionBeforeService.getAll(mapInputToEnabledState(state))).build();
    }

    @GET
    @Path("/after")
    public Response afterGetAll() {
        return Response.ok(promotionAfterService.getAll(EnabledState.All)).build();
    }

    @GET
    @Path("/after/{state:[a-z]*}")
    public Response afterGetAll(@PathParam("state") String state) {
        return Response.ok(promotionAfterService.getAll(mapInputToEnabledState(state))).build();
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
    public Response afterSavePromotion(PromotionAfterBean promotion) {
        return restHelper.responseCreated(promotionAfterService.save(promotion));
    }

    @POST
    @Path("/before")
    public Response beforeSavePromotion(PromotionBeforeBean promotion) {
        return restHelper.responseCreated(promotionBeforeService.save(promotion));
    }

    @PUT
    @Path("/after")
    public Response afterUpdatePromotion(PromotionAfterBean promotion) {
        return restHelper.responseCreated(promotionAfterService.update(promotion));
    }

    @PUT
    @Path("/before")
    public Response beforeUpdatePromotion(PromotionBeforeBean promotion) {
        return restHelper.responseCreated(promotionBeforeService.update(promotion));
    }

    @PUT
    @Path("/before/enable/{id:[0-9]*}")
    public Response beforeEnable(@PathParam("id") Long id) {
        promotionBeforeService.enable(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/before/disable/{id:[0-9]*}")
    public Response beforeDisable(@PathParam("id") Long id) {
        promotionBeforeService.disable(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/after/enable/{id:[0-9]*}")
    public Response afterEnable(@PathParam("id") Long id) {
        promotionAfterService.enable(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/after/disable/{id:[0-9]*}")
    public Response afterDisable(@PathParam("id") Long id) {
        promotionAfterService.disable(id);
        return Response.ok().build();
    }
}

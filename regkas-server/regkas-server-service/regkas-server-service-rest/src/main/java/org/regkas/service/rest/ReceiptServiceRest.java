package org.regkas.service.rest;

import org.regkas.service.api.ReceiptService;
import org.regkas.service.api.context.ContextService;
import org.regkas.service.rest.filter.HeaderAuthenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/receipt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@HeaderAuthenticated
public class ReceiptServiceRest {

    @Inject
    private ReceiptService receiptService;

    @Inject
    private ContextService contextService;

    @GET
    @Path("/start/check")
    public Response printStart() {
        return Response.ok(!receiptService.isStartReceiptCreated()).build();
    }

    @GET
    @Path("/schluss/check")
    public Response printSchluss() {
        return Response.ok(!receiptService.isSchlussReceiptCreated()).build();
    }

    @GET
    @Path("/month/check")
    public Response printMonth() {
        return Response.ok(receiptService.shouldCreateMonthReceipt()).build();
    }

    @GET
    @Path("/environment")
    public Response getEnvironment() {
        return Response.ok(receiptService.getCurrentRkOnlineEnvironment()).build();
    }

    @PUT
    @Path("/environment/test")
    public Response setEnvironmentTest() {
        receiptService.setRkOnlineEnvironment("test");
        return Response.ok(receiptService.getCurrentRkOnlineEnvironment()).build();
    }

    @PUT
    @Path("/environment/prod")
    public Response setEnvironmentProd() {
        receiptService.setRkOnlineEnvironment("prod");
        return Response.ok(receiptService.getCurrentRkOnlineEnvironment()).build();
    }
}

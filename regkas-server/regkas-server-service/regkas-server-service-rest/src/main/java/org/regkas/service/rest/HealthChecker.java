package org.regkas.service.rest;

import org.regkas.service.api.JournalService;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Inject
    private JournalService journalService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok("OK").build();
    }

    @GET
    @Path("/update/receipts")
    public Response updateReceipt() {
        return Response.ok("updated receipts: " + journalService.updateReceipts()).build();
    }
}
package org.boatpos.service.rest;

import org.boatpos.service.api.JournalService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/journal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JournalServiceRest {

    @Inject
    private JournalService journalService;

    @GET
    @Path("/income/{year:[0-9]*}")
    public Response totalIncome(@PathParam("year") Integer year) {
        return Response.ok(journalService.totalIncomeFor(year)).build();
    }

    @GET
    @Path("/income/{year:[0-9]*}/{month:[0-9]*}")
    public Response totalIncome(@PathParam("year") Integer year, @PathParam("month") Integer month) {
        return Response.ok(journalService.totalIncomeFor(year, month)).build();
    }

    @GET
    @Path("/income/{year:[0-9]*}/{month:[0-9]*}/{day:[0-9]*}")
    public Response totalIncome(@PathParam("year") Integer year, @PathParam("month") Integer month, @PathParam("day") Integer day) {
        return Response.ok(journalService.totalIncomeFor(year, month, day)).build();
    }
}
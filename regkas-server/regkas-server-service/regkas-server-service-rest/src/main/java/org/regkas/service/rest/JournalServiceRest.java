package org.regkas.service.rest;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.regkas.service.api.JournalService;
import org.regkas.service.rest.filter.Authenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/journal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class JournalServiceRest {

    @Inject
    private JournalService journalService;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @GET
    @Path("/income/year")
    public Response totalIncomeCurrentYear() {
        return Response.ok(journalService.totalIncomeFor(dateTimeHelper.currentDate().getYear())).build();
    }

    @GET
    @Path("/income/month")
    public Response totalIncomeCurrentMonth() {
        return Response.ok(journalService.totalIncomeFor(dateTimeHelper.currentDate().getYear(), dateTimeHelper.currentDate().getMonthValue())).build();
    }

    @GET
    @Path("/income/day")
    public Response totalIncomeCurrentDay() {
        return Response.ok(journalService.totalIncomeFor(dateTimeHelper.currentDate().getYear(), dateTimeHelper.currentDate().getMonthValue(), dateTimeHelper.currentDate().getDayOfMonth())).build();
    }

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

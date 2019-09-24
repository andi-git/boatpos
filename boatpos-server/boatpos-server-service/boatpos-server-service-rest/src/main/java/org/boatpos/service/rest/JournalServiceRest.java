package org.boatpos.service.rest;

import org.boatpos.common.service.rest.RestHelper;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.service.api.JournalService;
import org.boatpos.service.rest.filter.HeaderAuthenticated;
import org.boatpos.service.rest.filter.QueryParamAuthenticated;

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

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private RestHelper restHelper;

    @GET
    @Path("/income/year")
    @HeaderAuthenticated
    public Response totalIncomeCurrentYear() {
        return Response.ok(journalService.totalIncomeFor(dateTimeHelper.currentDate().getYear())).build();
    }

    @GET
    @Path("/income/month")
    @HeaderAuthenticated
    public Response totalIncomeCurrentMonth() {
        return Response.ok(journalService.totalIncomeFor(dateTimeHelper.currentDate().getYear(), dateTimeHelper.currentDate().getMonthValue())).build();
    }

    @GET
    @Path("/income/day")
    @HeaderAuthenticated
    public Response totalIncomeCurrentDay() {
        return Response.ok(journalService.totalIncomeFor(dateTimeHelper.currentDate().getYear(), dateTimeHelper.currentDate().getMonthValue(), dateTimeHelper.currentDate().getDayOfMonth())).build();
    }

    @GET
    @Path("/income/{year:[0-9]*}")
    @HeaderAuthenticated
    public Response totalIncome(@PathParam("year") Integer year) {
        return Response.ok(journalService.totalIncomeFor(year)).build();
    }

    @GET
    @Path("/income/{year:[0-9]*}/{month:[0-9]*}")
    @HeaderAuthenticated
    public Response totalIncome(@PathParam("year") Integer year, @PathParam("month") Integer month) {
        return Response.ok(journalService.totalIncomeFor(year, month)).build();
    }

    @GET
    @Path("/income/{year:[0-9]*}/{month:[0-9]*}/{day:[0-9]*}")
    @HeaderAuthenticated
    public Response totalIncome(@PathParam("year") Integer year, @PathParam("month") Integer month, @PathParam("day") Integer day) {
        return Response.ok(journalService.totalIncomeFor(year, month, day)).build();
    }

    @GET
    @Path("/dep/{year:[0-9]*}")
    @QueryParamAuthenticated
    @Produces({"application/zip"})
    public Response datenErfassungsProtokoll(@PathParam("year") Integer year) {
        return restHelper.createZipOutput(journalService.datenErfassungsProtokoll(year));
    }

    @GET
    @Path("/dep/{year:[0-9]*}/{month:[0-9]*}")
    @QueryParamAuthenticated
    @Produces({"application/zip"})
    public Response datenErfassungsProtokoll(@PathParam("year") Integer year, @PathParam("month") Integer month) {
        return restHelper.createZipOutput(journalService.datenErfassungsProtokoll(year, month));
    }

    @GET
    @Path("/dep/{year:[0-9]*}/{month:[0-9]*}/{day:[0-9]*}")
    @QueryParamAuthenticated
    @Produces({"application/zip"})
    public Response datenErfassungsProtokoll(@PathParam("year") Integer year, @PathParam("month") Integer month, @PathParam("day") Integer day) {
        return restHelper.createZipOutput(journalService.datenErfassungsProtokoll(year, month, day));
    }

    @GET
    @Path("/dep/latest")
    @QueryParamAuthenticated
    @Produces("application/zip")
    public Response latestDatenErfassungsProtokollRKV2012() {
        return restHelper.createZipOutput(journalService.latestDatenErfassungsProtokollRKV2012());
    }

    @GET
    @Path("/dep/rksv")
    @QueryParamAuthenticated
    @Produces("application/zip")
    public Response datenErfassungsProtokollRKSV() {
        return restHelper.createZipOutput(journalService.datenErfassungsProtokollRKSV());
    }

    @GET
    @Path("/dep/rksv/latest")
    @QueryParamAuthenticated
    @Produces("application/zip")
    public Response latestDatenErfassungsProtokollRKSV() {
        return restHelper.createZipOutput(journalService.latestDatenErfassungsProtokollRKSV());
    }

    @GET
    @Path("/receipt/id/{receiptId}")
    @HeaderAuthenticated
    public Response getReceiptById(@PathParam("receiptId") String receiptId) {
        return Response.ok(journalService.getReceiptById(receiptId)).build();
    }

}

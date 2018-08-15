package org.regkas.service.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.boatpos.common.service.rest.RestHelper;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.regkas.service.api.JournalService;
import org.regkas.service.rest.filter.HeaderAuthenticated;
import org.regkas.service.rest.filter.QueryParamAuthenticated;

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
        return Response
            .ok(journalService.totalIncomeFor(dateTimeHelper.currentDate().getYear(), dateTimeHelper.currentDate().getMonthValue()))
            .build();
    }

    @GET
    @Path("/income/day")
    @HeaderAuthenticated
    public Response totalIncomeCurrentDay() {
        return Response
            .ok(
                journalService.totalIncomeFor(
                    dateTimeHelper.currentDate().getYear(),
                    dateTimeHelper.currentDate().getMonthValue(),
                    dateTimeHelper.currentDate().getDayOfMonth()))
            .build();
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
    @Produces({"application/zip"})
    @QueryParamAuthenticated
    public Response datenErfassungsProtokoll(@PathParam("year") Integer year) {
        return restHelper.createZipOutput(journalService.datenErfassungsProtokollRKV2012(year));
    }

    @GET
    @Path("/dep/{year:[0-9]*}/{month:[0-9]*}")
    @Produces({"application/zip"})
    @QueryParamAuthenticated
    public Response datenErfassungsProtokoll(@PathParam("year") Integer year, @PathParam("month") Integer month) {
        return restHelper.createZipOutput(journalService.datenErfassungsProtokollRKV2012(year, month));
    }

    @GET
    @Path("/dep/{year:[0-9]*}/{month:[0-9]*}/{day:[0-9]*}")
    @Produces("application/zip")
    @QueryParamAuthenticated
    public Response datenErfassungsProtokoll(@PathParam("year") Integer year, @PathParam("month") Integer month, @PathParam("day") Integer day) {
        return restHelper.createZipOutput(journalService.datenErfassungsProtokollRKV2012(year, month, day));
    }

    @GET
    @Path("/dep/latest")
    @Produces("application/zip")
    @QueryParamAuthenticated
    public Response latestDatenErfassungsProtokollRKV2012() {
        return restHelper.createZipOutput(journalService.latestDatenErfassungsProtokollRKV2012());
    }

    @GET
    @Path("/dep/rksv")
    @Produces("application/zip")
    @QueryParamAuthenticated
    public Response datenErfassungsProtokollRKSV() {
        return restHelper.createZipOutput(journalService.datenErfassungsProtokollRKSV());
    }

    @GET
    @Path("/dep/rksv/latest")
    @Produces("application/zip")
    @QueryParamAuthenticated
    public Response latestDatenErfassungsProtokollRKSV() {
        return restHelper.createZipOutput(journalService.latestDatenErfassungsProtokollRKSV());
    }
}

package org.boatpos.service.rest;

import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;
import org.boatpos.service.rest.filter.HeaderAuthenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Stateless
@Path("/rental")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@HeaderAuthenticated
public class RentalServiceRest {

    @Inject
    private RentalService rentalService;

    @GET
    @Path("/currentDay")
    public Response getAllCurrentDay() {
        return Response.ok(rentalService.getAllCurrentDay()).build();
    }

    @GET
    @Path("/{year:[0-9]*}/{month:[0-9]*}/{day:[0-9]*}")
    public Response getAll(@PathParam("year") Integer year, @PathParam("month") Integer month, @PathParam("day") Integer day) {
        return Response.ok(rentalService.getAll(LocalDate.of(year, month, day))).build();
    }

    @GET
    @Path("/{dayId:[0-9]*}")
    public Response get(@PathParam("dayId") Integer dayId) {
        return Response.ok(rentalService.get(new RentalDayNumberWrapper(dayId))).build();
    }

    @DELETE
    @Path("/{dayId:[0-9]*}")
    public Response delete(@PathParam("dayId") Integer dayId) {
        return Response.ok(rentalService.delete(new RentalDayNumberWrapper(dayId))).build();
    }

    @GET
    @Path("/undoDelete/{dayId:[0-9]*}")
    public Response undoDelete(@PathParam("dayId") Integer dayId) {
        return Response.ok(rentalService.undoDelete(new RentalDayNumberWrapper(dayId))).build();
    }

    @GET
    @Path("nextId")
    public Response nextDayId() {
        return Response.ok(rentalService.nextDayId()).build();
    }
}

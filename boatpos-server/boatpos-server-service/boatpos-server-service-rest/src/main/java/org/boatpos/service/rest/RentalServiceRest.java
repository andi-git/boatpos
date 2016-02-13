package org.boatpos.service.rest;

import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/rental")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RentalServiceRest {

    @Inject
    private RentalService rentalService;

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

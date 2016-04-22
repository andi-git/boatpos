package org.boatpos.service.rest;

import org.boatpos.service.api.PrinterService;
import org.boatpos.service.api.bean.IpAddressBean;
import org.boatpos.service.rest.filter.Authenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/printer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class PrinterServiceRest {

    @Inject
    private PrinterService printerService;

    @GET
    @Path("")
    public Response load() {
        return Response.ok(printerService.load()).build();
    }

    @POST
    @Path("")
    public Response save(IpAddressBean ipAddress) {
        printerService.save(ipAddress);
        return Response.ok().build();
    }
}

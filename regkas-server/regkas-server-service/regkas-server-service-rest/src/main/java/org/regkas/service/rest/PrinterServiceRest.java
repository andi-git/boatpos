package org.regkas.service.rest;

import org.regkas.service.api.PrinterService;
import org.regkas.service.api.bean.IpAddressBean;
import org.regkas.service.rest.filter.Authenticated;

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
    @Path("/ip")
    public Response load() {
        return Response.ok(printerService.loadIp()).build();
    }

    @POST
    @Path("/ip")
    public Response save(IpAddressBean ipAddress) {
        printerService.saveIp(ipAddress);
        return Response.ok().build();
    }
}

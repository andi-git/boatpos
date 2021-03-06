package org.regkas.service.rest;

import org.regkas.service.api.ProductGroupService;
import org.regkas.service.rest.filter.HeaderAuthenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/productgroup")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@HeaderAuthenticated
public class ProductGroupServiceRest {

    @Inject
    private ProductGroupService productGroupService;

    @GET
    @Path("")
    public Response getAll() {
        return Response.ok(productGroupService.getAllForCurrentCompany()).build();
    }

    @GET
    @Path("/{name}")
    public Response getProductGroupByName(@PathParam("name") String name) {
        return Response.ok(productGroupService.getForCurrentCompany(name)).build();
    }

    @GET
    @Path("/{name}/generic")
    public Response getGenericProductFor(@PathParam("name") String name) {
        return Response.ok(productGroupService.getGenericProductFor(name)).build();
    }

}

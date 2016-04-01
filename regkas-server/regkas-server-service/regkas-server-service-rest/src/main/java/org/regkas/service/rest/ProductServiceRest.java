package org.regkas.service.rest;

import org.boatpos.common.service.rest.RestHelper;
import org.regkas.service.api.ProductService;
import org.regkas.service.rest.filter.Authenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ProductServiceRest {

    @Inject
    private ProductService productService;

    @GET
    @Path("")
    public Response getAll() {
        return Response.ok(productService.getAllForCurrentCompany()).build();
    }

    @GET
    @Path("/{name}")
    public Response getProductByName(@PathParam("name") String name) {
        return Response.ok(productService.getForCurrentCompany(name)).build();
    }
}

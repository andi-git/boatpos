package org.regkas.service.rest;

import org.boatpos.common.service.rest.RestHelper;
import org.regkas.service.api.ProductGroupService;
import org.regkas.service.api.ProductService;
import org.regkas.service.rest.filter.Authenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/productgroup")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ProductGroupServiceRest {

    @Inject
    private ProductGroupService productGroupService;

    @GET
    @Path("")
    public Response getAll() {
        return Response.ok(productGroupService.getAllForCurrentCompany()).build();
    }
}

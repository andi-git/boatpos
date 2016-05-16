package org.regkas.service.rest;

import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.api.context.ContextService;
import org.regkas.service.rest.filter.HeaderAuthenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/sale")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@HeaderAuthenticated
public class SaleServiceRest {

    @Inject
    private SaleService saleService;

    @Inject
    private ContextService contextService;

    @POST
    @Path("")
    public Response sale(SaleBean saleBean) {
        return Response.ok(saleService.sale(saleBean)).build();
    }
}

package org.boatpos.service.rest;

import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.DepartureService;
import org.boatpos.service.api.bean.AddPromotionBean;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.DepartureBean;
import org.boatpos.service.api.bean.PaymentBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.google.common.base.Preconditions.checkNotNull;

@Stateless
@Path("/departure")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartureServiceRest {

    @Inject
    private DepartureService departureService;

    @Inject
    private RestHelper restHelper;

    @POST
    @Path("/depart")
    public Response depart(DepartureBean departureBean) {
        checkNotNull(departureBean, "'departureBean' must not be null");
        return Response.ok(departureService.depart(departureBean)).build();
    }

    @POST
    @Path("/pay")
    public Response pay(PaymentBean paymentBean) {
        checkNotNull(paymentBean, "'paymentBean' must not be null");
        return Response.ok(departureService.pay(paymentBean)).build();
    }
}

package org.boatpos.service.rest;

import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.bean.AddPromotionBean;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.api.bean.PaymentBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Stateless
@Path("/arrival")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArrivalServiceRest {

    @Inject
    private ArrivalService arrivalService;

    @Inject
    private RestHelper restHelper;

    @POST
    @Path("/arrive")
    public Response getById(ArrivalBean arrivalBean) {
        checkNotNull(arrivalBean, "'arrivalBean' must not be null");
        return Response.ok(arrivalService.arrive(arrivalBean)).build();
    }

    @POST
    @Path("/promotion")
    public Response getByName(AddPromotionBean addPromotionBean) {
        checkNotNull(addPromotionBean, "'addPromotion' must not be null");
        return Response.ok(arrivalService.addPromotion(addPromotionBean)).build();
    }

    @POST
    @Path("/pay")
    public Response pay(PaymentBean paymentBean) {
        checkNotNull(paymentBean, "'paymentBean' must not be null");
        return Response.ok(arrivalService.pay(paymentBean)).build();
    }
}
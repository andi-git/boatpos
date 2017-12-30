package org.boatpos.service.rest;

import org.boatpos.common.service.rest.RestHelper;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.bean.AddPromotionBean;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.PaymentBean;
import org.boatpos.service.api.bean.RemovePromotionsAfterBean;
import org.boatpos.service.rest.filter.HeaderAuthenticated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.google.common.base.Preconditions.checkNotNull;

@Stateless
@Path("/arrival")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@HeaderAuthenticated
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
    public Response addPromotion(AddPromotionBean addPromotionBean) {
        checkNotNull(addPromotionBean, "'addPromotion' must not be null");
        return Response.ok(arrivalService.addPromotion(addPromotionBean)).build();
    }

    @PUT
    @Path("/promotion")
    public Response removePromotions(RemovePromotionsAfterBean removePromotionsAfterBean) {
        checkNotNull(removePromotionsAfterBean, "'removePromotionsAfterBean' must not be null");
        return Response.ok(arrivalService.removePromotionsAfter(removePromotionsAfterBean)).build();
    }

    @POST
    @Path("/pay")
    public Response pay(PaymentBean paymentBean) {
        checkNotNull(paymentBean, "'paymentBean' must not be null");
        return Response.ok(arrivalService.pay(paymentBean)).build();
    }

    @POST
    @Path("/receipt")
    public Response receipt(String receiptType) {
        checkNotNull(receiptType, "'receiptType' must not be null");
        return Response.ok(arrivalService.receipt(receiptType)).build();
    }

    @GET
    @Path("/signatureDeviceAvailable")
    public Response signatureDeviceAvailable() {
        return Response.ok(arrivalService.isSignatureDeviceAvailable()).build();
    }

    @GET
    @Path("/start/check")
    public Response checkIfStartbelegMustBePrinted() {
        return Response.ok(arrivalService.checkIfStartbelegMustBePrinted()).build();
    }

    @GET
    @Path("/schluss/check")
    public Response checkIfSchlussbelegCanBePrinted() {
        return Response.ok(arrivalService.checkIfSchlussbelegCanBePrinted()).build();
    }
}

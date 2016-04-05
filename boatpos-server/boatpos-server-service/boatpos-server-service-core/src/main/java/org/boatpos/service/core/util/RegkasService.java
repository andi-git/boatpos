package org.boatpos.service.core.util;

import com.google.common.collect.Lists;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.DayId;
import org.boatpos.service.api.bean.PaymentBean;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Function;

/**
 * A facade for all regkas-services.
 */
@Dependent
public class RegkasService {

    @Inject
    private RentalLoader rentalLoader;

    public ProductBean getProduct(Boat boat) throws Exception {
        return readEntity(createRestCall(webTarget -> webTarget.path("product").path(boat.getName().get())).get(), ProductBean.class);
    }

    public BillBean sale(PaymentBean paymentBean) throws Exception {
        Rental rental = rentalLoader.loadOnCurrentDayBy(new DayId(paymentBean.getDayNumber()));
        ProductBean productBean = getProduct(rental.getBoat());
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod(paymentBean.getPaymentMethod());
        sale.setReceiptType("Standard-Beleg");
        sale.setSaleElements(Lists.newArrayList(
                new ReceiptElementBean(productBean, 1, rental.getPricePaidComplete().get()))
        );
        return readEntity(createRestCall(webTarget -> webTarget.path("sale")).post(Entity.json(sale)), BillBean.class);
    }

    public <T> T readEntity(Response response, Class<T> type) {
        if (Response.Status.OK.getStatusCode() != response.getStatus()) {
            throw new RuntimeException("error on calling regkas-service: " + response.getStatus());
        }
        return response.readEntity(type);
    }

    public Invocation.Builder addCredentials(Invocation.Builder builder) throws Exception {
        return builder
                .header("username", System.getProperty("boatpos.regkas.service.username"))
                .header("password", System.getProperty("boatpos.regkas.service.password"))
                .header("cashbox", System.getProperty("boatpos.regkas.service.cashbox"));
    }

    public Invocation.Builder createRestCall(Function<WebTarget, WebTarget> addPath) throws Exception {
        WebTarget webTarget = ClientBuilder.newClient().target(System.getProperty("boatpos.regkas.service.rest"));
        webTarget = addPath.apply(webTarget);
        return addCredentials(webTarget.request().accept(MediaType.APPLICATION_JSON));
    }
}

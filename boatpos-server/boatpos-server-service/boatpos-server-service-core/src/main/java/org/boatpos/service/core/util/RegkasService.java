package org.boatpos.service.core.util;

import com.google.common.collect.Lists;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
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
import java.io.*;
import java.util.function.Function;

/**
 * A facade for all regkas-services.
 */
@Dependent
public class RegkasService {

    @Inject
    private RentalLoader rentalLoader;

    @Inject
    @SLF4J
    private LogWrapper log;

    public ProductBean getProduct(Boat boat) throws Exception {
        return readEntity(createRestCall(webTarget -> webTarget.path("rest/product").path(boat.getName().get())).get(), ProductBean.class);
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
        return readEntity(createRestCall(webTarget -> webTarget.path("rest/sale")).post(Entity.json(sale)), BillBean.class);
    }

    public File getDEP(int year) {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/" + year)).get());
    }

    public File getDEP(int year, int month) {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/" + year + "/" + month)).get());
    }

    public File getDEP(int year, int month, int day) {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/" + year + "/" + month + "/" + day)).get());
    }

    File convert(Response response) {
        return writeToFile(readEntity(response, InputStream.class), new File(System.getProperty("java.io.tmpdir"), getFileNameFromContentDisposition(response)));
    }

    String getFileNameFromContentDisposition(Response response) {
        String contentDisposition = (String) response.getHeaders().get("Content-Disposition").get(0);
        return contentDisposition.substring(contentDisposition.indexOf('"') + 1, contentDisposition.length() - 1);
    }

    File writeToFile(InputStream inputStream, File file) {
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    private <T> T readEntity(Response response, Class<T> type) {
        if (Response.Status.OK.getStatusCode() != response.getStatus()) {
            throw new RuntimeException("error on calling regkas-service: " + response.getStatus());
        }
        return response.readEntity(type);
    }

    Invocation.Builder addCredentials(Invocation.Builder builder) {
        return builder
                .header("username", getNotNullableSystemProperty("boatpos.regkas.service.username"))
                .header("password", getNotNullableSystemProperty("boatpos.regkas.service.password"))
                .header("cashbox", getNotNullableSystemProperty("boatpos.regkas.service.cashbox"));
    }

    Invocation.Builder createRestCall(Function<WebTarget, WebTarget> addPath) {
        WebTarget webTarget = ClientBuilder.newClient().target(getNotNullableSystemProperty("boatpos.regkas.service.rest"));
        webTarget = addPath.apply(webTarget);
        return addCredentials(webTarget.request().accept(MediaType.APPLICATION_JSON));
    }

    private String getNotNullableSystemProperty(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            throw new RuntimeException("unable to get system-property-value for key: '" + key + "'");
        }
        return value;
    }
}

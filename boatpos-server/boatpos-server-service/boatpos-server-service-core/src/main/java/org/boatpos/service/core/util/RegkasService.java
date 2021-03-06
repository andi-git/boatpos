package org.boatpos.service.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Function;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.values.DayId;
import org.boatpos.domain.api.values.PricePaidComplete;
import org.boatpos.domain.api.values.ReceiptId;
import org.boatpos.service.api.bean.PaymentBean;
import org.regkas.service.api.bean.*;

import com.google.common.collect.Lists;

/**
 * A facade for all regkas-services.
 */
@Dependent
public class RegkasService {

    private static final MediaType MEDIA_TYPE_ZIP = new MediaType("application", "zip");

    @Inject
    private RentalLoader rentalLoader;

    @Inject
    @SLF4J
    private LogWrapper log;

    private ProductBean getProduct(Boat boat) {
        return readEntity(
            createRestCall(webTarget -> webTarget.path("rest/product").path(boat.getName().get()), MediaType.APPLICATION_JSON_TYPE).get(),
            ProductBean.class);
    }

    public BillBean sale(PaymentBean paymentBean) throws Exception {
        Rental rental = rentalLoader.loadOnCurrentDayBy(new DayId(paymentBean.getDayNumber()));
        ProductBean productBean = getProduct(rental.getBoat());
        return receipt(createSaleBean(paymentBean, rental.getPricePaidComplete(), productBean));
    }

    public SaleBean createSaleBean(PaymentBean paymentBean, PricePaidComplete pricePaidComplete, ProductBean productBean) {
        SaleBean sale = new SaleBean();
        sale.setPaymentMethod(paymentBean.getPaymentMethod());
        sale.setReceiptType(paymentBean.getReceiptType());
        sale.setSaleElements(Lists.newArrayList(new ReceiptElementBean(productBean, 1, pricePaidComplete.get())));
        return sale;
    }

    public BillBean receipt(SaleBean sale) {
        return readEntity(
            createRestCall(webTarget -> webTarget.path("rest/sale"), MediaType.APPLICATION_JSON_TYPE).post(Entity.json(sale)),
            BillBean.class);
    }

    public BillBean receiptById(ReceiptId receiptId) {
        return readEntity(
            createRestCall(webTarget -> webTarget.path("rest/receipt/id/" + receiptId.get()), MediaType.APPLICATION_JSON_TYPE).get(),
            BillBean.class);
    }

    public IncomeBean totalIncome(int year) {
        return readEntity(createRestCall(webTarget -> webTarget.path("rest/journal/income/" + year),  MediaType.APPLICATION_JSON_TYPE).get(), IncomeBean.class);
    }

    public IncomeBean totalIncome(int year, int month) {
        return readEntity(createRestCall(webTarget -> webTarget.path("rest/journal/income/" + year + "/" + month),  MediaType.APPLICATION_JSON_TYPE).get(), IncomeBean.class);
    }

    public IncomeBean totalIncome(int year, int month, int day) {
        return readEntity(createRestCall(webTarget -> webTarget.path("rest/journal/income/" + year + "/" + month + "/" + day),  MediaType.APPLICATION_JSON_TYPE).get(), IncomeBean.class);
    }

    public File getDEP(int year) {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/" + year), MEDIA_TYPE_ZIP).get());
    }

    public File getDEP(int year, int month) {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/" + year + "/" + month), MEDIA_TYPE_ZIP).get());
    }

    public File getDEP(int year, int month, int day) {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/" + year + "/" + month + "/" + day), MEDIA_TYPE_ZIP).get());
    }

    public File getDEPRKSV() {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/rksv"), MEDIA_TYPE_ZIP).get());
    }

    public File getLatestDEPRKSV2012() {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/latest"), MEDIA_TYPE_ZIP).get());
    }

    public File getLatestDEPRKSV() {
        return convert(createRestCall(webTarget -> webTarget.path("rest/journal/dep/rksv/latest"), MEDIA_TYPE_ZIP).get());
    }

    File convert(Response response) {
        return writeToFile(
            readEntity(response, InputStream.class),
            new File(System.getProperty("java.io.tmpdir"), getFileNameFromContentDisposition(response)));
    }

    String getFileNameFromContentDisposition(Response response) {
        String contentDisposition = (String) response.getHeaders().get("Content-Disposition").get(0);
        return contentDisposition.substring(contentDisposition.indexOf('"') + 1, contentDisposition.length() - 1);
    }

    File writeToFile(InputStream inputStream, File file) {
        try (OutputStream outputStream = new FileOutputStream(file)) {
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

    Invocation.Builder addCredentialsHeader(Invocation.Builder builder) {
        return builder
            .header("username", getNotNullableSystemProperty("boatpos.regkas.service.username"))
            .header("password", getNotNullableSystemProperty("boatpos.regkas.service.password"))
            .header("cashbox", getNotNullableSystemProperty("boatpos.regkas.service.cashbox"));
    }

    WebTarget addCredentialsQuery(WebTarget webTarget) {
        return webTarget
            .queryParam("username", getNotNullableSystemProperty("boatpos.regkas.service.username"))
            .queryParam("password", getNotNullableSystemProperty("boatpos.regkas.service.password"))
            .queryParam("cashbox", getNotNullableSystemProperty("boatpos.regkas.service.cashbox"));
    }

    Invocation.Builder createRestCall(Function<WebTarget, WebTarget> addPath, MediaType mediaType) {
        WebTarget webTarget = ClientBuilder.newClient().target(getNotNullableSystemProperty("boatpos.regkas.service.rest"));
        webTarget = addPath.apply(webTarget);
        webTarget = addCredentialsQuery(webTarget);
        return addCredentialsHeader(webTarget.request().accept(mediaType));
    }

    String getNotNullableSystemProperty(String key) {
        String value = System.getProperty(key);
        if (value == null || "".equals(value)) {
            throw new RuntimeException("unable to get system-property-value for key: '" + key + "'");
        }
        return value;
    }

    public Boolean isSignatureDeviceAvailable() {
        return readEntity(
            createRestCall(webTarget -> webTarget.path("rest/sale/signatureDeviceAvailable"), MediaType.APPLICATION_JSON_TYPE).get(),
            Boolean.class);
    }

    public Boolean checkIfStartreceiptMustBePrinted() {
        return readEntity(
            createRestCall(webTarget -> webTarget.path("rest/receipt/start/check"), MediaType.APPLICATION_JSON_TYPE).get(),
            Boolean.class);
    }

    public Boolean checkIfSchlussreceiptCanBePrinted() {
        return readEntity(
            createRestCall(webTarget -> webTarget.path("rest/receipt/schluss/check"), MediaType.APPLICATION_JSON_TYPE).get(),
            Boolean.class);
    }
}

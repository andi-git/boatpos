package org.boatpos.service.rest;

import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.boatpos.common.test.rest.RestTestHelper;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class PromotionServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    private GenericType<List<PromotionBeforeBean>> promotionBeforeGenericTypeList = new GenericType<List<PromotionBeforeBean>>() {
    };

    private GenericType<List<PromotionAfterBean>> promotionAfterGenericTypeList = new GenericType<List<PromotionAfterBean>>() {
    };

    @Test
    public void testGetById() throws Exception {
        Response response = helper.createRestCall(url, (wt) -> wt.path("promotion/before/name/Fahr 3 zahl 2")).get();
        final Long idBefore = response.readEntity(PromotionBeforeBean.class).getId();
        response = helper.createRestCall(url, (wt) -> wt.path("promotion/before/id/" + idBefore)).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Fahr 3 zahl 2", response.readEntity(PromotionBeforeBean.class).getName());

        response = helper.createRestCall(url, (wt) -> wt.path("promotion/before/id/999")).get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        response = helper.createRestCall(url, (wt) -> wt.path("promotion/after/name/HolliKnolli")).get();
        final Long idAfter = response.readEntity(PromotionAfterBean.class).getId();
        response = helper.createRestCall(url, (wt) -> wt.path("promotion/after/id/" + idAfter)).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("HolliKnolli", response.readEntity(PromotionAfterBean.class).getName());
    }

    @Test
    public void testGetByName() throws Exception {
        Response response = helper.createRestCall(url, (wt) -> wt.path("promotion/before/name/Fahr 3 zahl 2")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Fahr 3 zahl 2", response.readEntity(PromotionBeforeBean.class).getName());

        response = helper.createRestCall(url, (wt) -> wt.path("promotion/before/name/XXX")).get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetAll() throws Exception {
        helper.assertCount(url, "promotion/before", 3, promotionBeforeGenericTypeList);
        helper.assertCount(url, "promotion/before", 2, EnabledState.Enabled, promotionBeforeGenericTypeList);
        helper.assertCount(url, "promotion/before", 1, EnabledState.Disabled, promotionBeforeGenericTypeList);
        helper.assertCount(url, "promotion/after", 2, promotionAfterGenericTypeList);
        helper.assertCount(url, "promotion/after", 1, EnabledState.Enabled, promotionAfterGenericTypeList);
        helper.assertCount(url, "promotion/after", 1, EnabledState.Disabled, promotionAfterGenericTypeList);
    }

    @Test
    public void testSave() throws Exception {
        helper.assertCount(url, "promotion/before", 3, promotionBeforeGenericTypeList);

        PromotionBean promotion = new PromotionBeforeBean(null, null, "PROMO", 300, "price / 3", 3, true, 'a', "", "");
        Response response = helper.createRestCall(url, (wt) -> wt.path("promotion/before")).post(Entity.json(promotion));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        PromotionBean result = response.readEntity(PromotionBeforeBean.class);
        assertNotNull(result.getId());
        assertEquals(0, result.getVersion().intValue());
        helper.assertCount(url, "promotion/before", 4, promotionBeforeGenericTypeList);

        promotion = new PromotionBeforeBean(null, null, null, 300, "price / 3", 3, true, 'a', "", "");
        response = helper.createRestCall(url, (wt) -> wt.path("promotion/before")).post(Entity.json(promotion));
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        helper.assertCount(url, "promotion/before", 4, promotionBeforeGenericTypeList);

        helper.assertCount(url, "promotion/after", 2, promotionAfterGenericTypeList);
        promotion = new PromotionAfterBean(null, null, "PROMO", "price / 3", 3, true, 'a', "", "");
        response = helper.createRestCall(url, (wt) -> wt.path("promotion/after")).post(Entity.json(promotion));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        result = response.readEntity(PromotionAfterBean.class);
        assertNotNull(result.getId());
        assertEquals(0, result.getVersion().intValue());
        helper.assertCount(url, "promotion/after", 3, promotionAfterGenericTypeList);

        promotion = new PromotionAfterBean(null, null, null, "price / 3", 3, true, 'a', "", "");
        response = helper.createRestCall(url, (wt) -> wt.path("promotion/after")).post(Entity.json(promotion));
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

        helper.assertCount(url, "promotion/after", 3, promotionAfterGenericTypeList);
    }

    @Test
    public void testUpdate() throws Exception {
        helper.assertCount(url, "promotion/before", 3, promotionBeforeGenericTypeList);

        Response response = helper.createRestCall(url, (wt) -> wt.path("promotion/before/name/Fahr 3 zahl 2")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        PromotionBean boat = response.readEntity(PromotionBeforeBean.class);
        assertEquals("Fahr 3 zahl 2", boat.getName());
        assertEquals(1, boat.getVersion().intValue());

        boat.setName("FAHR 3 ZAHL 2");
        response = helper.createRestCall(url, (wt) -> wt.path("promotion/before")).put(Entity.json(boat));
        boat = response.readEntity(PromotionBeforeBean.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("FAHR 3 ZAHL 2", boat.getName());
        assertEquals(2, boat.getVersion().intValue());

        response = helper.createRestCall(url, (wt) -> wt.path("promotion/before/name/FAHR 3 ZAHL 2")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        boat = response.readEntity(PromotionBeforeBean.class);
        assertEquals("FAHR 3 ZAHL 2", boat.getName());
        assertEquals(2, boat.getVersion().intValue());

        response = helper.createRestCall(url, (wt) -> wt.path("promotion/after/name/HolliKnolli")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        boat = response.readEntity(PromotionAfterBean.class);
        assertEquals("HolliKnolli", boat.getName());
        assertEquals(1, boat.getVersion().intValue());

        boat.setName("HOLLIKNOLLI");
        response = helper.createRestCall(url, (wt) -> wt.path("promotion/after")).put(Entity.json(boat));
        boat = response.readEntity(PromotionAfterBean.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("HOLLIKNOLLI", boat.getName());
        assertEquals(2, boat.getVersion().intValue());

        response = helper.createRestCall(url, (wt) -> wt.path("promotion/after/name/HOLLIKNOLLI")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        boat = response.readEntity(PromotionAfterBean.class);
        assertEquals("HOLLIKNOLLI", boat.getName());
        assertEquals(2, boat.getVersion().intValue());

        helper.assertCount(url, "promotion/before", 3, promotionBeforeGenericTypeList);
    }

    @Test
    public void testEnable() throws Exception {
        helper.assertCount(url, "promotion/before", 2, EnabledState.Enabled, promotionBeforeGenericTypeList);
        final Long idBeforeToEnable = helper.createRestCall(url, (webTarget) -> webTarget.path("promotion/before/name/Tageskarte")).get().readEntity(PromotionBeforeBean.class).getId();
        helper.createRestCall(url, (wt) -> wt.path("promotion/before/enable/" + idBeforeToEnable)).put(null);
        helper.assertCount(url, "promotion/before", 3, EnabledState.Enabled, promotionBeforeGenericTypeList);

        helper.assertCount(url, "promotion/after", 1, EnabledState.Enabled, promotionAfterGenericTypeList);
        final Long idAfterToEnable = helper.createRestCall(url, (webTarget) -> webTarget.path("promotion/after/name/Sommerferien")).get().readEntity(PromotionAfterBean.class).getId();
        helper.createRestCall(url, (wt) -> wt.path("promotion/after/enable/" + idAfterToEnable)).put(null);
        helper.assertCount(url, "promotion/after", 2, EnabledState.Enabled, promotionAfterGenericTypeList);
    }

    @Test
    public void testDisable() throws Exception {
        helper.assertCount(url, "promotion/before", 1, EnabledState.Disabled, promotionBeforeGenericTypeList);
        final Long idBeforeToDisable = helper.createRestCall(url, (webTarget) -> webTarget.path("promotion/before/name/Fahr 3 zahl 2")).get().readEntity(PromotionBeforeBean.class).getId();
        helper.createRestCall(url, (wt) -> wt.path("promotion/before/disable/" + idBeforeToDisable)).put(null);
        helper.assertCount(url, "promotion/before", 2, EnabledState.Disabled, promotionBeforeGenericTypeList);

        helper.assertCount(url, "promotion/after", 1, EnabledState.Disabled, promotionAfterGenericTypeList);
        final Long idAfterToDisable = helper.createRestCall(url, (webTarget) -> webTarget.path("promotion/after/name/HolliKnolli")).get().readEntity(PromotionAfterBean.class).getId();
        helper.createRestCall(url, (wt) -> wt.path("promotion/after/disable/" + idAfterToDisable)).put(null);
        helper.assertCount(url, "promotion/after", 2, EnabledState.Disabled, promotionAfterGenericTypeList);
    }
}

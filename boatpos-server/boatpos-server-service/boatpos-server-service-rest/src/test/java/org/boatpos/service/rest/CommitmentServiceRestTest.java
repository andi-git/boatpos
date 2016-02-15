package org.boatpos.service.rest;

import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.CommitmentBean;
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
public class CommitmentServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testGetById() throws Exception {
        Response response = helper.createRestCall(url, (wt) -> wt.path("commitment/name/Ausweis")).get();
        Long id = response.readEntity(CommitmentBean.class).getId();

        response = helper.createRestCall(url, (wt) -> wt.path("commitment/id/" + id)).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Ausweis", response.readEntity(CommitmentBean.class).getName());

        response = helper.createRestCall(url, (wt) -> wt.path("commitment/id/999")).get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetByName() throws Exception {
        Response response = helper.createRestCall(url, (wt) -> wt.path("commitment/name/Ausweis")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Ausweis", response.readEntity(CommitmentBean.class).getName());

        response = helper.createRestCall(url, (wt) -> wt.path("commitment/name/XXX")).get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetAll() throws Exception {
        List<CommitmentBean> commitments = helper.createRestCall(url, (wt) -> wt.path("commitment")).get().readEntity(new GenericType<List<CommitmentBean>>() {
        });
        assertEquals(6, commitments.size());
        assertEquals("Ausweis", commitments.get(0).getName());
        helper.assertCount(url, "commitment", 5, EnabledState.Enabled);
        helper.assertCount(url, "commitment", 1, EnabledState.Disabled);
    }

    @Test
    public void testSave() throws Exception {
        helper.assertCount(url, "commitment", 6);

        CommitmentBean commitment = new CommitmentBean(null, null, "Pass", true, 10, true, 'a', "", "");
        Response response = helper.createRestCall(url, (wt) -> wt.path("commitment")).post(Entity.json(commitment));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        CommitmentBean result = response.readEntity(CommitmentBean.class);
        assertNotNull(result.getId());
        assertEquals(0, result.getVersion().intValue());
        helper.assertCount(url, "commitment", 7);

        commitment = new CommitmentBean(-1L, null, null, true, 10, true, 'a', "", "");
        response = helper.createRestCall(url, (wt) -> wt.path("commitment")).post(Entity.json(commitment));
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

        helper.assertCount(url, "commitment", 7);
    }

    @Test
    public void testUpdate() throws Exception {
        helper.assertCount(url, "commitment", 6);

        Response response = helper.createRestCall(url, (wt) -> wt.path("commitment/name/Ausweis")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        CommitmentBean commitment = response.readEntity(CommitmentBean.class);
        assertEquals("Ausweis", commitment.getName());
        assertEquals(1, commitment.getVersion().intValue());

        commitment.setName("AUSWEIS");
        response = helper.createRestCall(url, (wt) -> wt.path("commitment")).put(Entity.json(commitment));
        commitment = response.readEntity(CommitmentBean.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("AUSWEIS", commitment.getName());
        assertEquals(2, commitment.getVersion().intValue());

        response = helper.createRestCall(url, (wt) -> wt.path("commitment/name/AUSWEIS")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        commitment = response.readEntity(CommitmentBean.class);
        assertEquals("AUSWEIS", commitment.getName());
        assertEquals(2, commitment.getVersion().intValue());

        helper.assertCount(url, "commitment", 6);
    }

    @Test
    public void testEnable() throws Exception {
        helper.assertCount(url, "commitment", 5, EnabledState.Enabled);
        Long idToEnable = helper.createRestCall(url, (webTarget) -> webTarget.path("commitment/name/Kinderwagen")).get().readEntity(CommitmentBean.class).getId();
        helper.createRestCall(url, (wt) -> wt.path("commitment/enable/" + idToEnable)).put(null);
        helper.assertCount(url, "commitment", 6, EnabledState.Enabled);
    }

    @Test
    public void testDisable() throws Exception {
        helper.assertCount(url, "commitment", 1, EnabledState.Disabled);
        Long idToDisable = helper.createRestCall(url, (webTarget) -> webTarget.path("commitment/name/Ausweis")).get().readEntity(CommitmentBean.class).getId();
        helper.createRestCall(url, (wt) -> wt.path("commitment/disable/" + idToDisable)).put(null);
        helper.assertCount(url, "commitment", 4, EnabledState.Enabled);
    }
}

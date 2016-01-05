package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BoatCountSummaryTest extends JavaBeanTest<BoatCountSummary> {

    @Override
    protected Class<BoatCountSummary> getType() {
        return BoatCountSummary.class;
    }

    @Test
    public void testAdditional() {
        BoatCountSummary boatCountSummary = new BoatCountSummary();
        boatCountSummary.addBoatCount(new BoatCountBean());
        boatCountSummary.clearBoatCountList();
        boatCountSummary.setBoatCountList(new ArrayList<>());
    }
}

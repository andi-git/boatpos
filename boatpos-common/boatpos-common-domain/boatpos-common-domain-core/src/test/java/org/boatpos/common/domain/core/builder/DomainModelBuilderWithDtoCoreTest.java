package org.boatpos.common.domain.core.builder;

import org.boatpos.common.domain.core.model.BarBean;
import org.boatpos.common.domain.core.model.BarBuilder;
import org.boatpos.common.domain.core.model.BarCore;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class DomainModelBuilderWithDtoCoreTest {

    @Test
    public void from() throws Exception {
        Assert.assertEquals(BarCore.class, new BarBuilder().from(new BarBean()).getClass());
    }
}
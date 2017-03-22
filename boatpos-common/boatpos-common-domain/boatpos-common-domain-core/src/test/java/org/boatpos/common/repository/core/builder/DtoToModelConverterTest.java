package org.boatpos.common.repository.core.builder;

import org.boatpos.common.repository.core.model.FooBean;
import org.boatpos.common.repository.core.model.FooCore;
import org.boatpos.common.repository.core.model.FooCoreToProduceException;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class DtoToModelConverterTest {

    @Test
    public void from() throws Exception {
        assertEquals(FooCore.class, DtoToModelConverter.from(new FooBean(), FooCore.class).getClass());
    }

    @Test(expected = RuntimeException.class)
    public void fromException() throws Exception {
        DtoToModelConverter.from(new FooBean(), FooCoreToProduceException.class);
    }
}
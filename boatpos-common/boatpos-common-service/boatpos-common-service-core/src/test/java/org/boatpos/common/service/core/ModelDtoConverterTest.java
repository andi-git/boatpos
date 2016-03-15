package org.boatpos.common.service.core;

import com.google.common.collect.Lists;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ModelDtoConverterTest {

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Test
    public void testConvert() throws Exception {
        List<FooBean> fooBeans = modelDtoConverter.convert(Lists.newArrayList(FooCore.createDummy1(), FooCore.createDummy2()));
        assertEquals(2, fooBeans.size());
    }

    @Test
    public void testConvertOptional() throws Exception {
        assertFalse(modelDtoConverter.convert(Optional.empty()).isPresent());
        assertEquals(1L, modelDtoConverter.convert(Optional.of(FooCore.createDummy1())).get().getId().longValue());
    }
}
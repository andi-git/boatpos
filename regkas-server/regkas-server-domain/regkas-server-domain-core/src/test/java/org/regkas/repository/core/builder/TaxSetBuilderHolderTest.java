package org.regkas.repository.core.builder;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.model.TaxSetBesondersEntity;
import org.regkas.model.TaxSetErmaessigt1Entity;
import org.regkas.model.TaxSetErmaessigt2Entity;
import org.regkas.model.TaxSetNormalEntity;
import org.regkas.model.TaxSetNullEntity;
import org.regkas.repository.core.model.TaxSetBesondersCore;
import org.regkas.repository.core.model.TaxSetErmaessigt1Core;
import org.regkas.repository.core.model.TaxSetErmaessigt2Core;
import org.regkas.repository.core.model.TaxSetNormalCore;
import org.regkas.repository.core.model.TaxSetNullCore;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class TaxSetBuilderHolderTest extends EntityManagerProviderForRegkas {

    @Inject
    private TaxSetBuilderHolder taxSetBuilderHolder;

    @Test
    @Transactional
    public void getAvaiableBuilders() throws Exception {
        assertEquals(5, taxSetBuilderHolder.getAvaiableBuilders().size());
    }

    @Test
    @Transactional
    public void getReceiptTypeFor() throws Exception {
        assertEquals(TaxSetNormalCore.class, taxSetBuilderHolder.getTaxSetFor(new TaxSetNormalEntity()).get().getClass());
        assertEquals(TaxSetErmaessigt1Core.class, taxSetBuilderHolder.getTaxSetFor(new TaxSetErmaessigt1Entity()).get().getClass());
        assertEquals(TaxSetErmaessigt2Core.class, taxSetBuilderHolder.getTaxSetFor(new TaxSetErmaessigt2Entity()).get().getClass());
        assertEquals(TaxSetNullCore.class, taxSetBuilderHolder.getTaxSetFor(new TaxSetNullEntity()).get().getClass());
        assertEquals(TaxSetBesondersCore.class, taxSetBuilderHolder.getTaxSetFor(new TaxSetBesondersEntity()).get().getClass());
    }
}
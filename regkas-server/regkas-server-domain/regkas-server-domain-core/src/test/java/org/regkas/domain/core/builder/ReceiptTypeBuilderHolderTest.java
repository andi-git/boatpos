package org.regkas.domain.core.builder;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.core.model.ReceiptTypeJahrCore;
import org.regkas.domain.core.model.ReceiptTypeMonatCore;
import org.regkas.domain.core.model.ReceiptTypeNullCore;
import org.regkas.domain.core.model.ReceiptTypeSammelCore;
import org.regkas.domain.core.model.ReceiptTypeSchlussCore;
import org.regkas.domain.core.model.ReceiptTypeStandardCore;
import org.regkas.domain.core.model.ReceiptTypeStartCore;
import org.regkas.domain.core.model.ReceiptTypeStornoCore;
import org.regkas.domain.core.model.ReceiptTypeTagCore;
import org.regkas.domain.core.model.ReceiptTypeTrainingCore;
import org.regkas.model.*;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "ConstantConditions"})
@RunWith(Arquillian.class)
public class ReceiptTypeBuilderHolderTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeBuilderHolder receiptTypeBuilderHolder;

    @Test
    @Transactional
    public void getAvaiableBuilders() throws Exception {
        assertEquals(10, receiptTypeBuilderHolder.getAvaiableBuilders().size());
    }

    @Test
    @Transactional
    public void getReceiptTypeFor() throws Exception {
        assertEquals(ReceiptTypeJahrCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeJahrEntity()).get().getClass());
        assertEquals(ReceiptTypeMonatCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeMonatEntity()).get().getClass());
        assertEquals(ReceiptTypeNullCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeNullEntity()).get().getClass());
        assertEquals(ReceiptTypeStandardCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeStandardEntity()).get().getClass());
        assertEquals(ReceiptTypeStartCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeStartEntity()).get().getClass());
        assertEquals(ReceiptTypeStornoCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeStornoEntity()).get().getClass());
        assertEquals(ReceiptTypeTrainingCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeTrainingEntity()).get().getClass());
        assertEquals(ReceiptTypeSchlussCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeSchlussEntity()).get().getClass());
        assertEquals(ReceiptTypeSammelCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeSammelEntity()).get().getClass());
        assertEquals(ReceiptTypeTagCore.class, receiptTypeBuilderHolder.getReceiptTypeFor(new ReceiptTypeTagEntity()).get().getClass());
    }
}
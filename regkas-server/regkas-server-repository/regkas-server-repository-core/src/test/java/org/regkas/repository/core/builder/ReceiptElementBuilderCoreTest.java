package org.regkas.repository.core.builder;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.values.Amount;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptElementBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptElementProducer receiptElementProducer;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertEquals("product-name", receiptElementProducer.getReceiptElement().getProduct().getName().get());
    }

    @Dependent
    public static class ReceiptElementProducer {

        @Inject
        private ProductBuilderCoreTest.ProductProducer productProducer;

        public ReceiptElement getReceiptElement() {
            return new ReceiptElementBuilderCore()
                    .add(productProducer.getProduct())
                    .add(new Amount(2))
                    .add(new TotalPrice(new BigDecimal("6.00")))
                    .build();
        }

    }
}
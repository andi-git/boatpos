package org.regkas.domain.core.mapping;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class ReceiptElementMappingTest extends EntityManagerProviderForRegkas {

    @Test
    @Transactional
    public void testFromCDI() {
        assertNotNull(ReceiptElementMapping.fromCDI());
    }
}
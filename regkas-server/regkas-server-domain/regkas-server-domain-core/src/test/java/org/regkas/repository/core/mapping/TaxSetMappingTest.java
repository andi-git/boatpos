package org.regkas.repository.core.mapping;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.service.api.bean.Period;
import org.regkas.service.api.bean.ReceiptBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class TaxSetMappingTest extends EntityManagerProviderForRegkas {

    @Test
    @Transactional
    public void testFromCDI() {
        assertNotNull(TaxSetMapping.fromCDI());
    }
}
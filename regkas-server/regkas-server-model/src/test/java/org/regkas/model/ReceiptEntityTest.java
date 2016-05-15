package org.regkas.model;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RunWith(Arquillian.class)
public class ReceiptEntityTest extends JavaBeanTest<ReceiptEntity> {

    @Test
    public void testConstructor() {
        new ReceiptEntity(1L, 1, "name", LocalDateTime.now(), "turnoverValue", "signatureValue", new CompanyEntity(), new CashBoxEntity(), new UserEntity(), new ReceiptTypeEntity(), PaymentMethod.CASH, TimeType.Current, new ArrayList<>(), "");
    }
}
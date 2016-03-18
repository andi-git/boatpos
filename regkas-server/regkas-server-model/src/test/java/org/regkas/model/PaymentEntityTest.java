package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.HashSet;

@RunWith(Arquillian.class)
public class PaymentEntityTest extends JavaBeanTest<PaymentEntity> {

    @Test
    public void testConstructor() {
        new PaymentEntity(1L, 1, "name", LocalDateTime.now(), "turnoverValue", "signatureValue", new CompanyEntity(), new CashBoxEntity(), new UserEntity(), new HashSet<>());
    }
}
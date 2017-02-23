package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CashBoxEntityTest extends JavaBeanTest<CashBoxEntity> {

    @Test
    public void testConstructor() {
        new CashBoxEntity(1L, 1, true, 1, "", "", "name", "serial", new CompanyEntity(), "", "", 0L, "", "", "");
    }
}
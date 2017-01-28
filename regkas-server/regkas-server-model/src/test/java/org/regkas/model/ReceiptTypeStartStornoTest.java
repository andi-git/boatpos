package org.regkas.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ReceiptTypeStartStornoTest extends JavaBeanTest<ReceiptTypeStornoEntity> {

    @Test
    public void testConstructor() {
        new ReceiptTypeStornoEntity(1L, 1, true, 1, "", "", "name");
    }
}
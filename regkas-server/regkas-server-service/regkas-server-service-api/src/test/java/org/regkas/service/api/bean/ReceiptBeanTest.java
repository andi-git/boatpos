package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

public class ReceiptBeanTest extends JavaBeanTest<ReceiptBean> {

    @Test
    public void testConstructor() {
        new ReceiptBean("id", LocalDateTime.now(), "", "", new CompanyBean(), "cashbox", "receipttype", new HashSet<>());
    }
}
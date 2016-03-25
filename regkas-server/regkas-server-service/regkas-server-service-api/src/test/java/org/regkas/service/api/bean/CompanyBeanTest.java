package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyBeanTest extends JavaBeanTest<CompanyBean> {

    @Test
    public void testConstructor() {
        new CompanyBean(1L, 1, true, 1, ' ', "", "", "name", "street", "zip", "city", "country", "phone", "mail", "atu");
    }
}
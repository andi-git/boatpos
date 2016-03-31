package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

public class CredentialsBeanTest extends JavaBeanTest<CredentialsBean> {

    @Test
    public void testConstructor() {
        new CredentialsBean("name", "password");
    }
}
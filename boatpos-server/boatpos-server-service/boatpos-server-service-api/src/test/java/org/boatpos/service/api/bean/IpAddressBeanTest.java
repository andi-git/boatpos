package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

public class IpAddressBeanTest extends JavaBeanTest<IpAddressBean> {

    @Test
    public void testConstructor() {
        new IpAddressBean("192.168.0.11");
    }
}
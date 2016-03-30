package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SaleBeanTest extends JavaBeanTest<SaleBean> {

    @Test
    public void testConstructor() {
        new SaleBean("pm", "rt", new ArrayList<>());
    }
}
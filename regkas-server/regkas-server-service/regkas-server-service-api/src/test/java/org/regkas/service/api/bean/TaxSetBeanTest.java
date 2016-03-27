package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.boatpos.common.test.JavaBeanTester;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class TaxSetBeanTest extends JavaBeanTest<TaxSetBean> {

    @Test
    public void testConstructor() {
        new TaxSetBean("taxSet", 20);
    }
}
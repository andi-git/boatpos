package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTester;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class TaxSetBeanTest {

    @Test
    public void testBean() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JavaBeanTester javaBeanTester = new JavaBeanTester();
        javaBeanTester.test(TaxSetBean.TaxSetNormalBean.class);
        javaBeanTester.test(TaxSetBean.TaxSetErmaessigt1Bean.class);
        javaBeanTester.test(TaxSetBean.TaxSetErmaessigt2Bean.class);
        javaBeanTester.test(TaxSetBean.TaxSetBesonderesBean.class);
        javaBeanTester.test(TaxSetBean.TaxSetNullBean.class);
    }
}
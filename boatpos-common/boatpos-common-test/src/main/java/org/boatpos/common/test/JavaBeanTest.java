package org.boatpos.common.test;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;

public abstract class JavaBeanTest<T> {

    protected JavaBeanTester javaBeanTester = new JavaBeanTester();

    @Test
    public void testBean() throws Exception {
        javaBeanTester.test(getType());
    }

    private Class<T> getType() {
        //noinspection unchecked
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}

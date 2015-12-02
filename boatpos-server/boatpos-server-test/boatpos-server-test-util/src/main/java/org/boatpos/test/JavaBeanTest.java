package org.boatpos.test;

import org.junit.Test;

public abstract class JavaBeanTest<T> {

    protected JavaBeanTester javaBeanTester = new JavaBeanTester();

    @Test
    public void testBean() throws Exception {
        javaBeanTester.test(getType());
    }

    protected abstract Class<T> getType();
}

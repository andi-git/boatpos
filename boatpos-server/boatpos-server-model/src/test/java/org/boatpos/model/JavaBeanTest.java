package org.boatpos.model;

import org.boatpos.test.JavaBeanTester;
import org.junit.Test;

import javax.inject.Inject;

public abstract class JavaBeanTest<T> {

    @Inject
    protected JavaBeanTester javaBeanTester;

    @Test
    public void testBean() throws Exception {
        javaBeanTester.test(getType());
    }

    protected abstract Class<T> getType();
}

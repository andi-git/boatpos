package org.boatpos.test;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JavaBeanTesterTest {

    private JavaBeanTester javaBeanTester;

    @Before
    public void before() {
        javaBeanTester = new JavaBeanTester();
    }

    @Test
    public void testBeanCheck() throws Exception {
        javaBeanTester.test(BeanA.class);
    }

    public static class BeanA {

        private int i;

        private boolean b;

        private String string;

        private BeanB beanb;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public boolean isB() {
            return b;
        }

        public void setB(boolean b) {
            this.b = b;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public BeanB getBeanb() {
            return beanb;
        }

        public void setBeanb(BeanB beanb) {
            this.beanb = beanb;
        }
    }

    public static class BeanB {

        private List<String> list;

        public BeanB() {
        }

        public BeanB(List<String> list) {
            this.list = list;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
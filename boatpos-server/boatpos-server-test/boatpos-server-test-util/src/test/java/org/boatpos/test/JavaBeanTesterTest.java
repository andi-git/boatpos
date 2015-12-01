package org.boatpos.test;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JavaBeanTesterTest {

    private JavaBeanTester javaBeanTester;

    @Before
    public void before() {
        javaBeanTester = new JavaBeanTester();
    }

    @Test
    public void testBeanCheck() throws Exception {
        javaBeanTester.test(BeanA.class);
        javaBeanTester.test(BeanE.class);
        javaBeanTester.test(BeanF.class);
    }

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    public static abstract class BeanC {

        private int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    @SuppressWarnings("unused")
    public static abstract class BeanD extends BeanC {

        private String s;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }

    @SuppressWarnings("unused")
    public static class BeanE extends BeanD {

        private String s;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }

    @SuppressWarnings("unused")
    public static class BeanF {

        private BeanC beanC;

        public BeanC getBeanC() {
            return beanC;
        }

        public void setBeanC(BeanC beanC) {
            this.beanC = beanC;
        }
    }
}
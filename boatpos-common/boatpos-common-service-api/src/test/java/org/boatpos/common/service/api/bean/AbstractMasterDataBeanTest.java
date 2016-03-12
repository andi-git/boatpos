package org.boatpos.common.service.api.bean;

import org.boatpos.common.test.JavaBeanTester;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractMasterDataBeanTest {

    private MyBean myBean1 = new MyBean(1L, 1, true, 1, 'c', "qwe", "qwe", "qwe");
    private MyBean myBean2 = new MyBean(2L, 2, true, 2, 'v', "asd", "asd", "asd");
    private MyBean myBean3 = new MyBean(1L, 3, true, 3, 'b', "yxc", "yxc", "yxc");

    @Test
    public void testBean() throws Exception {
        new JavaBeanTester().test(MyBean.class);
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(myBean1.equals(myBean3));
        assertFalse(myBean1.equals(myBean2));
        assertFalse(myBean2.equals(myBean3));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(myBean1.hashCode(), myBean3.hashCode());
        assertNotEquals(myBean1.hashCode(), myBean2.hashCode());
        assertNotEquals(myBean2.hashCode(), myBean3.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("{\"enabled\":true,\"priority\":1,\"pictureUrlThumb\":\"qwe\",\"pictureUrl\":\"qwe\",\"id\":1,\"version\":1}", myBean1.toString());
    }

    public static class MyBean extends AbstractMasterDataBean {

        private String name;

        @SuppressWarnings("unused")
        public MyBean() {
            super();
        }

        public MyBean(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb, String name) {
            super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
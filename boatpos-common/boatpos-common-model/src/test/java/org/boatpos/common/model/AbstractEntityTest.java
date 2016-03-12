package org.boatpos.common.model;

import org.boatpos.common.test.JavaBeanTester;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AbstractEntityTest {

    private ConcreteEntity entity1;
    private ConcreteEntity entity2;
    private ConcreteEntity entity3;

    @Before
    public void before() {
        entity1 = new ConcreteEntity();
        entity1.setId(1L);
        entity2 = new ConcreteEntity();
        entity2.setId(2L);
        entity3 = new ConcreteEntity();
        entity3.setId(1L);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(entity1, entity1);
        assertEquals(entity2, entity2);
        assertEquals(entity3, entity3);

        assertEquals(entity1, entity3);
        assertEquals(entity3, entity1);
        assertNotEquals(entity1, entity2);
        assertNotEquals(entity2, entity1);
        assertNotEquals(entity2, entity3);
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(entity1.hashCode(), entity1.hashCode());
        assertEquals(entity2.hashCode(), entity2.hashCode());
        assertEquals(entity3.hashCode(), entity3.hashCode());

        assertEquals(entity1.hashCode(), entity3.hashCode());
        assertNotEquals(entity1.hashCode(), entity2.hashCode());
        assertNotEquals(entity2.hashCode(), entity3.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        entity1.setName("name");
        assertEquals("{\"name\":\"name\",\"enabled\":false,\"id\":1}", entity1.toString());
    }

    @Test
    public void testBean() throws Exception {
        new ConcreteEntity(1L, 1, "name", 1, true, "", "", 'c');
        new JavaBeanTester().test(ConcreteEntity.class);
    }
}
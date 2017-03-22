package org.regkas.domain.core.builder;

import static org.junit.Assert.assertNull;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ReceiptTypeTagBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertNull(new ReceiptTypeTagBuilderCore().build());
    }
}

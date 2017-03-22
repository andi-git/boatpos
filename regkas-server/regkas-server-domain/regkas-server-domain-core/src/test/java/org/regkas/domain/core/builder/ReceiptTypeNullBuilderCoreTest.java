package org.regkas.domain.core.builder;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class ReceiptTypeNullBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertNull(new ReceiptTypeNullBuilderCore().build());
    }
}
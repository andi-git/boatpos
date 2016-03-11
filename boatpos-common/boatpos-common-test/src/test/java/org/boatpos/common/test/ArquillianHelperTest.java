package org.boatpos.common.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArquillianHelperTest {

    @Test
    public void testGetAllArquillianLibs() throws Exception {
        assertEquals(4, ArquillianHelper.getAllArquillianLibs().length);
    }
}
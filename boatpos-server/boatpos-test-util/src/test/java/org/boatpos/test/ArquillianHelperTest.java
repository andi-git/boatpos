package org.boatpos.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArquillianHelperTest {

    @Test
    public void testGetAllArquillianLibs() throws Exception {
        assertEquals(1, ArquillianHelper.getAllArquillianLibs().length);
    }
}
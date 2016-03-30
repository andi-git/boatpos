package org.regkas.service.core.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiptTypeTest {

    @Test
    public void get() throws Exception {
        assertEquals(ReceiptType.Null, ReceiptType.get(""));
        assertEquals(ReceiptType.Standard, ReceiptType.get("Standard-Beleg"));
    }
}
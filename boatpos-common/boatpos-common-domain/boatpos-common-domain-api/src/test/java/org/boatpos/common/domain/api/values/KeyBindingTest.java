package org.boatpos.common.domain.api.values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyBindingTest {

    @Test
    public void test() {
        assertEquals('c', new KeyBinding('c').get().charValue());
    }
}
package org.boatpos.common.domain.api.values;

/**
 * The key.binding for the user-input.
 */
public class KeyBinding extends SimpleValueObject<KeyBinding, Character> {

    public KeyBinding(Character value) {
        super(value);
    }
}

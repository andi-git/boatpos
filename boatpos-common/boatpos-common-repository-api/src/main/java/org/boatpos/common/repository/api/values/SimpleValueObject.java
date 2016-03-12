package org.boatpos.common.repository.api.values;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Abstract simple value object.
 */
public abstract class SimpleValueObject<SVO extends SimpleValueObject, T extends Comparable> implements Serializable, Comparable<SimpleValueObject<SVO, T>> {

    protected final T value;

    private final Class<T> type;

    public SimpleValueObject(T value) {
        this.value = value;
        //noinspection unchecked
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T get() {
        return value;
    }

    public T orElseGet(T other) {
        return value != null ? value : other;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public SVO clone() {
        try {
            //noinspection unchecked
            return (SVO) type.getDeclaredConstructor(value.getClass()).newInstance(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked") SimpleValueObject<SVO, T> that = (SimpleValueObject<SVO, T>) o;
        return Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return type.getName() + ": " + String.valueOf(value);
    }

    @Override
    public int compareTo(SimpleValueObject<SVO, T> o) {
        //noinspection unchecked
        return value.compareTo(o.get());
    }

    public static <SVO extends SimpleValueObject, T extends Comparable> T nullSafe(SimpleValueObject<SVO, T> simpleValueObject) {
        if (simpleValueObject != null) {
            return simpleValueObject.get();
        }
        return null;
    }

    public boolean isPresent() {
        return value != null;
    }
}

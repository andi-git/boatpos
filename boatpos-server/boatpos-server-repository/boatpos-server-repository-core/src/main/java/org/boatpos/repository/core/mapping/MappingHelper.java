package org.boatpos.repository.core.mapping;

/**
 * The real mapping. It encapsulates the mapping-implementation / -framework.
 */
public interface MappingHelper {

    /**
     * Perform the mapping.
     *
     * @param source     the source-object
     * @param targetType the type of the target
     * @param <T>        the type of the target
     * @return a new instance of type with values mapped from the target
     */
    <T> T map(Object source, Class<T> targetType);

    /**
     * Perform the mapping.
     *
     * @param source the source-object
     * @param target the type of the target
     * @return a new instance of type with values mapped from the target
     */
    void map(Object source, Object target);
}

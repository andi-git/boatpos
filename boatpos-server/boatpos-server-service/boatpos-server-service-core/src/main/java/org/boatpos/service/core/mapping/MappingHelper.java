package org.boatpos.service.core.mapping;

/**
 * The real mapping. It encapsulates the mapping-implementation / -framework.
 */
public interface MappingHelper {

    /**
     * Perform the mapping.
     *
     * @param object the source-object
     * @param type   the type of the target
     * @param <T>    the type of the target
     * @return a new instance of type with values mapped from the target
     */
    <T> T map(Object object, Class<T> type);
}

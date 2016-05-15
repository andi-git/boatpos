package org.regkas.service.core.serializer;

/**
 * Serialize and deserialize objects.
 */
public interface Serializer {

    String serialize(Object object);

    <T> T deserialize(String serialized, Class<T> type);
}

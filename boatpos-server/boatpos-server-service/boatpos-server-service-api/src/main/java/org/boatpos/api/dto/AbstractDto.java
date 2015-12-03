package org.boatpos.api.dto;

import com.google.gson.GsonBuilder;

/**
 * Basic class for DTOs to have the method {@link #toString()}.
 */
@SuppressWarnings("unused")
public abstract class AbstractDto {

    public AbstractDto() {
    }

    @Override
    public String toString() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(this);
    }
}

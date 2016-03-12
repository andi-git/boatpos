package org.boatpos.common.service.api.bean;

import com.google.gson.GsonBuilder;

/**
 * Basic class for DTOs to have the method {@link #toString()}.
 */
@SuppressWarnings("unused")
public abstract class AbstractBean {

    public AbstractBean() {
    }

    @Override
    public String toString() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(this);
    }
}

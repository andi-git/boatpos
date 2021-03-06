package org.regkas.domain.core.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.regkas.domain.api.serializer.NonPrettyPrintingGson;
import org.regkas.domain.api.serializer.Serializer;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
@NonPrettyPrintingGson
public class GsonSerializer implements Serializer {

    private final Gson gson;

    public GsonSerializer() {
        gson = new GsonBuilder()
//                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeSerializer())
                .disableHtmlEscaping()
                .create();
    }

    @Override
    public String serialize(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T deserialize(String serialized, Class<T> type) {
        return this.gson.fromJson(serialized, type);
    }
}

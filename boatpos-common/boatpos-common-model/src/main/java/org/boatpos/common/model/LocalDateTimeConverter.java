package org.boatpos.common.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime date) {
        return date != null ? Timestamp.valueOf(date) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp value) {
        return value != null ? value.toLocalDateTime() : null;
    }
}

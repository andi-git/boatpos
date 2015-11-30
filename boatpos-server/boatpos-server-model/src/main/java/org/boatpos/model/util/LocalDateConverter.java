package org.boatpos.model.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.sql.Date;

@SuppressWarnings("unused")
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        return date != null ? Date.valueOf(date) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
        return value != null ? value.toLocalDate() : null;
    }
}
package org.boatpos.common.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

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

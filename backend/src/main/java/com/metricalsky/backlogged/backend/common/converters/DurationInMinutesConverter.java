package com.metricalsky.backlogged.backend.common.converters;

import java.time.Duration;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DurationInMinutesConverter implements AttributeConverter<Duration, Short> {

    @Override
    public Short convertToDatabaseColumn(Duration attribute) {
        if (attribute == null) {
            return null;
        }
        var dbData = attribute.toMinutes();
        if ((short) dbData != dbData) {
            throw new IllegalArgumentException();
        }
        return (short) dbData;
    }

    @Override
    public Duration convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : Duration.ofMinutes(dbData);
    }
}

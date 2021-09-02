package com.metricalsky.backlogged.backend.common.converters;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DurationInMinutesConverterTest {

    private final DurationInMinutesConverter converter = new DurationInMinutesConverter();

    @ParameterizedTest
    @MethodSource({"values", "narrowingValues"})
    void givenDuration_whenConvertToDatabaseColumn_thenReturnMinutes(String duration, int expected) {
        assertThat(converter.convertToDatabaseColumn(Duration.parse(duration)))
                .isEqualTo((short) expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"P22DT18H8M", "-P22DT18H9M"})
    void givenOverflow_whenConvertToDatabaseColumn_thenThrow(String duration) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> converter.convertToDatabaseColumn(Duration.parse(duration)));
    }

    @Test
    void givenNull_whenConvertToDatabaseColumn_thenReturnNull() {
        assertThat(converter.convertToDatabaseColumn(null))
                .isNull();
    }

    @ParameterizedTest
    @MethodSource("values")
    void givenMinutes_whenConvertToEntityAttribute_thenReturnDuration(String expected, int minutes) {
        assertThat(converter.convertToEntityAttribute((short) minutes))
                .isEqualTo(Duration.parse(expected));
    }

    @Test
    void givenNull_whenConvertToEntityAttribute_thenReturnNull() {
        assertThat(converter.convertToEntityAttribute(null))
                .isNull();
    }

    private static List<Arguments> values() {
        return List.of(
                arguments("PT0M", 0),
                arguments("PT1M", 1),
                arguments("PT1H", 60),
                arguments("P1D", 1440),
                arguments("P22DT18H7M", 32767),
                arguments("-P22DT18H8M", -32768)
        );
    }

    private static List<Arguments> narrowingValues() {
        return List.of(
                arguments("PT1S", 0),
                arguments("PT59S", 0),
                arguments("PT1M1S", 1)
        );
    }
}

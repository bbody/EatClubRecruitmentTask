package com.brendonbody.components;

import java.time.LocalTime;

import com.brendonbody.util.TimeParser;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TimeOfDayConvertor implements Converter<String, LocalTime> {
    @Override
    public @Nullable LocalTime convert(String source) {
        return TimeParser.parse(source);
    }
}

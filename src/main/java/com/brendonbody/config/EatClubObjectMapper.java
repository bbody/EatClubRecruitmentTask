package com.brendonbody.config;

import com.brendonbody.clients.eat_club_responses.LocalTimeDeserializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalTime;

public final class EatClubObjectMapper {

    private EatClubObjectMapper() {}

    public static ObjectMapper create() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(
                LocalTime.class,
                new LocalTimeDeserializer()
        );

        return new ObjectMapper()
                .registerModule(module)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}

package com.brendonbody.clients.eat_club_responses;

import com.brendonbody.util.TimeParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(
            JsonParser parser,
            DeserializationContext context
    ) throws IOException {
        return TimeParser.parse(parser.getText());
    }
}


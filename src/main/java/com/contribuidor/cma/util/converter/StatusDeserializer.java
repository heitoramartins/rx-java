package com.contribuidor.cma.util.converter;

import com.contribuidor.cma.entities.enumeration.Status;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class StatusDeserializer extends StdDeserializer<Status> {

    public StatusDeserializer() {
        super(Status.class);
    }

    @Override
    public Status deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Status.fromValue(jsonParser.getValueAsString());
    }
}

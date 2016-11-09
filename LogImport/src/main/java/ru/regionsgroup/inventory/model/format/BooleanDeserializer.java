package ru.regionsgroup.inventory.model.format;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class BooleanDeserializer extends JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getText().toLowerCase();
        if (value.equals("no") || value.equals("false") || value.equals("0")) {
            return false;
        }
        return true;
    }
}
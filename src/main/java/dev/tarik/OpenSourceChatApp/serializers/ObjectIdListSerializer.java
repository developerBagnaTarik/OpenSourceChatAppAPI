package dev.tarik.OpenSourceChatApp.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.List;

public class ObjectIdListSerializer extends JsonSerializer<List<ObjectId>> {
    @Override
    public void serialize(List<ObjectId> objectIds, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (ObjectId objectId : objectIds) {
            jsonGenerator.writeString(objectId.toHexString());
        }
        jsonGenerator.writeEndArray();
    }
}

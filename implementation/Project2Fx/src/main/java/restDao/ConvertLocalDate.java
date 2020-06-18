package restDao;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class ConvertLocalDate implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    public ConvertLocalDate() {
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString());
    }

}


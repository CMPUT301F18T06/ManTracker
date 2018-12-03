package project.ece301.mantracker.DataManagment;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import project.ece301.mantracker.Account.Account;


/**
 * Add-in to allow GSON/JSON Serializing of the class Account and its subclasses.
 */
public class AccountSerializer implements JsonSerializer<Account>, JsonDeserializer<Account> {

    /**
     * @param json    The Json data being deserialized
     * @param typeOfT not used
     * @param context
     * @return a deserialized object which is a subclass of {@code Account}
     * @throws JsonParseException if json is not in the expected format of the mood type
     * @see {@code Account}
     * @see com.google.gson.JsonDeserializer
     */
    @Override
    public Account deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName("com.assign1.rfurrer.feelsbook.feeling." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }

    /**
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(Account src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));
        return result;
    }
}

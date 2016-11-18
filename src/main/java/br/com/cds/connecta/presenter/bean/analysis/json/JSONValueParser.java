package br.com.cds.connecta.presenter.bean.analysis.json;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 *
 * @author diego
 */
public class JSONValueParser {

    public JSONValue parse(InputStream inputStream) {
        JsonReader reader = Json.createReader(inputStream);
        return parseTree(reader.read(), null);
    }
    
     public JSONValue parse(String jsonString) {
         JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
         try {
            return parseTree(jsonReader.read(), null);
         } catch (Exception e) {
             return new JSONValue() {};
         }
    }

    private JSONValue parseTree(JsonValue tree, String key) {
        JSONValue value;

        switch (tree.getValueType()) {
            case OBJECT:
                JSONValueObject valueObject = new JSONValueObject();
                valueObject.setName(key);
                valueObject.setType(JSONValueType.OBJECT);

                JsonObject object = (JsonObject) tree;
                List<JSONValue> attributes = new ArrayList<>();

                for (String name : object.keySet()) {
                    attributes.add(parseTree(object.get(name), name));
                }

                valueObject.setAttributes(attributes);

                value = valueObject;
                break;
            case ARRAY:
                JsonArray array = (JsonArray) tree;

                JSONValueArray valueArray = new JSONValueArray();
                valueArray.setName(key);
                valueArray.setType(JSONValueType.ARRAY);

                List<JSONValue> values = new ArrayList<>();

                for (JsonValue val : array) {
                    values.add(parseTree(val, null));
                }

                valueArray.setValues(values);

                value = valueArray;

                break;
            case STRING:
                JSONValuePrimitive<String> valueString = new JSONValuePrimitive<>();
                valueString.setName(key);
                valueString.setType(JSONValueType.STRING);
                valueString.setValue(((JsonString) tree).getString());

                value = valueString;
                break;
            case NUMBER:
                JSONValuePrimitive<Number> valueNumber = new JSONValuePrimitive<>();
                valueNumber.setName(key);
                valueNumber.setType(JSONValueType.NUMBER);
                JsonNumber number = (JsonNumber) tree;
                valueNumber.setValue(number.bigDecimalValue());

                value = valueNumber;
                break;
            case TRUE:
            case FALSE:
                JSONValuePrimitive<Boolean> valueBoolean = new JSONValuePrimitive<>();
                valueBoolean.setName(key);
                valueBoolean.setType(JSONValueType.BOOLEAN);

                valueBoolean.setValue(Boolean.getBoolean(tree.getValueType().toString()));
                value = valueBoolean;
                break;
            case NULL:
            default:
                value = new JSONValueUnknown();
                value.setName(key);
                value.setType(JSONValueType.UNKNOWN);
                break;
        }

        return value;
    }

   
}

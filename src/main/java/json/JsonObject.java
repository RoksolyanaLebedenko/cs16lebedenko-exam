package json;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private ArrayList<JsonPair> jsonPairs;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new ArrayList<>();
        for (JsonPair i: jsonPairs){
            this.jsonPairs.add(i);
        }
    }

    @Override
    public String toJson() {
        String str = "{";
        for (int i = 0; i < jsonPairs.size(); i++) {
            JsonPair pr = jsonPairs.get(i);
            str += "'" + pr.key + "'"+ ": " + pr.value.toJson();
            if (i != jsonPairs.size()-1){
                str += ", ";
            }
        }
        str += "}";
        return str;
    }



    public void add(JsonPair jsonPair) {
        for (int i = 0; i < jsonPairs.size(); i++){
            if (Objects.equals(jsonPairs.get(i).key, jsonPair.key)){
                jsonPairs.set(i, jsonPair);
                break;
            }
            jsonPairs.add(jsonPair);
        }
    }


    public Json find(String name) {
        for (JsonPair pair : jsonPairs) {
            if (Objects.equals(pair.key, name)) {
                return pair.value;
            }
        }
        return null;
    }


    public JsonObject projection(String... names) {
        JsonObject jsonObj = new JsonObject();
        for (JsonPair i: this.jsonPairs){
            for (String str: names){
                if (Objects.equals(i.key, str)){
                    jsonObj.add(i);
                }
            }
        }
        return jsonObj;
    }

}

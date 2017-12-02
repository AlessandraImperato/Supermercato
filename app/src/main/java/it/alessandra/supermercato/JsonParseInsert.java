package it.alessandra.supermercato;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by User on 02/12/2017.
 */

public class JsonParseInsert {

    public static int key(String json) {
        int index = 1;
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                index++;
                iterator.next();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return index;
    }

}


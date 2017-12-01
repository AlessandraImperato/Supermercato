package it.alessandra.supermercato;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by utente7.academy on 01/12/2017.
 */

public class JsonParseProdotti {

    public static int key(String json) {
        int index = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> prd = jsonObject.keys();
            while (prd.hasNext()) {
                String oneKey = prd.next();
                JSONObject oneProd = jsonObject.getJSONObject(oneKey);
                Iterator num = oneProd.keys();
                while (num.hasNext()) {
                    index++;
                    num.next();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return index;
    }
}

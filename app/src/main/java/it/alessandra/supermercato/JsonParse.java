package it.alessandra.supermercato;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 30/11/2017.
 */

public class JsonParse {

   /* public static List<Prodotto> getList(String json) throws JSONException{

        List<Prodotto> prodotti = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> prd = jsonObject.keys();
            while(prd.hasNext()){
                Prodotto prodotto = new Prodotto();
                String oneKey = prd.next(); //carne/pesce/latte

                JSONObject oneProd = jsonObject.getJSONObject(oneKey);
                Iterator<String> num = oneProd.keys();
                while (num.hasNext()){
                    String oneKey2 = num.next();
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }*/
}

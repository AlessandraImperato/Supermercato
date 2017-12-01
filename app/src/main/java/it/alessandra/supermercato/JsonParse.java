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

    public static List<Prodotto> getList(String json) throws JSONException {

        List<Prodotto> prodotti = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> prd = jsonObject.keys();
            while (prd.hasNext()) {

                String oneKey = prd.next(); // carne/pesce/latte

                JSONObject oneProd = jsonObject.getJSONObject(oneKey);
                Iterator<String> num = oneProd.keys();
                while (num.hasNext()) {
                    Carne pCarne = new Carne();
                    Pesce pPesce = new Pesce();
                    Latte pLatte = new Latte();
                    String oneKey2 = num.next(); // 01/02...

                    JSONObject specificPrd = oneProd.getJSONObject(oneKey2);

                    Iterator<String> dettagli = specificPrd.keys();
                    while (dettagli.hasNext()) {
                        String oneKey3 = dettagli.next(); // marca / prezzo
                        if (oneKey.equals("Carne")) {
                            if (oneKey3.equals("Marca")) {
                                String marca = specificPrd.getString(oneKey3);
                                pCarne.setMarca(marca);
                            } else if (oneKey3.equals("Prezzo")) {
                                double prezzo = Double.parseDouble(specificPrd.getString(oneKey3));
                                pCarne.setPrezzo(prezzo);
                            }
                        }
                        if (oneKey.equals("Pesce")) {
                            if (oneKey3.equals("Marca")) {
                                String marca = specificPrd.getString(oneKey3);
                                pPesce.setMarca(marca);
                            } else if (oneKey3.equals("Prezzo")) {
                                double prezzo = Double.parseDouble(specificPrd.getString(oneKey3));
                                pPesce.setPrezzo(prezzo);
                            }
                        }
                        if (oneKey.equals("Latte")) {
                            if (oneKey3.equals("Marca")) {
                                String marca = specificPrd.getString(oneKey3);
                                pLatte.setMarca(marca);
                            } else if (oneKey3.equals("Prezzo")) {
                                double prezzo = Double.parseDouble(specificPrd.getString(oneKey3));
                                pLatte.setPrezzo(prezzo);
                            }
                        }
                    }
                    if (oneKey.equals("Carne")){prodotti.add(pCarne);}
                    else if (oneKey.equals("Pesce")){prodotti.add(pPesce);}
                    else if (oneKey.equals("Latte")){prodotti.add(pLatte);}
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return prodotti;
    }
}

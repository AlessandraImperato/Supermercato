package it.alessandra.supermercato;

import java.io.Serializable;

/**
 * Created by utente7.academy on 30/11/2017.
 */

public class Pesce extends Prodotto implements Serializable {

    public Pesce(){
        super();
    }

    public Pesce(String marca, double prezzo){
        super(marca,prezzo);
    }

}

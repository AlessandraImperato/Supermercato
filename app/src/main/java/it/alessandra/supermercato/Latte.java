package it.alessandra.supermercato;

import java.io.Serializable;

/**
 * Created by utente7.academy on 30/11/2017.
 */

public class Latte extends Prodotto implements Serializable {

    public Latte(){
        super();
    }

    public Latte(String marca, double prezzo){
        super(marca,prezzo);
    }

}

package it.alessandra.supermercato;

import java.io.Serializable;

/**
 * Created by utente7.academy on 30/11/2017.
 */

public class Carne extends Prodotto implements Serializable{

    public Carne(){
        super();
    }

    public Carne(String marca, double prezzo){
        super(marca,prezzo);
    }

}

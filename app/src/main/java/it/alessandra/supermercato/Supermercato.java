package it.alessandra.supermercato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by utente7.academy on 30/11/2017.
 */

public class Supermercato {

    private List<Prodotto> prodotti;

    public Supermercato(){
        this.prodotti = new ArrayList<>();
    }

    public Supermercato(List<Prodotto> prodotti){
        this.prodotti = prodotti;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }
}

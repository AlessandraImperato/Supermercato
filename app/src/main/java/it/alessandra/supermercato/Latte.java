package it.alessandra.supermercato;

/**
 * Created by utente7.academy on 30/11/2017.
 */

public class Latte extends Prodotto {
    String marca;
    double prezzo;

    public Latte(){
        super();
        this.marca = null;
        this.prezzo = 0.0;
    }

    public Latte(String nome, String marca, double prezzo){
        super(nome);
        this.marca = marca;
        this.prezzo = prezzo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}

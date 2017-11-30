package it.alessandra.supermercato;

/**
 * Created by utente7.academy on 30/11/2017.
 */

public class Prodotto {
    private String nome;

    public Prodotto(){
        this.nome = null;
    }

    public Prodotto(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

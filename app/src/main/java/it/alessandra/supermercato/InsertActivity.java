package it.alessandra.supermercato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class InsertActivity extends AppCompatActivity {

    private Spinner tipoProdotto;
    private String valSpinner;
    private EditText editMarca;
    private EditText editPrezzo;
    private Button bInsert;
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Supermercato supermercato;
    private List<Prodotto> prodotti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editMarca = (EditText) findViewById(R.id.marca);
        editPrezzo = (EditText) findViewById(R.id.editprezzo);
        tipoProdotto = (Spinner) findViewById(R.id.spinner);
        bInsert = (Button) findViewById(R.id.binserisci);

        supermercato = (Supermercato) InternalStorage.readObject(this, "FileSupermercato");
        prodotti = supermercato.getProdotti();


        valSpinner = tipoProdotto.getSelectedItem().toString(); // valSpinner assumerà valore latte/carne/pesce

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReferenceFromUrl("https://dbsupermercato.firebaseio.com/Prodotti");

        bInsert.setOnClickListener(new View.OnClickListener() { //inserisce il prodotto su firebase
            @Override
            public void onClick(View v) {

                /* per creare nuovo prodotto */
                String marca = editMarca.getText().toString();
                String prezzo = editPrezzo.getText().toString();

                if (marca.equals("") || prezzo.equals("")) {
                    Toast.makeText(getApplicationContext(), "Inserire tutti i campi", Toast.LENGTH_LONG).show();
                } else {
                    double prezzod = Double.parseDouble(prezzo);
                    int control = controlloTipo(valSpinner);
                    if (control == 1) {
                        Carne prodottoC = new Carne(marca, prezzod);
                        prodotti.add(prodottoC);
                        /*inserimento su firebase*/
                    } else if (control == 2) {
                        Pesce prodottoP = new Pesce(marca, prezzod);
                        prodotti.add(prodottoP);
                    } else if (control == 3) {
                        Latte prodottoL = new Latte(marca, prezzod);
                        prodotti.add(prodottoL);
                    }
                    Toast.makeText(getApplicationContext(), "Prodotto inserito", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public int controlloTipo(String tipo) {
        if (tipo.equals("Carne")) {
            return 1;
        } else if (tipo.equals("Pesce")) {
            return 2;
        } else if (tipo.equals("Latte")) {
            return 3;
        } else return 0;
    }
}

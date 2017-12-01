package it.alessandra.supermercato;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class InsertActivity extends AppCompatActivity implements TaskDelegate {

    private Spinner tipoProdotto;
    private String valSpinner;
    private EditText editMarca;
    private EditText editPrezzo;
    private Button bInsert;
    private Supermercato supermercato;
    private List<Prodotto> prodotti;
    private TaskDelegate delegate;
    private ProgressDialog dialog;
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String marca;
    private String prezzo;

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

        marca ="";
        prezzo ="";

        delegate = this;

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReferenceFromUrl("https://dbsupermercato.firebaseio.com/Prodotti");

        bInsert.setOnClickListener(new View.OnClickListener() { //inserisce il prodotto su firebase
            @Override
            public void onClick(View v) {

                /* per creare nuovo prodotto */
                 marca = editMarca.getText().toString();
                 prezzo = editPrezzo.getText().toString();
                valSpinner = tipoProdotto.getSelectedItem().toString(); // valSpinner assumerà valore latte/carne/pesce

                if (marca.equals("") || prezzo.equals("")) {
                    Toast.makeText(getApplicationContext(), "Inserire tutti i campi", Toast.LENGTH_LONG).show();
                } else {
                    double prezzod = Double.parseDouble(prezzo);
                    int control = controlloTipo(valSpinner);
                    if (control == 1) {
                        Carne prodottoC = new Carne(marca, prezzod);
                        prodotti.add(prodottoC);
                    } else if (control == 2) {
                        Pesce prodottoP = new Pesce(marca, prezzod);
                        prodotti.add(prodottoP);
                    } else if (control == 3) {
                        Latte prodottoL = new Latte(marca, prezzod);
                        prodotti.add(prodottoL);
                    }
                    supermercato.setProdotti(prodotti);
                    restCallAddProd(delegate);
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

    public void restCallAddProd(final TaskDelegate delegate){
        dialog = new ProgressDialog(InsertActivity.this);
        dialog.setMessage("Caricamento");
        dialog.show();

        FirebaseRestClient.get("Prodotti.json", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String(responseBody);
                    int index = JsonParseProdotti.key(text);
                    databaseReference.child(valSpinner).child(generaKey(index)).child("Marca").setValue(marca);
                    databaseReference.child(valSpinner).child(generaKey(index)).child("Prezzo").setValue(prezzo);
                    delegate.TaskCompletionResult("Prodotto Registrato!");
                    Intent j = new Intent(getApplicationContext(),ProductActivity.class);
                    startActivity(j);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.TaskCompletionResult("NO!");
            }
        });
    }

    @Override
    public void TaskCompletionResult(String result) {
        dialog.dismiss();
        dialog.cancel();
        InternalStorage.writeObject(this,"FileSupermercato",supermercato);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }

    public String generaKey(int index) {
        String keyG = "";
        if (index < 10) {
            keyG = "0" + index;
        } else {
            keyG = "" + index;
        }
        return keyG;
    }
}

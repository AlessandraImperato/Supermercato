package it.alessandra.supermercato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {

    private Spinner tipoProdotto;
    private String valSpinner;
    private EditText editMarca;
    private EditText editPrezzo;
    private Button bInsert;
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editMarca = (EditText) findViewById(R.id.user);
        editPrezzo = (EditText) findViewById(R.id.editprezzo);
        tipoProdotto = (Spinner) findViewById(R.id.spinner);
        bInsert = (Button) findViewById(R.id.binserisci);

        valSpinner = tipoProdotto.getSelectedItem().toString(); // valSpinner assumerà valore latte/carne/pesce
        String marca = editMarca.getText().toString();
        //double prezzo = Double.parseDouble(editPrezzo.getText().toString());
        String prezzo = editPrezzo.getText().toString();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReferenceFromUrl("https://dbsupermercato.firebaseio.com/Prodotti");

        bInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inserisce il prodotto su firebase
            }
        });

    }
}

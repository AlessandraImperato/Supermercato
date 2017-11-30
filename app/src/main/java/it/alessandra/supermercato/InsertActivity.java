package it.alessandra.supermercato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InsertActivity extends AppCompatActivity {

    private Spinner tipoProdotto;
    private String valSpinner;
    private EditText editMarca;
    private EditText editPrezzo;
    private Button bInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editMarca = (EditText) findViewById(R.id.user);
        editPrezzo = (EditText) findViewById(R.id.editprezzo);
        tipoProdotto = (Spinner) findViewById(R.id.spinner);
        bInsert = (Button) findViewById(R.id.binserisci);

        valSpinner = tipoProdotto.getSelectedItem().toString(); // valSpinner assumerà valore latte/carne/pesce

    }
}

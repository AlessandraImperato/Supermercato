package it.alessandra.supermercato;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements TaskDelegate {

    private Button bMostra;
    private Button bRegistrati;
    private TextView benvenuto;
    private Supermercato supermercato;
    private List<Prodotto> prodotti;
    private ProgressDialog dialog;
    private SharedPreferences prefs;
    private TaskDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        supermercato = new Supermercato();
        prodotti = new ArrayList<>();

        String nomeUtente = "";

        bMostra = (Button) findViewById(R.id.bmostraprod);
        bRegistrati = (Button) findViewById(R.id.bregistra);
        benvenuto = (TextView) findViewById(R.id.benvenuto);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        nomeUtente = prefs.getString("NOMEUTENTE", "Ospite"); //Benvenuto ospite è il valore di default
        benvenuto.setText("Benvenuto " + nomeUtente);

        delegate = this;

        restCallprodotti(delegate);

        bRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });

        bMostra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*popolamento lista prodotti*/
                //restCallprodotti(delegate);
                Intent i = new Intent(getApplicationContext(),ProductActivity.class);
                startActivity(i);
            }
        });
    }

    public void restCallprodotti(final TaskDelegate delegate){
        dialog = new ProgressDialog(MainActivity.this); /**/
        dialog.setMessage("Caricamento");
        dialog.show();

        FirebaseRestClient.get("Prodotti.json", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    String text = new String (responseBody);
                    try{
                        prodotti = JsonParse.getList(text);
                        delegate.TaskCompletionResult("Prodotti caricati");
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.TaskCompletionResult("Caricamento fallito");
            }
        });
    }

    @Override
    public void TaskCompletionResult(String result) {
        dialog.dismiss();
        dialog.cancel();

        //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

        supermercato.setProdotti(prodotti);

        String nomeFile = "FileSupermercato";
        InternalStorage.writeObject(this,nomeFile,supermercato);
    }
}

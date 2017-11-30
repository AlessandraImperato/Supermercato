package it.alessandra.supermercato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button bMostra;
    private Button bRegistrati;
    private TextView benvenuto;
    private Supermercato supermercato;
    private List<Prodotto> prodotti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        supermercato = new Supermercato();
        prodotti = new ArrayList<>();

        bMostra = (Button) findViewById(R.id.bmostraprod);
        bRegistrati = (Button) findViewById(R.id.bregistra);
        benvenuto = (TextView) findViewById(R.id.benvenuto);

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
                Intent i = new Intent(getApplicationContext(),ProductActivity.class);
                startActivity(i);
            }
        });
    }
}

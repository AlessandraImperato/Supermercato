package it.alessandra.supermercato;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter myAdapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        recyclerView = (RecyclerView) findViewById(R.id.reciclerview);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);

        Supermercato supermercato = (Supermercato)InternalStorage.readObject(this,"FileSupermercato");
        List<Prodotto> prodotti = supermercato.getProdotti();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(prodotti,getApplicationContext());
        recyclerView.setAdapter(myAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),InsertActivity.class);
                startActivity(i);

            }
        });
    }
}

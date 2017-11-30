package it.alessandra.supermercato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private Button salvaUtente;
    private EditText user;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        salvaUtente = (Button) findViewById(R.id.bsalvautente);
        user = (EditText) findViewById(R.id.user);

        salvaUtente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user.getText().toString();
                if (username.equals("")){
                    Toast.makeText(getApplicationContext(),"Inserire l'username",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    // aggiungere salvataggio dell'utente su firebase
                }
            }
        });
    }
}

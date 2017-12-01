package it.alessandra.supermercato;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private Button salvaUtente;
    private EditText user;
    private String username;
    private SharedPreferences prefs;

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
                    /** salvo con la chiave "NOMEUTENTE" il nome inserito nell'editText, che poi devo riprendere nella MainActivity*/
                    prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("NOMEUTENTE", user.getText().toString());
                    editor.commit();
                    /**/

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);

                    // aggiungere salvataggio dell'utente su firebase
                }
            }
        });
    }
}

package it.alessandra.supermercato;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignUpActivity extends AppCompatActivity implements TaskDelegate {

    private Button salvaUtente;
    private EditText user;
    private String username;
    private SharedPreferences prefs;
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TaskDelegate delegate;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        salvaUtente = (Button) findViewById(R.id.bsalvautente);
        user = (EditText) findViewById(R.id.user);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReferenceFromUrl("https://dbsupermercato.firebaseio.com/User");
        delegate = this;


        salvaUtente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user.getText().toString();
                if (username.equals("")) {
                    Toast.makeText(getApplicationContext(), "Inserire l'username", Toast.LENGTH_LONG).show();
                } else {
                    /* salvo con la chiave "NOMEUTENTE" il nome inserito nell'editText, che poi devo riprendere nella MainActivity*/
                    prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("NOMEUTENTE", user.getText().toString());
                    editor.commit();

                    restCallUser(delegate);

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    public void restCallUser(final TaskDelegate delegate) {
        dialog = new ProgressDialog(SignUpActivity.this);
        dialog.setMessage("Caricamento");
        dialog.show();

        FirebaseRestClient.get("User.json", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String(responseBody);
                    int index = JsonParseUtente.key(text);
                    databaseReference.child(generaKey(index)).setValue(username);
                    delegate.TaskCompletionResult("Utente registrato!");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.TaskCompletionResult("NO!");
            }
        });
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

    @Override
    public void TaskCompletionResult(String result) {
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

    }
}

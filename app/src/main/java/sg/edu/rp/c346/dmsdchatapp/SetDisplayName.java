package sg.edu.rp.c346.dmsdchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetDisplayName extends AppCompatActivity {

    private static final String TAG = "SetDisplayName";

    EditText etName;
    Button btnSubmit;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference nameListReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_display_name);

        etName = (EditText) findViewById(R.id.etName);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        nameListReference = firebaseDatabase.getReference("profiles/");


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = firebaseUser.getUid();
                String displayName = etName.getText().toString();
                nameListReference.child(uid).setValue(displayName);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_logout){
            firebaseAuth.signOut();
            Intent i = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}

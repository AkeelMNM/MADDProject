package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {

    private static final String TAG ="AdminLoginActivity";
    EditText AName,APassword;
    Button btnLogIn;
    TextView userLog;
    String GName,GPassword,GType,GUserID,GEmail;
    private FirebaseAuth firebaseAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        AName=findViewById(R.id.loginAdminEmail);
        APassword=findViewById(R.id.loginAdminPassword);

        btnLogIn=findViewById(R.id.btnAdminLogIn);

        userLog=findViewById(R.id.userLogLink);

        firebaseAuth=FirebaseAuth.getInstance();

        userLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
    }

    private void logIn() {
        String name=AName.getText().toString().trim();
        String pass = APassword.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(name, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String id = user.getUid();
                            getUserDetails(id);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AdminLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void getUserDetails(final String id) {
        dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.Register.TABLE_NAME);
        Query data =dbRef.orderByChild(id);

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot st: dataSnapshot.getChildren()) {
                    GUserID=st.getKey();
                    GName=st.child(DBMaster.Register.COLUMN_NAME_USER_NAME).getValue().toString();
                    GPassword=st.child(DBMaster.Register.COLUMN_NAME_USER_PASSWORD).getValue().toString();
                    GType=st.child(DBMaster.Register.COLUMN_NAME_USER_TYPE).getValue().toString();
                    GEmail=st.child(DBMaster.Register.COLUMN_NAME_USER_EMAIL).getValue().toString();
                }
                SessionApplication.setUserID(GUserID);
                SessionApplication.setUserName(GName);
                SessionApplication.setUserType(GType);
                SessionApplication.setUserEmail(GEmail);

                Intent intent = new Intent(AdminLoginActivity.this,AdminHomeActivity.class);
                intent.putExtra("AdminName",GName);
                intent.putExtra("AdminID",id);
                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
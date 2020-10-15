package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG ="LoginActivity" ;
    EditText UName,UPassword;
    Button btnLogIn,btnLogUp;
    TextView adminLog;
    String GName,GPassword,GType,GUserID,GEmail,GResID,GJobT;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UName=findViewById(R.id.loginUserEmail);
        UPassword=findViewById(R.id.loginUserPassword);

        btnLogIn=findViewById(R.id.btnLogIn);
        btnLogUp=findViewById(R.id.btnLogUp);

        progressBar =findViewById(R.id.LoginProgressBar);
        progressBar.setVisibility(View.INVISIBLE);

        firebaseAuth=FirebaseAuth.getInstance();

        adminLog=findViewById(R.id.adminLogLink);

        btnLogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,UserRegisterActivity.class);
                startActivity(intent);
            }
        });

        adminLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,AdminLoginActivity.class);
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
        String name=UName.getText().toString().trim();
        String pass = UPassword.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(name, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            progressBar.setVisibility(View.VISIBLE);
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String id = user.getUid();
                            setResumeAndJobDetails(id);
                            getUserDetails(id);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void getUserDetails(String id) {
        dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.Register.TABLE_NAME).child(id);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){
                    GUserID=dataSnapshot.getKey();
                    GName=dataSnapshot.child(DBMaster.Register.COLUMN_NAME_USER_NAME).getValue().toString();
                    GPassword=dataSnapshot.child(DBMaster.Register.COLUMN_NAME_USER_PASSWORD).getValue().toString();
                    GType=dataSnapshot.child(DBMaster.Register.COLUMN_NAME_USER_TYPE).getValue().toString();
                    GEmail=dataSnapshot.child(DBMaster.Register.COLUMN_NAME_USER_EMAIL).getValue().toString();
                }
                SessionApplication.setUserID(GUserID);
                SessionApplication.setUserName(GName);
                SessionApplication.setUserType(GType);
                SessionApplication.setUserEmail(GEmail);

                if(GType.equals("Job Seeker")){
                    Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else if(GType.equals("Company")){

                    Intent intent= new Intent(LoginActivity.this,CompanyHomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Sorry User Authentication failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setResumeAndJobDetails(String id){

        dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
        Query data = dbRef.orderByChild(DBMaster.Resume.COLUMN_NAME_USER_ID).equalTo(id);

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot d : dataSnapshot.getChildren()){
                    GResID = d.getKey();
                    GJobT = d.child(DBMaster.Resume.COLUMN_NAME_JOB_TITLE).getValue().toString();
                }
                SessionApplication.setResumeID(GResID);
                SessionApplication.setJobName(GJobT);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
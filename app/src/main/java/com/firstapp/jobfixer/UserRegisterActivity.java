package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegisterActivity extends AppCompatActivity {

    EditText name,password,email;
    Button btnRe,btnLogin;
    String type;
    String UName,UPassword,UEmail;
    DatabaseReference dbRef;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        name=findViewById(R.id.RegisterUName);
        password=findViewById(R.id.RegisterUPassword);
        email=findViewById(R.id.RegisterUEmail);

        btnLogin=findViewById(R.id.btnRegisterToLogin);
        btnRe=findViewById(R.id.btnUserRegister);

        progressBar=findViewById(R.id.RegisterprogressBar);
        progressBar.setVisibility(View.INVISIBLE);

        firebaseAuth=FirebaseAuth.getInstance();

        Spinner uType = (Spinner) findViewById(R.id.RegisterUTypeSpinner);

        /**Assigning the values to Job Category Spinner in the Activity**/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.UserTy));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uType.setAdapter(arrayAdapter);

        uType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                type = parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /**Reading the values in Job Type Spinner in the Activity**/
        //type = uType.getSelectedItem().toString().trim();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                registerUser(type);
            }
        });

    }

    private void registerUser(final String type) {

        UName=name.getText().toString().trim();
        UPassword=password.getText().toString().trim();
        UEmail = email.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(UEmail,UPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    Register register =new Register(UName,UEmail,UPassword,type);
                    dbRef  = FirebaseDatabase.getInstance().getReference().child(DBMaster.Register.TABLE_NAME);
                    dbRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(register);

                    Toast.makeText(UserRegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

                    finish();
                    Intent intent = new Intent(UserRegisterActivity.this,LoginActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(UserRegisterActivity.this, "Could not create account. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
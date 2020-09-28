package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firstapp.jobfixer.Model.LoggedInUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import DataBase.DBMaster;

public class Register extends AppCompatActivity {
    EditText RUserName ,REmail ,RPassword;
    Button SignUp ,SignIn ;
    String UserTypo ;
    DatabaseReference dbRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        RUserName = findViewById(R.id.txtUserName);
        REmail = findViewById(R.id.txtReCreEmail);
        RPassword = findViewById(R.id.txtPassword);
        SignUp = findViewById(R.id.btnSignUp);
        SignIn = findViewById(R.id.btnSignIn);
        Spinner userType = (Spinner) findViewById(R.id.UserTypeSpinner);

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getString(R.array.Account_Cat));//ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getString(R.array.Account_Cat))
        /**ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getString(R.array.Account_Cat));
         arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         userType.setAdapter(arrayAdapter);
         UserTypo = userType.getSelectedItem().toString();**/

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.UserLogin.TABLE_NAME_USER_LOGIN);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        LoggedInUser loggedInUser = new LoggedInUser();
                        loggedInUser.setUserName(RUserName.getText().toString().trim());
                        loggedInUser.setEmail(REmail.getText().toString().trim());
                        loggedInUser.setPassword(RPassword.getText().toString().trim());
                        loggedInUser.setUserType("Company");

                        dbRef.child("log03").setValue(loggedInUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(Register.this,LoginActivity.class);
                //startActivity(intent);
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


}
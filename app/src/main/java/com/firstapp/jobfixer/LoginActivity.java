package com.firstapp.jobfixer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import DataBase.DBMaster;

public class LoginActivity extends AppCompatActivity {

    EditText username ,password ;
    String chkPass, UserId , UserType;
    Button SignIn ,SignUp ;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.txtUserName);
        password = findViewById(R.id.txtPassword);

        SignIn = findViewById(R.id.btnSignIn);
        SignUp = findViewById(R.id.btnSignUp);



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query = dbRef.orderByChild(DBMaster.UserLogin.TABLE_NAME_USER_LOGIN).equalTo(username.getText().toString().trim());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            chkPass = dataSnapshot.child(DBMaster.UserLogin.COLUMN_NAME_PASSWORD).getValue().toString().trim();
                            UserId = dataSnapshot.getKey().toString();
                            UserType = dataSnapshot.child(DBMaster.UserLogin.COLUMN_NAME_USER_TYPE).getValue().toString().trim();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

                if(chkPass.equals(password.getText().toString().trim())){

                    if(UserType.equals("Company")){
                        Intent intent = new Intent(LoginActivity.this,CompanyHomeActivity.class);
                        intent.putExtra("UserId",UserId);
                        startActivity(intent);
                    }
                    if(UserType.equals("Applicant")){

                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("UserId",UserId);
                        startActivity(intent);
                    }
                }
            }

        });


    }


}

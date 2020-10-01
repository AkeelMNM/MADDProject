package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserRegisterActivity extends AppCompatActivity {

    EditText name,password,email;
    Button btnRe,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        name=findViewById(R.id.RegisterUName);
        password=findViewById(R.id.RegisterUPassword);
        email=findViewById(R.id.RegisterUEmail);

        btnLogin=findViewById(R.id.btnRegisterToLogin);
        btnRe=findViewById(R.id.btnUserRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
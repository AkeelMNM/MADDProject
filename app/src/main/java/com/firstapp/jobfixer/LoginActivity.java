package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText UName,UPassword;
    Button btnLogIn,btnLogUp;
    TextView adminLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UName=findViewById(R.id.loginUserEmail);
        UPassword=findViewById(R.id.loginUserPassword);

        btnLogIn=findViewById(R.id.btnLogIn);
        btnLogUp=findViewById(R.id.btnLogUp);

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
    }
}
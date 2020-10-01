package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

public class AdminLoginActivity extends AppCompatActivity {

    EditText AName,APassword;
    Button btnLogIn;
    TextView userLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        AName=findViewById(R.id.loginAdminEmail);
        APassword=findViewById(R.id.loginAdminPassword);

        btnLogIn=findViewById(R.id.btnAdminLogIn);

        userLog=findViewById(R.id.userLogLink);

        userLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
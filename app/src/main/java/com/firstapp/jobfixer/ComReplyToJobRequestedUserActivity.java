package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ComReplyToJobRequestedUserActivity extends AppCompatActivity {

    EditText compName,compEmail,AppName,AppEmail,compMsg;
    Button btnSend,btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_reply_to_job_requested_user);

        compName=findViewById(R.id.sendComName);
        compEmail=findViewById(R.id.sendComEmail);
        AppName=findViewById(R.id.sendApplicantName);
        AppEmail=findViewById(R.id.sendApplicantEmail);
        compMsg=findViewById(R.id.sendCompMessage);

        btnSend=findViewById(R.id.btnSendMsgToUser);
        btnGoBack=findViewById(R.id.btnGoBackViewReq);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
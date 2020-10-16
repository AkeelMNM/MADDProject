package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.SendReplyToApplicant;
import com.firstapp.jobfixer.Model.SendRequestToCompany;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ComReplyToJobRequestedUserActivity extends AppCompatActivity {

    EditText compName,compEmail,AppName,AppEmail,compMsg;
    Button btnSend,btnGoBack;
    String uId,JName;

    DatabaseReference dbRef;

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

        /** Retrieving the RecycleView item details from JobAdAdminViewActivity.java class via Intent**/
        Bundle bundle = getIntent().getExtras();
        compName.setText(SessionApplication.getUserName());
        compEmail.setText(SessionApplication.getUserEmail());
        AppName.setText(bundle.getString("AppName"));
        AppEmail.setText(bundle.getString("AppEmail"));
        uId=bundle.getString("ApplicantID");
        JName=bundle.getString("AppJob");

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComReplyToJobRequestedUserActivity.this,CompViewAllApplicantJobRequestActivity.class);
                startActivity(intent);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.SendReplyToApplicant.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        SendReplyToApplicant req = new SendReplyToApplicant();
                        req.setUserID(uId);
                        req.setApplicantName(AppName.getText().toString().trim());
                        req.setApplicantEmail(AppEmail.getText().toString().trim());
                        req.setSendDate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                        req.setJobName(JName);
                        req.setCompName(compName.getText().toString().trim());
                        req.setCompEmail(compEmail.getText().toString().trim());
                        req.setCompMsg(compMsg.getText().toString().trim());

                        //Insert into Database
                        dbRef.push().setValue(req);


                        Toast.makeText(ComReplyToJobRequestedUserActivity.this, "Request Send Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ComReplyToJobRequestedUserActivity.this,CompViewAllApplicantJobRequestActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /** Menu bar actions**/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                helpCenter();
                return true;
            case R.id.action_logout:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Logout from device**/
    private void logOut() {
        SessionApplication.setUserID("");
        SessionApplication.setUserName("");
        SessionApplication.setUserType("");
        SessionApplication.setUserEmail("");

        Intent intent = new Intent(ComReplyToJobRequestedUserActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(ComReplyToJobRequestedUserActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(ComReplyToJobRequestedUserActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}
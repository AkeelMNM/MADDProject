package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.SendRequestToCompany;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JobViewForUserActivity extends AppCompatActivity {

    TextView jc,jt,cn,ca,jq,jType,jSal;
    Button btnAp,btnHome;
    DatabaseReference dbRef;
    String jID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view_for_user);

        jc=findViewById(R.id.txtFJC);
        jt=findViewById(R.id.txtFJT);
        cn=findViewById(R.id.txtFCN);
        ca=findViewById(R.id.txtFCA);
        jq=findViewById(R.id.txtFJReq);
        jType=findViewById(R.id.txtFJType);
        jSal=findViewById(R.id.txtFJSal);

        btnAp=findViewById(R.id.bApp);
        btnHome=findViewById(R.id.bHome);

        /** Retrieving the RecycleView item details from JobAdAdminViewActivity.java class via Intent**/
        Bundle bundle = getIntent().getExtras();
        jc.setText(bundle.getString("jFCat"));
        jt.setText(bundle.getString("jFTitle"));
        cn.setText(bundle.getString("cFName"));
        ca.setText(bundle.getString("cFLoc"));
        jSal.setText(bundle.getString("jFSal"));
        jType.setText(bundle.getString("jFType"));
        jq.setText(bundle.getString("jFQua"));
        jID =bundle.getString("jFID");


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobViewForUserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.SendRequestToCompany.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        SendRequestToCompany req = new SendRequestToCompany();
                        req.setJobID(jID);
                        req.setUserID(SessionApplication.getUserID());
                        req.setApplicantName(SessionApplication.getUserName());
                        req.setApplicantEmail(SessionApplication.getUserEmail());
                        req.setApplyDate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                        req.setJobName(jt.getText().toString().trim());
                        req.setCompName(cn.getText().toString().trim());
                        req.setResumeID(SessionApplication.getResumeID());

                        //Insert into Database
                        dbRef.push().setValue(req);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(JobViewForUserActivity.this, "Request Send Successfully", Toast.LENGTH_SHORT).show();
            }
        });
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

        Intent intent = new Intent(JobViewForUserActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
         Intent intent = new Intent(JobViewForUserActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(JobViewForUserActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}
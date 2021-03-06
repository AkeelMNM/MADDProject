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
import android.widget.TextView;

import com.firstapp.jobfixer.Database.DBMaster;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CompanyViewResumeActivity extends AppCompatActivity {

    TextView fName,lName,location,phone,email,jName,aboutMe,workEx,Edu;
    Button btnResp,btnCancel;
    String UEmail,AName1,AName2,AJob,AID;

    DatabaseReference dBRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_view_resume);

        fName = findViewById(R.id.compVfname);
        lName=findViewById(R.id.compVLname);
        location=findViewById(R.id.compVLocation);
        phone=findViewById(R.id.compVPhone);
        email=findViewById(R.id.compVEmail);
        jName=findViewById(R.id.compVJobs);
        aboutMe=findViewById(R.id.compVAboutme);
        workEx=findViewById(R.id.compVWorkExp);
        Edu=findViewById(R.id.compVEduc);

        btnResp = findViewById(R.id.btncompVrespond);
        btnCancel=findViewById(R.id.btncompVCancel);

        Bundle bundle = getIntent().getExtras();
        UEmail = bundle.getString("ApplicantEmail");

        dBRead = FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
        Query data = dBRead.orderByChild(DBMaster.Resume.COLUMN_NAME_EMAIL).equalTo(UEmail);

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot st:dataSnapshot.getChildren()){
                    fName.setText(st.child(DBMaster.Resume.COLUMN_NAME_FIRST_NAME).getValue().toString());
                    lName.setText(st.child(DBMaster.Resume.COLUMN_NAME_LAST_NAME).getValue().toString());
                    location.setText(st.child(DBMaster.Resume.COLUMN_NAME_LOCATION).getValue().toString());
                    phone.setText(st.child(DBMaster.Resume.COLUMN_NAME_PHONE).getValue().toString());
                    email.setText(st.child(DBMaster.Resume.COLUMN_NAME_EMAIL).getValue().toString());
                    jName.setText(st.child(DBMaster.Resume.COLUMN_NAME_JOB_TITLE).getValue().toString());
                    aboutMe.setText(st.child(DBMaster.Resume.COLUMN_NAME_JOB_ABOUT_ME).getValue().toString());
                    workEx.setText(st.child(DBMaster.Resume.COLUMN_NAME_JOB_WORK_EXPERIENCE).getValue().toString());
                    Edu.setText(st.child(DBMaster.Resume.COLUMN_NAME_JOB_EDUCTION).getValue().toString());

                    AID = st.child(DBMaster.Resume.COLUMN_NAME_USER_ID).getValue().toString();
                    AName1 = st.child(DBMaster.Resume.COLUMN_NAME_FIRST_NAME).getValue().toString();
                    AName2=st.child(DBMaster.Resume.COLUMN_NAME_LAST_NAME).getValue().toString();
                    AJob=st.child(DBMaster.Resume.COLUMN_NAME_JOB_TITLE).getValue().toString();

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyViewResumeActivity.this,ComReplyToJobRequestedUserActivity.class);
                intent.putExtra("ApplicantID",AID);
                intent.putExtra("AppName",AName1+" "+AName2);
                intent.putExtra("AppEmail",UEmail);
                intent.putExtra("AppJob",AJob);
                startActivity(intent);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyViewResumeActivity.this,CompViewAllApplicantJobRequestActivity.class);
                startActivity(intent);
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

        Intent intent = new Intent(CompanyViewResumeActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(CompanyViewResumeActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(CompanyViewResumeActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}
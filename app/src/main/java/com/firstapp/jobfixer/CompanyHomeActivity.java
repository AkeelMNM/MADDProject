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

public class CompanyHomeActivity extends AppCompatActivity {

    Button HCompJobs,HCompProfile,HCompRequests,HCompHelpCent;
    String SesUID,SesName, SesType, SesEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);

        HCompJobs=findViewById(R.id.btnCompanyJobs);
        HCompProfile=findViewById(R.id.btnCompanyProfile);
        HCompRequests=findViewById(R.id.btnCompanyRequest);
        HCompHelpCent=findViewById(R.id.btnCompanyHelpCenter);

        /** Session Application class to get user Details**/
        SesUID=SessionApplication.getUserID();
        SesName=SessionApplication.getUserName();
        SesType=SessionApplication.getUserType();
        SesEmail=SessionApplication.getUserEmail();

        HCompJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyHomeActivity.this,CompanyViewJobActivity.class);
                startActivity(intent);
            }
        });

        HCompHelpCent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyHomeActivity.this,HelpCenterActivity.class);
                startActivity(intent);
            }
        });

        HCompRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyHomeActivity.this,CompViewAllApplicantJobRequestActivity.class);
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

        Intent intent = new Intent(CompanyHomeActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
         Intent intent = new Intent(CompanyHomeActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(CompanyHomeActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}
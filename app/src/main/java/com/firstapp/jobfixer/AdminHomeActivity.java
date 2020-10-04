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

public class AdminHomeActivity extends AppCompatActivity {

    Button HAdminJobsAds,HAdminJobApproval,HCompRequests,HAdminHelpCent;
    String SesUID, SesName,SesType,SesEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        HAdminJobsAds=findViewById(R.id.btnAdmJobsAds);
        HAdminHelpCent=findViewById(R.id.btnAdminHelpCenter);

        /** Session Application class to get user Details**/
        SesUID=SessionApplication.getUserID();
        SesName=SessionApplication.getUserName();
        SesType=SessionApplication.getUserType();
        SesEmail=SessionApplication.getUserEmail();


        HAdminJobsAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this,JobAdAdminViewActivity.class);
                startActivity(intent);
            }
        });

        HAdminHelpCent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this,UserRequestsActivity.class);
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

        Intent intent = new Intent(AdminHomeActivity.this,AdminLoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
         Intent intent = new Intent(AdminHomeActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(AdminHomeActivity.this,AdminLoginActivity.class);
            startActivity(intent);
        }

    }
}
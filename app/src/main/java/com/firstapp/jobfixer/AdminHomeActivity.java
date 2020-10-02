package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
        HAdminJobApproval=findViewById(R.id.btnAdminCompanyJobApproval);

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
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
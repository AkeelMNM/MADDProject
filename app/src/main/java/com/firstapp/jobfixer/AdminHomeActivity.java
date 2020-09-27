package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

public class AdminHomeActivity extends AppCompatActivity {

    Button HAdminJobsAds,HAdminJobApproval,HCompRequests,HAdminHelpCent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        HAdminJobsAds=findViewById(R.id.btnAdmJobsAds);
        HAdminHelpCent=findViewById(R.id.btnAdminHelpCenter);
        HAdminJobApproval=findViewById(R.id.btnAdminCompanyJobApproval);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
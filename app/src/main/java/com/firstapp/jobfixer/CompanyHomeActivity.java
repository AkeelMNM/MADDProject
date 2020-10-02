package com.firstapp.jobfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
        HCompHelpCent=findViewById(R.id.btnUserHelpCenter);

        /** Session Application class to get user Details**/
        SesUID=SessionApplication.getUserID();
        SesName=SessionApplication.getUserName();
        SesType=SessionApplication.getUserType();
        SesEmail=SessionApplication.getUserEmail();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
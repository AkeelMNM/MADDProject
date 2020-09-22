package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobAdUpdateActivity extends AppCompatActivity {

    EditText txtCompanyAddress,txtJobSalary,txtQualification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_ad_update);


        Spinner txtJobCategory = (Spinner) findViewById(R.id.JobCatAddSpinner);
        Spinner txtJobTitle = (Spinner) findViewById(R.id.JobTiAddSpinner);
        Spinner txtCompanyName = (Spinner) findViewById(R.id.ComNaAddSpinner);
        Spinner txtJobType = (Spinner) findViewById(R.id.jobTypeAddSpinner);

        txtCompanyAddress = findViewById(R.id.TxtInputUpComAdd);
        txtJobSalary = findViewById(R.id.txtInputUpJobSal);
        txtQualification = findViewById(R.id.TxtInputUpJobQualic);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("KEY");

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME).child(id);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtCompanyAddress.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_COMPANY_ADDRESS).getValue().toString());
                    //txtJobTitle.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_COMPANY_NAME).getValue().toString());
                    txtJobSalary.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_SALARY).getValue().toString());
                    //txtTit.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_TITLE).getValue().toString());
                    //txtJobType.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_TYPE).getValue().toString());
                    txtQualification.setText(dataSnapshot.child(DBMaster.Advertisement.COLUMN_NAME_JOB_QUALIFICATION).getValue().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
        public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
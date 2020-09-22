package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.AdService;
import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Advertisement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobAdCreateActivity extends AppCompatActivity {


    EditText txtCompanyAddress,txtJobSalary,txtQualification;


    Advertisement ad;
    DatabaseReference dbRef;
    Button btnSave;

    //Method to clear all inputs
    private  void clearControls(){
        txtCompanyAddress.setText("");
        txtJobSalary.setText("");
        txtQualification.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_ad_create);

        Spinner txtJobCategory = (Spinner) findViewById(R.id.JobCatAddSpinner);
        Spinner txtJobTitle = (Spinner) findViewById(R.id.JobTiAddSpinner);
        Spinner txtCompanyName = (Spinner) findViewById(R.id.ComNaAddSpinner);
        Spinner txtJobType = (Spinner) findViewById(R.id.jobTypeAddSpinner);

        txtCompanyAddress = findViewById(R.id.TxtInputCompAdd);
        txtJobSalary = findViewById(R.id.TxtInputJobSal);
        txtQualification = findViewById(R.id.txtInputQualifcAdd);

        btnSave=findViewById(R.id.btnCreate);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef  = FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Advertisement ad = new Advertisement();
                        ad.setJobID("J001");
                        ad.setJobCategory("AR");
                        ad.setJobTitle("HR");
                        ad.setCompanyName("IFS");
                        ad.setJobType("FullTime");
                        ad.setCompanyAddress(txtCompanyAddress.getText().toString().trim());
                        ad.setJobSalary(txtJobSalary.getText().toString().trim());
                        ad.setQualification(txtQualification.getText().toString().trim());

                        //Insert into Database
                        //dbRef.push().setValue(std);
                        dbRef.child("Ad1").setValue(ad);

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
}
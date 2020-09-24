package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firstapp.jobfixer.Database.DBMaster;
import com.firstapp.jobfixer.Model.Advertisement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobAdUpdateActivity extends AppCompatActivity {

    EditText txtCompanyAddress,txtJobSalary,txtQualification,txtJobID;
    Button btnUpdate;
    String jName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_ad_update);

        Spinner spJobCategory = (Spinner) findViewById(R.id.JobCatAddSpinner);
        Spinner spJobTitle = (Spinner) findViewById(R.id.JobTiAddSpinner);
        Spinner spCompanyName = (Spinner) findViewById(R.id.ComNaAddSpinner);
        Spinner spJobType = (Spinner) findViewById(R.id.jobTypeAddSpinner);

        txtCompanyAddress = findViewById(R.id.TxtInputUpComAdd);
        txtJobSalary = findViewById(R.id.txtInputUpJobSal);
        txtQualification = findViewById(R.id.TxtInputUpJobQualic);
        txtJobID=findViewById(R.id.txtJobID);

        Bundle bundle = getIntent().getExtras();
        txtCompanyAddress.setText(bundle.getString("cLoc"));
        txtJobSalary.setText(bundle.getString("jSal"));
        txtQualification.setText(bundle.getString("jQua"));
        txtJobID.setText(bundle.getString("jID"));

        ArrayList<String> jCat = new ArrayList<String>();
        jCat.add(bundle.getString("jCat"));
        ArrayAdapter<String> arrayAdapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jCat);
        arrayAdapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobCategory.setAdapter(arrayAdapterC);

        ArrayList<String> jTit = new ArrayList<String>();
        jTit.add(bundle.getString("jTitle"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jTit);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobTitle.setAdapter(arrayAdapter);


        ArrayList<String> cN = new ArrayList<String>();
        cN.add(bundle.getString("cName"));
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cN);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCompanyName.setAdapter(arrayAdapter1);

        ArrayList<String> jT = new ArrayList<String>();
        jT.add(bundle.getString("jType"));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jT);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobType.setAdapter(arrayAdapter2);

        /*btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference upRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChild(txtJobID.getText().toString().trim())) {
                            Advertisement ad = new Advertisement();
                            ad.setJobID("J002");
                            //ad.setJobCategory(Select);
                            //ad.setJobTitle(Select2);
                            ad.setCompanyName("IFS");
                            //ad.setJobType(Select3);
                            ad.setCompanyAddress(txtCompanyAddress.getText().toString().trim());
                            ad.setJobSalary(txtJobSalary.getText().toString().trim());
                            ad.setQualification(txtQualification.getText().toString().trim());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });*/


    }
        public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
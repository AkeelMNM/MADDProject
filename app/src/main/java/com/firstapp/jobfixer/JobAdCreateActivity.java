package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
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

public class JobAdCreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<String> mJobID = new ArrayList<>();
    public static final String AD_ID_PREFIX ="AD0";

    EditText txtCompanyAddress, txtJobSalary, txtQualification;
    String Select,Select2,Select3;

    Advertisement ad;
    DatabaseReference dbRef;
    Button btnSave;

    //Method to clear all inputs
    private void clearControls() {
        txtCompanyAddress.setText("");
        txtJobSalary.setText("");
        txtQualification.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_ad_create);

        /**Accessing the Spinners,TextViews and button in the Activity**/

        Spinner spJobCategory = (Spinner) findViewById(R.id.JobCatAddSpinner);
        Spinner spCompanyName = (Spinner) findViewById(R.id.ComNaAddSpinner);
        Spinner spJobType = (Spinner) findViewById(R.id.jobTypeAddSpinner);

        txtCompanyAddress = findViewById(R.id.TxtInputCompAdd);
        txtJobSalary = findViewById(R.id.TxtInputJobSal);
        txtQualification = findViewById(R.id.txtInputQualifcAdd);
        btnSave = findViewById(R.id.btnCreate);

        /**Assigning the values to Job Category Spinner in the Activity**/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jobCat));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobCategory.setAdapter(arrayAdapter);
        spJobCategory.setOnItemSelectedListener(this);

        /**Assigning the values to Job Type Spinner in the Activity**/
        ArrayAdapter<String> arrayAdapterTy = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jobTy));
        arrayAdapterTy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobType.setAdapter(arrayAdapterTy);

        /**Reading the values in Job Type Spinner in the Activity**/
        Select3 = spJobType.getSelectedItem().toString();

        /**Adding a new advertisement in firebase DB**/
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Advertisement ad = new Advertisement();
                        ad.setJobID("J003");
                        ad.setJobCategory(Select);
                        ad.setJobTitle(Select2);
                        ad.setCompanyName("IFS");
                        ad.setJobType(Select3);
                        ad.setCompanyAddress(txtCompanyAddress.getText().toString().trim());
                        ad.setJobSalary(txtJobSalary.getText().toString().trim());
                        ad.setQualification(txtQualification.getText().toString().trim());

                        for(DataSnapshot st: dataSnapshot.getChildren()){
                            mJobID.add(st.getKey().toString());
                        }

                        String id = null;
                        int alSize = mJobID.size();
                        alSize++;
                        id=AD_ID_PREFIX + alSize;
                        if(mJobID.contains(id))
                        {
                            alSize++;
                            id=AD_ID_PREFIX + alSize;
                        }

                        //Insert into Database
                        dbRef.child(id).setValue(ad);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(JobAdCreateActivity.this, "Advertisement Created Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(JobAdCreateActivity.this,JobAdAdminViewActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){

        /**Reading the values in Job Category Spinner in the Activity**/
        Select = parent.getItemAtPosition(position).toString();


        Spinner spJobTitle = (Spinner) findViewById(R.id.JobTiAddSpinner);

        /**Change the values in Job Title according to the user selection of the job Category and assigning values to the job title Spinner in the Activity**/

        if (Select.equals("Information Technology")) {
            ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.itJobCat));
            arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spJobTitle.setAdapter(arrayAdapterJ);

        }
        else if(Select.equals("Business Management and Administration")){
            ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bmJobCat));
            arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spJobTitle.setAdapter(arrayAdapterJ);

        }
        else if(Select.equals("Engineering")){
            ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.enJobCat));
            arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spJobTitle.setAdapter(arrayAdapterJ);

        }
        else if(Select.equals("Hospitality and Tourism")){
            ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.htJobCat));
            arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spJobTitle.setAdapter(arrayAdapterJ);
        }
        else if(Select.equals("Architecture and Construction")){
            ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.arJobCat));
            arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spJobTitle.setAdapter(arrayAdapterJ);
        }

        /**Reading the values in Job Title Spinner in the Activity**/
        Select2 = spJobTitle.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }

    /*private String getAdID(){

        Query data = FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot st: dataSnapshot.getChildren()){
                    mJobID.add(st.getKey().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }*/
}
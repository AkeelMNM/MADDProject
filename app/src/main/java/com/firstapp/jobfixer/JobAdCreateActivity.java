package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobAdCreateActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener {

    private ArrayList<String> AdID = new ArrayList<>();
    public static final String AD_ID_PREFIX ="AD0";
    ArrayList<String> ComN = new ArrayList<String>();

    EditText txtCompanyAddress, txtJobSalary, txtQualification;
    String Select,Select2,Select3,Select4,Address,jType,jQu,jSal;

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
        final Spinner spJobType = (Spinner) findViewById(R.id.jobTypeAddSpinner);
        final Spinner spCompanyName = (Spinner) findViewById(R.id.ComNaAddSpinner);
        final Spinner spJobTitle = (Spinner) findViewById(R.id.JobTiAddSpinner);


        txtCompanyAddress = findViewById(R.id.TxtInputCompAdd);
        txtJobSalary = findViewById(R.id.TxtInputJobSal);
        txtQualification = findViewById(R.id.txtInputQualifcAdd);
        btnSave = findViewById(R.id.btnCreate);

        /**Assigning the values to Job Category Spinner in the Activity**/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jobCat));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJobCategory.setPrompt("Select a Category");
        spJobCategory.setAdapter(arrayAdapter);

        /**Reading the values in Job Category Spinner in the Activity**/
        spJobCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Select = parent.getItemAtPosition(position).toString();

                /**Change the values in Job Title according to the user selection of the job Category and assigning values to the job title Spinner in the Activity**/

                if (Select.equals("Information Technology")) {
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.itJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spJobTitle.setAdapter(arrayAdapterJ);

                } else if (Select.equals("Business Management and Administration")) {
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bmJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spJobTitle.setAdapter(arrayAdapterJ);


                } else if (Select.equals("Engineering")) {
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.enJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spJobTitle.setAdapter(arrayAdapterJ);

                } else if (Select.equals("Hospitality and Tourism")) {
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.htJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spJobTitle.setAdapter(arrayAdapterJ);
                } else if (Select.equals("Architecture and Construction")) {
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.arJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spJobTitle.setAdapter(arrayAdapterJ);
                } else {
                    ArrayList<String> ar = new ArrayList<>();
                    ar.add("Select a job title");
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item, ar);
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spJobTitle.setAdapter(arrayAdapterJ);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**Setting a first value to display in Company Spinner in the Activity**/
        ComN.add("Select Company Name");

        /**Reading the values in Job Title Spinner in the Activity**/
        spJobTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Select2 = parent.getSelectedItem().toString();

                /**Retrieving the Company Name from firebase to assigning to Company Name EditText in the Activity**/
                dbRef=FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
                Query data = dbRef.orderByChild(DBMaster.Job.COLUMN_NAME_TITLE).equalTo(Select2);

                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for(DataSnapshot st: dataSnapshot.getChildren()){
                            ComN.add(st.child(DBMaster.Job.COLUMN_NAME_COMPANY_NAME).getValue().toString());
                        }

                        /** assigning the company names to ths job spinner**/
                        ArrayAdapter<String> arrayAdapterCompName = new ArrayAdapter<>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item,ComN);
                        arrayAdapterCompName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCompanyName.setAdapter(arrayAdapterCompName);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**Reading the values in Company Name Spinner in the Activity**/
        spCompanyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Select4 = parent.getSelectedItem().toString();

                /**Retrieving the values from firebase to assigning to EditTexts in the Activity**/
                dbRef=FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
                Query data = dbRef.orderByChild(DBMaster.Job.COLUMN_NAME_COMPANY_NAME).equalTo(Select4);

                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot st : dataSnapshot.getChildren()){
                            Address = st.child(DBMaster.Job.COLUMN_NAME_COMPANY_ADDRESS).getValue().toString();
                            jSal=st.child(DBMaster.Job.COLUMN_NAME_SALARY).getValue().toString();
                            jQu=st.child(DBMaster.Job.COLUMN_NAME_DESCRIPTION).getValue().toString();
                            jType=st.child(DBMaster.Job.COLUMN_NAME_TYPE).getValue().toString();
                        }

                        /**Assigning the values to All EditText in the Activity**/
                        txtCompanyAddress.setText(Address);
                        txtQualification.setText(jQu);
                        txtJobSalary.setText(jSal);

                        /**Assigning the values to Job Type Spinner in the Activity**/
                        ArrayAdapter<String> arrayAdapterTy = new ArrayAdapter<String>(JobAdCreateActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jobTy));
                        arrayAdapterTy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spJobType.setAdapter(arrayAdapterTy);
                        if(jType == "Full Time"){
                            spJobType.setSelection(0);
                        }
                        else if(jType == "Part Time"){
                            spJobType.setSelection(1);
                        }
                        else if (jType == "Internship"){
                            spJobType.setSelection(2);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**Reading the values in Job Type Spinner in the Activity**/
        spJobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /**Reading the values in Job Type Spinner in the Activity**/
                Select3 = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**Adding a new advertisement in firebase DB**/
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Advertisement.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Advertisement ad = new Advertisement();
                            ad.setJobCategory(Select);
                            ad.setJobTitle(Select2);
                            ad.setCompanyName(Select4);
                            ad.setJobType(Select3);
                            ad.setCompanyAddress(txtCompanyAddress.getText().toString().trim());
                            ad.setJobSalary(txtJobSalary.getText().toString().trim());
                            ad.setQualification(txtQualification.getText().toString().trim());
                        //}

                        for(DataSnapshot st: dataSnapshot.getChildren()){
                            AdID.add(st.getKey().toString());
                        }

                        String id = null;
                        int alSize = AdID.size();
                        alSize++;
                        id=AD_ID_PREFIX + alSize;
                        if(AdID.contains(id))
                        {
                            alSize++;
                            id=AD_ID_PREFIX + alSize;
                        }

                         if(TextUtils.isEmpty(txtCompanyAddress.getText())){
                            Toast.makeText(JobAdCreateActivity.this,"Enter Company Address.. ",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtJobSalary.getText())){
                            Toast.makeText(JobAdCreateActivity.this,"Enter Salary.. ",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtQualification.getText())){
                            Toast.makeText(JobAdCreateActivity.this,"Enter Description.. ",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            //Insert into Database
                            dbRef.child(id).setValue(ad);
                        }


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

    /**Menu Bar**/
    public boolean onCreateOptionsMenu (Menu menu){
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

        Intent intent = new Intent(JobAdCreateActivity.this,AdminLoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
         Intent intent = new Intent(JobAdCreateActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(JobAdCreateActivity.this,AdminLoginActivity.class);
            startActivity(intent);

        }

    }

}
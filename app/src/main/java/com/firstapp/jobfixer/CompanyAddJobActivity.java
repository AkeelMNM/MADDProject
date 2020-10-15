package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.firstapp.jobfixer.Model.Jobs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompanyAddJobActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<String> JobID = new ArrayList<>();
    public static final String JOBS_ID_PREFIX ="job0";

    EditText txtComName,txtComAdd,txtComSal,txtComDes;
    Button AddJobBtn;
    String[] JobCategoryArray,JobTypeArray;
    ArrayAdapter<String> CategoryAdapter,TileAdapter,TypeAdapter;
    DatabaseReference DBRef;

    private String SelectCat ,SelectTit ,SelectType ;

    //Method to clear all inputs
    private void clearControls() {
        txtComName.setText("");
        txtComAdd.setText("");
        txtComSal.setText("");
        txtComDes.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_add_job);

        /**Accessing the Spinners,TextViews and button in the Activity**/

        Spinner jobCategory =(Spinner) findViewById(R.id.AddJobCatogorySpinner);

        Spinner JobType =(Spinner) findViewById(R.id.AddJobTypeSpinner);

        JobCategoryArray = getResources().getStringArray(R.array.jobCat);
        JobTypeArray = getResources().getStringArray(R.array.jobTy);

        txtComName = (EditText) findViewById(R.id.AddComName);
        txtComAdd = (EditText) findViewById(R.id.AddCompanyAddress);
        txtComSal = (EditText) findViewById(R.id.AddJobSalary);
        txtComDes = (EditText) findViewById(R.id.AddtxtDesc);

        AddJobBtn = (Button) findViewById(R.id.AddJobPostBtn);

        /**Assigning the values to Job Category Spinner in the Activity**/
        CategoryAdapter = new ArrayAdapter(CompanyAddJobActivity.this,android.R.layout.simple_spinner_item,JobCategoryArray);
        CategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobCategory.setAdapter(CategoryAdapter);
        jobCategory.setOnItemSelectedListener(this);

        /**Assigning the values to Job Type Spinner in the Activity**/
        TypeAdapter = new ArrayAdapter(CompanyAddJobActivity.this,android.R.layout.simple_spinner_item,JobTypeArray);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JobType.setAdapter(TypeAdapter);

        /**Reading the values in Job Type Spinner in the Activity**/
        SelectType = JobType.getSelectedItem().toString().trim();

        /**Reading the values in Company Name in the Activity**/
        txtComName.setText(SessionApplication.getUserName());

        AddJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Job.TABLE_NAME);
                DBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Jobs job = new Jobs();

                        job.setUserID(SessionApplication.getUserID());
                        job.setCategory(SelectCat);
                        job.setTitle(SelectTit);
                        job.setCompanyName(txtComName.getText().toString().trim());
                        job.setCompanyAddress(txtComAdd.getText().toString().trim());
                        job.setType(SelectType);
                        job.setSalary(txtComSal.getText().toString().trim());
                        job.setDescription(txtComDes.getText().toString().trim());

                        for(DataSnapshot jb: snapshot.getChildren())
                        {
                            JobID.add(jb.getValue().toString());
                        }

                        String id = null ;
                        int alSize = JobID.size();
                        alSize++;
                        id = JOBS_ID_PREFIX + alSize;

                        if(JobID.contains(id))
                        {
                            alSize++;
                            id = JOBS_ID_PREFIX + alSize;
                        }

                        if(TextUtils.isEmpty(txtComName.getText())){
                            Toast.makeText(CompanyAddJobActivity.this,"Enter Company Name",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtComAdd.getText())){
                            Toast.makeText(CompanyAddJobActivity.this,"Enter Company Address",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtComSal.getText())){
                            Toast.makeText(CompanyAddJobActivity.this,"Enter Salary",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(txtComDes.getText())){
                            Toast.makeText(CompanyAddJobActivity.this,"Enter Description",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            //Insert into Database
                            DBRef.child(id).setValue(job);

                            Toast.makeText(CompanyAddJobActivity.this, "Your Job Posted successfully..!! ",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(CompanyAddJobActivity.this,CompanyViewJobActivity.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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

    @Override
    public void onItemSelected (AdapterView< ? > parent, View view, int position, long id){

        /**Reading the values in Job Category Spinner in the Activity**/
        SelectCat = parent.getItemAtPosition(position).toString();

        Spinner JobTitle =(Spinner) findViewById(R.id.AddJobTitleSpinner);

        /**Change the values in Job Title according to the user selection of the job Category and assigning values to the job title Spinner in the Activity**/

        if (SelectCat.equals("Information Technology")) {
            TileAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.itJobCat));
            TileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            JobTitle.setAdapter(TileAdapter);

        }
        else if(SelectCat.equals("Business Management and Administration")){
            TileAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bmJobCat));
            TileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            JobTitle.setAdapter(TileAdapter);

        }
        else if(SelectCat.equals("Engineering")){
            TileAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.enJobCat));
            TileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            JobTitle.setAdapter(TileAdapter);

        }
        else if(SelectCat.equals("Hospitality and Tourism")){
            TileAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.htJobCat));
            TileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            JobTitle.setAdapter(TileAdapter);
        }
        else if(SelectCat.equals("Architecture and Construction")){
            TileAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.arJobCat));
            TileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            JobTitle.setAdapter(TileAdapter);
        }

        /**Reading the values in Job Title Spinner in the Activity**/
       //SelectTit = JobTitle.getSelectedItem().toString();

        JobTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                SelectTit = parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onNothingSelected (AdapterView < ? > parent){

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

        Intent intent = new Intent(CompanyAddJobActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(CompanyAddJobActivity.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(CompanyAddJobActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }

}
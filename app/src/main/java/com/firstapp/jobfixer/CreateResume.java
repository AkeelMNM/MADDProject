package com.firstapp.jobfixer;

import android.annotation.SuppressLint;
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
import com.firstapp.jobfixer.Model.Resume;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class CreateResume extends AppCompatActivity  {

    EditText firstName,lastName,location ,phone , email ,aboutMe ,workExperience , education;
    public static final String RESUME_ID_PREFIX ="Re";
    private ArrayList<String> ResId = new ArrayList<>();
    String JobCat ,JobTit;
    Resume res;
    DatabaseReference dbRef;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_resume);

        firstName = findViewById(R.id.txtFirstName);
        lastName = findViewById(R.id.txtLastName);
        location = findViewById(R.id.txtLocation);
        phone = findViewById(R.id.txtPhone);
        email = findViewById(R.id.txtEmail);
        aboutMe = findViewById(R.id.txtAboutMe);
        workExperience = findViewById(R.id.txtWorkExp);
        education = findViewById(R.id.txtEdu);
        btnCreate = findViewById(R.id.btnCreate);
        Spinner JobCategory = (Spinner) findViewById(R.id.JobCatSpinner);

        final Spinner JobTitle = (Spinner) findViewById(R.id.JobTitSpinner);




        /** Assigning a the values to job category **/

        /**Assigning the values to Job Category Spinner in the Activity**/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CreateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jobCat));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JobCategory.setPrompt("Select a Category");
        JobCategory.setAdapter(arrayAdapter);

        JobCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                JobCat = adapterView.getItemAtPosition(i).toString();

                if (JobCat.equals("Information Technology")) {
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(CreateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.itJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    JobTitle.setAdapter(arrayAdapterJ);

                }
                else if(JobCat.equals("Business Management and Administration")){
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(CreateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bmJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    JobTitle.setAdapter(arrayAdapterJ);

                }
                else if(JobCat.equals("Engineering")){
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(CreateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.enJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    JobTitle.setAdapter(arrayAdapterJ);

                }
                else if(JobCat.equals("Hospitality and Tourism")){
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(CreateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.htJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    JobTitle.setAdapter(arrayAdapterJ);
                }
                else if(JobCat.equals("Architecture and Construction")){
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(CreateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.arJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    JobTitle.setAdapter(arrayAdapterJ);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        JobTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                JobTit = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /** reading the value in Job Type spinner in activity **/
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertResume();


            }
        });

    }

    private void insertResume() {
        dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Resume res = new Resume();

                res.setUserId(SessionApplication.getUserID());
                res.setFirstName(firstName.getText().toString().trim());
                res.setLastName(lastName.getText().toString().trim());
                res.setPhone(phone.getText().toString().trim());
                res.setLocation(location.getText().toString().trim());
                res.setEmail(email.getText().toString().trim());
                res.setEducation(education.getText().toString().trim());
                res.setWorkExp(workExperience.getText().toString().trim());
                res.setAboutMe(aboutMe.getText().toString().trim());
                res.setJobCat(JobCat);
                res.setJobTit(JobTit);



                for(DataSnapshot jb: dataSnapshot.getChildren())
                {
                    ResId.add(jb.getValue().toString());
                }

                String id = null ;

                int alSize = ResId.size();
                alSize++;
                id = RESUME_ID_PREFIX + alSize;
                if(ResId.contains(id))
                {
                    alSize++;
                    id = RESUME_ID_PREFIX + alSize;
                }

                if(TextUtils.isEmpty(firstName.getText())){
                    Toast.makeText(CreateResume.this,"Enter First Name.. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(lastName.getText())){
                    Toast.makeText(CreateResume.this,"Enter Company Address.. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(phone.getText())){
                    Toast.makeText(CreateResume.this,"Enter Phone Number.. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(location.getText())){
                    Toast.makeText(CreateResume.this,"Enter Location.. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(email.getText())){
                    Toast.makeText(CreateResume.this,"Enter Email .. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(education.getText())){
                    Toast.makeText(CreateResume.this,"Enter Education.. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(workExperience.getText())){
                    Toast.makeText(CreateResume.this,"Enter workExperience.. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(aboutMe.getText())){
                    Toast.makeText(CreateResume.this,"Enter AboutMe.. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    //Insert into Database
                    dbRef.child(id).setValue(res);

                    Toast.makeText(CreateResume.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateResume.this,ViewResume.class);
                    startActivity(intent);
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

        Intent intent = new Intent(CreateResume.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(CreateResume.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(CreateResume.this,LoginActivity.class);
            startActivity(intent);
        }

    }



}
package com.firstapp.jobfixer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firstapp.jobfixer.Model.Resume;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import DataBase.DBMaster;


public class CreateResume extends AppCompatActivity {

    EditText firstName,lastName,location ,phone , email ,aboutMe ,workExperience , education;
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
        //Spinner JobCategory = (Spinner) findViewById(R.id.JobCatSpinner);


        /** Assigning a the values to job category **/

        /** ArrayAdapter<String> arrayAdapter = new ArrayAdapter<~>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getString(R.array.jobCat))
         arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         JobCategory.setAdapter(arrayAdapter);
         JobCategory.setOnItemSelectedListener(this);
         /** need to combine above code **/

        /** reading the value in Job Type spinner in activity **/
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Resume resume = new Resume();

                        resume.setUserId("Us02");
                        resume.setFirstName(firstName.getText().toString().trim());
                        resume.setLastName(lastName.getText().toString().trim());
                        resume.setPhone(phone.getText().toString().trim());
                        resume.setLocation(location.getText().toString().trim());
                        resume.setEmail(email.getText().toString().trim());
                        resume.setEducation(education.getText().toString().trim());
                        resume.setAboutMe(aboutMe.getText().toString().trim());
                        resume.setJobCat("IT");
                        resume.setJobTit("Software Eng");

                        dbRef.child("Re03").setValue(resume);


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


    /**@Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    Spinner JobTitle = (Spinner) findViewById(R.id.JobTitSpinner);

    JobCat = adapterView.getItemAtPosition(i).toString();




    /**Change the values in Job Title according to the user selection of the job Category and assigning values to the job title Spinner in the Activity**/

    /**
     if (JobCat.equals("Information Technology")) {
     ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.itJobCat));
     arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     JobTitle.setAdapter(arrayAdapterJ);

     }
     else if(JobCat.equals("Business Management and Administration")){
     ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bmJobCat));
     arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     JobTitle.setAdapter(arrayAdapterJ);

     }
     else if(JobCat.equals("Engineering")){
     ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.enJobCat));
     arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     JobTitle.setAdapter(arrayAdapterJ);

     }
     else if(JobCat.equals("Hospitality and Tourism")){
     ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.htJobCat));
     arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     JobTitle.setAdapter(arrayAdapterJ);
     }
     else if(JobCat.equals("Architecture and Construction")){
     ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.arJobCat));
     arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     JobTitle.setAdapter(arrayAdapterJ);
     }

     /**Reading the values in Job Title Spinner in the Activity**/
    //JobTit = JobTitle.getSelectedItem().toString();
    //}

    /**@Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }**/
}
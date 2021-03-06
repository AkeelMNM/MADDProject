package com.firstapp.jobfixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class UpdateResume extends AppCompatActivity {
    EditText UpfirstName,UplastName,Uplocation ,Upphone , Upemail ,UpaboutMe ,UpworkExperience ,upJob, Upeducation;
    String UJobCat ,UJobTit ,ResumID;
    Resume resu;
    DatabaseReference dbRef;
    Button BtnupdateRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_resume);
        UpfirstName = findViewById(R.id.Upfname);
        UplastName = findViewById(R.id.txtUpReLastName);
        Uplocation = findViewById(R.id.txtReUpLocation);
        Upphone = findViewById(R.id.txtREUpPhone);
        Upemail = findViewById(R.id.txtReUpEmail);
        UpaboutMe = findViewById(R.id.txtReUpAboutMe);
        UpworkExperience = findViewById(R.id.txtReUpWorkExp);
        Upeducation = findViewById(R.id.txtReUpEdu);
        BtnupdateRes = findViewById(R.id.btnReUpdate);
        final Spinner UJobCategory = (Spinner) findViewById(R.id.JobReUpCatSpinner);

        final Spinner UJobTitle = (Spinner) findViewById(R.id.JobReUpTitSpinner);

        /**Assigning the values to Job Category Spinner in the Activity**/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UpdateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.jobCat));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UJobCategory.setPrompt("Select a Category");
        UJobCategory.setAdapter(arrayAdapter);

        UJobCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                UJobCat = adapterView.getItemAtPosition(i).toString();

                if (UJobCat.equals("Information Technology")) {
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(UpdateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.itJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    UJobTitle.setAdapter(arrayAdapterJ);

                }
                else if(UJobCat.equals("Business Management and Administration")){
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(UpdateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bmJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    UJobTitle.setAdapter(arrayAdapterJ);

                }
                else if(UJobCat.equals("Engineering")){
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(UpdateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.enJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    UJobTitle.setAdapter(arrayAdapterJ);

                }
                else if(UJobCat.equals("Hospitality and Tourism")){
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(UpdateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.htJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    UJobTitle.setAdapter(arrayAdapterJ);
                }
                else if(UJobCat.equals("Architecture and Construction")){
                    ArrayAdapter<String> arrayAdapterJ = new ArrayAdapter<>(UpdateResume.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.arJobCat));
                    arrayAdapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    UJobTitle.setAdapter(arrayAdapterJ);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        UJobTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                UJobTit = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
        Query data = dbRef.orderByChild(DBMaster.Resume.COLUMN_NAME_USER_ID).equalTo(SessionApplication.getUserID());

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               for(DataSnapshot st:dataSnapshot.getChildren()){
                    ResumID = st.getKey();
                    UpfirstName.setText(st.child("firstName").getValue().toString());
                    UplastName.setText(st.child("lastName").getValue().toString());
                    Uplocation.setText(st.child("location").getValue().toString());
                    Upphone.setText(st.child("phone").getValue().toString());
                    Upemail.setText(st.child("email").getValue().toString());
                    UpaboutMe.setText(st.child("aboutMe").getValue().toString());
                    UpworkExperience.setText(st.child("workExp").getValue().toString());
                    Upeducation.setText(st.child("education").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        BtnupdateRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateResume();

            }
        });


    }

    private void updateResume() {

    dbRef = FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME);
    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Resume resume =new Resume();
            resume.setUserId(SessionApplication.getUserID());
            resume.setFirstName(UpfirstName.getText().toString().trim());
            resume.setLastName(UplastName.getText().toString().trim());
            resume.setPhone(Upphone.getText().toString().trim());
            resume.setLocation(Uplocation.getText().toString().trim());
            resume.setEmail(Upemail.getText().toString().trim());
            resume.setEducation(Upeducation.getText().toString().trim());
            resume.setWorkExp(UpworkExperience.getText().toString().trim());
            resume.setAboutMe(UpaboutMe.getText().toString().trim());
            resume.setJobCat(UJobCat);
            resume.setJobTit(UJobTit);
//tis code is not working properly need to check
            dbRef= FirebaseDatabase.getInstance().getReference().child(DBMaster.Resume.TABLE_NAME).child(SessionApplication.getResumeID());
            dbRef.setValue(resume);

            Toast.makeText(UpdateResume.this, "Data Changed Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateResume.this,ViewResume.class);
            startActivity(intent);



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

        Intent intent = new Intent(UpdateResume.this,LoginActivity.class);
        startActivity(intent);


    }

    private void helpCenter() {
        Intent intent = new Intent(UpdateResume.this,HelpCenterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** check user is log in**/
        if(SessionApplication.getUserName().equals("")){
            Intent intent = new Intent(UpdateResume.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}